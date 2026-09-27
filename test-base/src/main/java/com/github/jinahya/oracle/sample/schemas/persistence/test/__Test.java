package com.github.jinahya.oracle.sample.schemas.persistence.test;

/*-
 * #%L
 * test-base
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

import com.github.jinahya.persistence.test.util.__InstantiatorUtils;
import com.github.jinahya.persistence.test.util.__RandomizerUtils;

import java.util.Objects;
import java.util.Optional;

public abstract class __Test<T> {

    protected __Test(final Class<T> targetClass) {
        super();
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    public T newTargetInstance() {
        return __InstantiatorUtils.newInstantiatedInstanceOf(targetClass);
    }

    public Optional<T> newRandomizedTargetInstance() {
        return __RandomizerUtils.newRandomizedInstanceOf(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> targetClass;
}
