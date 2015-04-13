/*
 * Copyright (C) 2010-2015, Danilo Pianini and contributors
 * listed in the project's pom.xml file.
 * 
 * This file is part of Alchemist, and is distributed under the terms of
 * the GNU General Public License, with a linking exception, as described
 * in the file LICENSE in the Alchemist distribution's top directory.
 */
package it.unibo.alchemist.model.implementations.actions;

import gnu.trove.TCollections;
import gnu.trove.list.TByteList;
import gnu.trove.list.array.TByteArrayList;
import gnu.trove.map.TIntObjectMap;
import it.unibo.alchemist.external.cern.jet.random.engine.RandomEngine;
import it.unibo.alchemist.language.protelis.FunctionDefinition;
import it.unibo.alchemist.language.protelis.interfaces.AnnotatedTree;
import it.unibo.alchemist.language.protelis.util.CodePath;
import it.unibo.alchemist.language.protelis.util.StackImpl;
import it.unibo.alchemist.model.implementations.nodes.ProtelisNode;
import it.unibo.alchemist.model.implementations.nodes.ProtelisNode.Self;
import it.unibo.alchemist.model.interfaces.IEnvironment;
import it.unibo.alchemist.model.interfaces.IMolecule;
import it.unibo.alchemist.model.interfaces.INode;
import it.unibo.alchemist.model.interfaces.IReaction;
import it.unibo.alchemist.utils.FasterString;
import it.unibo.alchemist.utils.ParseUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.math3.util.Pair;

import com.google.common.collect.MapMaker;

/**
 * @author Danilo Pianini
 *
 */
public class ProtelisProgram extends AbstractLocalAction<Object> implements IMolecule {

	private static final long serialVersionUID = 2207914086772704332L;
	/**
	 * Prefix for building Protelis molecules.
	 */
	public static final String PROGRAM_ID_PREFIX = "protelis-";
	private static final ConcurrentMap<FasterString, ProtelisProgram> DB = new MapMaker().weakValues().makeMap();
	private final FasterString programString, pidString;
	private final int hash;
	private final IEnvironment<Object> environment;
	private final IReaction<Object> reaction;
	private final ProtelisNode node;
	private final Map<FasterString, FunctionDefinition> fundefs;
	private boolean hasComputed;
	private final AnnotatedTree<?> program;
	private Map<CodePath, Object> lastExec;
	private String string;
	
	private ProtelisProgram(final IEnvironment<Object> env,  final INode<Object> n, final IReaction<Object> r, final AnnotatedTree<?> prog, final Map<FasterString, FunctionDefinition> funs, final FasterString pString) {
		this(n, env, r, new Pair<>(prog, funs), pString);
	}
	
	/**
	 * @param env the environment
	 * @param n the node
	 * @param r the reaction
	 * @param rand the random engine
	 * @param prog the Protelis program
	 * @throws SecurityException if you are not authorized to load required classes
	 * @throws ClassNotFoundException if required classes can not be found
	 */
	public ProtelisProgram(final IEnvironment<Object> env,  final ProtelisNode n, final IReaction<Object> r, final RandomEngine rand, final String prog) throws SecurityException, ClassNotFoundException {
		this(n, env, r, ParseUtils.parse(env, n, r, rand, prog), new FasterString(ParseUtils.filterSpaces(prog)));
	}

	private ProtelisProgram(final INode<Object> n, final IEnvironment<Object> env, final IReaction<Object> r, final Pair<AnnotatedTree<?>, Map<FasterString, FunctionDefinition>> prog, final FasterString pString) {
		super(n);
		Objects.requireNonNull(n);
		environment = env;
		reaction = r;
		program = prog.getFirst();
		fundefs = prog.getSecond();
		programString = pString;
		hash = programString.hashCode();
		pidString = new FasterString(PROGRAM_ID_PREFIX + programString.hashToString());
		node = getNode();
		addModifiedMolecule(this);
		DB.put(new FasterString(getProgramIDAsString()), this);
	}

	@Override
	public ProtelisProgram cloneOnNewNode(final INode<Object> n, final IReaction<Object> r) {
		return new ProtelisProgram(environment, n, r, program, fundefs, programString);
	}
	
	@Override
	public boolean dependsOn(final IMolecule mol) {
		return getId() == mol.getId();
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof ProtelisProgram) {
			return programString.equals(((ProtelisProgram) o).programString);
		}
		return false;
	}

	@Override
	public void execute() {
		final Map<CodePath, Object> newExec = lastExec == null ? new HashMap<>() : new HashMap<>(lastExec.size() * 3 / 2, 1f);
		final Self safeView = node.getSelf();
		final Map<FasterString, Object> gamma = safeView.getGamma();
		gamma.putAll(fundefs);
		// Note: TByteArrayList must start with non-zero byte to ensure different length "zero" extensions are distinguishable
		final TByteList initialPosition = new TByteArrayList(); initialPosition.add((byte) 1);
		/*
		 * Make sure nobody destroys theta while computing.
		 */
		final TIntObjectMap<Map<CodePath, Object>> theta = TCollections.unmodifiableMap(node.getTheta(this));
		program.eval(safeView, theta, new StackImpl(gamma), lastExec, newExec, initialPosition);
		safeView.commit();
		lastExec = newExec;
		node.setConcentration(this, program.getAnnotation());
		hasComputed = true;
	}

	/**
	 * @return the environment
	 */
	protected IEnvironment<Object> getEnvironment() {
		return environment;
	}

	@Override
	public long getId() {
		return programString.hash64();
	}

	@Override
	public ProtelisNode getNode() {
		return (ProtelisNode) super.getNode();
	}

	public Map<CodePath, Object> getLastProgramExecution() {
		return lastExec;
	}
	
	protected AnnotatedTree<?> getProgram() {
		return program;
	}
	
	/**
	 * @return the hosting reaction
	 */
	protected IReaction<Object> getReaction() {
		return reaction;
	}
	
	@Override
	public int hashCode() {
		return hash;
	}
	
	public boolean isComputationCycleComplete() {
		return hasComputed;
	}
	
	public void setCycleComplete() {
		hasComputed = false;
	}
	
	@Override
	public String toString() {
		if (string == null) {
			string = getClass().getSimpleName() + " id: " + programString.hashToString();
		}
		return string;
	}
	
	public String getProgramIDAsString() {
		return pidString.toString();
	}
	
	public FasterString getProgramIDAsFasterString() {
		return pidString;
	}
	
	public static ProtelisProgram getProgramByID(final FasterString id) {
		return DB.get(id);
	}
	
	public static ProtelisProgram getProgramByID(final String id) {
		return getProgramByID(new FasterString(id));
	}
	
}
