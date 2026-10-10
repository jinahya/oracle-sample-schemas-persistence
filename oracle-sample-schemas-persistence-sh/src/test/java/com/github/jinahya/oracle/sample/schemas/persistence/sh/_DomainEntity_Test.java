package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * sh
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

import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;

/**
 * An abstract base class for testing, without a persistence context, a class which maps a table or a view.
 * <p>
 * Its checks are, for now, the ones {@link _NonEntity_Test} runs too; checks only such a class has to pass go here.
 *
 * @param <T> the type of the class.
 * @param <U> the type of the identifier of the class; {@link Void} for a view with no key.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
abstract class _DomainEntity_Test<T extends __DomainEntity<U>, U> extends __Test<T> {

    /**
     * Creates a new instance for the specified target class.
     *
     * @param targetClass the class to test.
     */
    protected _DomainEntity_Test(final Class<T> targetClass) {
        super(targetClass);
    }

    // -------------------------------------------------------------------------------------------------------- toString

    @Override
    @Test
    protected void toString_NotBlank_NewInstance() {
        super.toString_NotBlank_NewInstance();
    }

    @Override
    @Test
    protected void toString_NotBlank_NewRandomizedInstance() {
        super.toString_NotBlank_NewRandomizedInstance();
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    @Override
    @Test
    protected void equals_verify_() {
        super.equals_verify_();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    @Test
    protected void propertyAccessors_DoNotThrow() throws IntrospectionException {
        super.propertyAccessors_DoNotThrow();
    }
}
