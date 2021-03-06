package org.protelis.vm.impl;

import java.io.Serializable;
import java.util.Arrays;

import org.protelis.vm.CodePath;
import org.protelis.vm.CodePathFactory;

import com.google.common.hash.Hasher;

import gnu.trove.list.TIntList;
import gnu.trove.stack.TIntStack;
import java8.util.function.Supplier;

/**
 * An hash-based {@link CodePath} factory. It allows for predictable packet
 * sizes, as codepath length is no affected by the evaluation tree depth at
 * which the field was built. It is arguably more secure than the default option
 * (if a cryptographic hashing function is used) as receivers cannot deduce the
 * code that generated some values. The provided factory is configurable by
 * using an {@link HashFunction} from Guava. Being hash based, it has a non-zero
 * probability of collision. Using decent hash functions (e.g. SHA) should
 * however make the event very unlikely. In any case, there is a trade-off
 * between collision probability and packet size.
 */
public class HashingCodePathFactory implements CodePathFactory {

    private static final long serialVersionUID = 1L;
    private final HasherSupplier algorithm;

    /**
     * @param hashFunction the hashing algorithm to use
     */
    public HashingCodePathFactory(final HasherSupplier hashFunction) {
        algorithm = hashFunction;
    }

    @Override
    public final CodePath createCodePath(final TIntList callStackIdentifiers, final TIntStack callStackSizes) {
        final Hasher hasher = algorithm.get();
        callStackIdentifiers.forEach(it -> {
            hasher.putInt(it);
            return true;
        });
        return new HashingCodePath(hasher.hash().asBytes(), false);
    }

    /**
     * Serializable supplier, because Java 8 lambdas are not.
     */
    @FunctionalInterface
    public interface HasherSupplier extends Supplier<Hasher>, Serializable { }

    /**
     * Hash-based {@link CodePath}.
     */
    public static final class HashingCodePath implements CodePath {
        private static final long serialVersionUID = 1L;
        private final byte[] hash;

        /**
         * Builds a new {@link HashingCodePath} based on the provided hash.
         * 
         * @param hash a byte array representing the hash it must be at least four bytes
         *             (though longer hashes are warmly recommended to avoid collisions)
         */
        public HashingCodePath(final byte[] hash) {
            this(hash, true);
        }

        /**
         * Builds a new {@link HashingCodePath} based on the provided hash.
         * 
         * @param hash a byte array representing the hash it must be at least four bytes
         *             (though longer hashes are warmly recommended to avoid collisions)
         */
        private HashingCodePath(final byte[] hash, final boolean copy) {
            if (hash.length < 4) {
                throw new IllegalArgumentException("Hashes shorter than four bytes are unacceptable: " + Arrays.toString(hash));
            }
            this.hash = copy ? Arrays.copyOf(hash, hash.length) : hash;
        }
        @Override
        public boolean equals(final Object obj) {
            return obj instanceof HashingCodePath
                    && Arrays.equals(hash, ((HashingCodePath) obj).hash);
        }
        @Override
        public int hashCode() {
            // CHECKSTYLE: MagicNumber OFF
            return (hash[0] & 0xFF)
                | ((hash[1] & 0xFF) << 8)
                | ((hash[2] & 0xFF) << 16)
                | ((hash[3] & 0xFF) << 24);
            // CHECKSTYLE: MagicNumber ON
        }
    }

}
