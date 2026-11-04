package com.github.jinahya.oracle.sample.persistence.co.mapped;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.oracle.sample.persistence.co._NonEntity_Test;
import org.junit.jupiter.api.Test;

/**
 * An abstract class for testing subclasses of {@link MappedBinary}.
 *
 * @param <T> the type of the subclass under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
abstract class MappedBinary_Test<T extends MappedBinary>
        extends _NonEntity_Test<T> {

    /**
     * Creates a new instance for the specified subclass of {@link MappedBinary}.
     *
     * @param targetClass the subclass to test.
     */
    MappedBinary_Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * Does nothing; a subclass of {@link MappedBinary} is not a class {@code EqualsVerifier} has
     * anything to verify.
     *
     * @implNote Neither {@link MappedBinary}, which holds the state, nor its subclasses
     *         declare {@code equals(Object)}, so instances carry {@link Object}'s reference
     *         equality. There is no value equality to verify, and suppressing every check that
     *         follows from that would leave the test asserting nothing anyway.
     */
    @Override
    @Test
    public void equals_verify_() {
        // empty
    }
}
