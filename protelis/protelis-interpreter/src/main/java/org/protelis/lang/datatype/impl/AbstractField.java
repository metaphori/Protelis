/*******************************************************************************
 * Copyright (C) 2014, 2015, Danilo Pianini and contributors
 * listed in the project's build.gradle or pom.xml file.
 *
 * This file is part of Protelis, and is distributed under the terms of
 * the GNU General Public License, with a linking exception, as described
 * in the file LICENSE.txt in this project's top directory.
 *******************************************************************************/
package org.protelis.lang.datatype.impl;

import java.util.Objects;
import java8.util.Optional;
import java8.util.function.BinaryOperator;

import org.apache.commons.math3.util.Pair;
import org.protelis.lang.datatype.DeviceUID;
import org.protelis.lang.datatype.Field;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

/**
 * Core functionality for implementing a field.
 */
public abstract class AbstractField implements Field {

    private static final long serialVersionUID = 7507440716878809781L;

    @Override
    public final DeviceUID reduceKeys(final BinaryOperator<DeviceUID> op, final DeviceUID exclude) {
        return reduce(nodeIterator(), op, exclude, null);
    }

    @Override
    public final Object reduceVals(final BinaryOperator<Object> op, final DeviceUID exclude, final Object defaultVal) {
        return reduce(valIterator(), op, exclude == null ? null : getSample(exclude), defaultVal);
    }

    @Override
    public final Pair<DeviceUID, Object> reducePairs(
            final BinaryOperator<Pair<DeviceUID, Object>> accumulator,
            final DeviceUID exclude) {
        return reduce(
                    coupleIterator(),
                    accumulator,
                    exclude == null ? null : new Pair<>(exclude, getSample(exclude)), null);
    }

    /**
     * Reduce the desired {@link Iterable} using the provided
     * {@link BinaryOperator}, excluding an element and returning a default
     * value if the {@link Iterable} is empty or only contains the value to
     * exclude. If multiple elements to exclude are present in the iterable,
     * only the first will be discarded.
     * 
     * @param c
     *            the {@link Iterable}
     * @param op
     *            the {@link BinaryOperator} that selects which element should
     *            be kept
     * @param exclude
     *            element not to consider (can be null)
     * @param defaultVal
     *            the value to return if the iterator is empty or only contains
     *            the value to ignore
     * @param <T>
     *            return type
     * @return the reduced element
     * 
     */
    protected static <T> T reduce(
            final Iterable<T> c,
            final BinaryOperator<T> op,
            final T exclude,
            final T defaultVal) {
        Objects.requireNonNull(c);
        Objects.requireNonNull(op);
        boolean filter = exclude != null;
        Optional<T> result = Optional.empty();
        for (final T el : c) {
            if (filter && el.equals(exclude)) {
                filter = false;
            } else {
                if (result.isPresent()) {
                    result = Optional.of(op.apply(result.get(), el));
                } else {
                    result = Optional.of(el);
                }
            }
        }
        return result.orElse(defaultVal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        for (final Pair<DeviceUID, Object> entry : coupleIterator()) {
            sb.append(entry.getKey());
            sb.append(':');
            sb.append(entry.getValue());
            sb.append(", ");
        }
        if (!isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Field) {
            final Field cmp = (Field) o;
            if (cmp.size() == size()) {
                for (final Pair<DeviceUID, Object> pv : coupleIterator()) {
                    if (!pv.getSecond().equals(cmp.getSample(pv.getFirst()))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        final Hasher hasher = Hashing.murmur3_32().newHasher();
        for (final Pair<DeviceUID, Object> pv : coupleIterator()) {
            hasher.putInt(pv.hashCode());
        }
        return hasher.hash().asInt();
    }

}
