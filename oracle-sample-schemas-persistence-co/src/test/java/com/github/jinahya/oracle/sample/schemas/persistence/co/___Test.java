package com.github.jinahya.oracle.sample.schemas.persistence.co;

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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import org.junit.platform.commons.util.ReflectionUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * An abstract base class for testing a target class, which the subclass names.
 *
 * @param <T> the type of the target class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class ___Test<T> {

    /**
     * Creates a new instance for the specified target class.
     *
     * @param targetClass the class to test.
     */
    protected ___Test(final Class<T> targetClass) {
        super();
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a new, uninitialized instance of {@link #targetClass}.
     *
     * @return a new instance of {@link #targetClass}.
     */
    public T newTargetInstance() {
        return ReflectionUtils.newInstance(targetClass);
    }

    /**
     * Returns a new instance of {@link #targetClass} with randomized property values.
     *
     * @return a new randomized instance of {@link #targetClass}; empty when no randomizer is registered for
     * {@link #targetClass}.
     */
    public Optional<T> newRandomizedTargetInstance() {
        return ObjectRandomizerUtils.newRandomizedInstanceOf(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The class which this test targets.
     */
    protected final Class<T> targetClass;
}
