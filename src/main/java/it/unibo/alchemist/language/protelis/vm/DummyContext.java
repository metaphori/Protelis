/**
 * 
 */
package it.unibo.alchemist.language.protelis.vm;

import it.unibo.alchemist.external.cern.jet.random.engine.MersenneTwister;
import it.unibo.alchemist.external.cern.jet.random.engine.RandomEngine;
import it.unibo.alchemist.language.protelis.util.Device;
import it.unibo.alchemist.model.implementations.positions.Continuous2DEuclidean;
import it.unibo.alchemist.model.interfaces.IPosition;
import it.unibo.alchemist.utils.FasterString;

import java.util.Collections;
import java.util.Map;

/**
 * A dummy Protelis VM to be used for testing.
 * 
 * @author Danilo Pianini
 *
 */
public class DummyContext extends AbstractExecutionContext {
	
	private final RandomEngine rng = new MersenneTwister();
	
	private static class DummyDevice implements Device {
		private static final long serialVersionUID = -4804905144759361059L;
		@Override
		public long getId() {
			return 0;
		}
	}
	
	private final Device dummy = new DummyDevice();
	
	/**
	 * @param availableFunctions
	 *            the functions available for this program
	 */
	public DummyContext(final Map<FasterString, ?> availableFunctions) {
		super(new DummyNetworkManager(), availableFunctions);
	}

	@Override
	public Device getLocalDevice() {
		return dummy;
	}

	@Override
	public double getCurrentTime() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double distanceTo(final Device target) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IPosition getDevicePosition() {
		return new Continuous2DEuclidean(0, 0);
	}
	
	@Override
	public double nextRandomDouble() {
		return rng.nextDouble();
	}

	@Override
	protected AbstractExecutionContext instance() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Device deviceFromId(final long id) {
		return dummy;
	}

	@Override
	protected Map<FasterString, Object> currentEnvironment() {
		return Collections.emptyMap();
	}

	@Override
	protected void setEnvironment(final Map<FasterString, Object> newEnvironment) {
	}


}
