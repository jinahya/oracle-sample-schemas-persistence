package com.github.jinahya.oracle.sample.schemas.persistence.hr;

/*-
 * #%L
 * hr
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

import com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_Test;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.EnableAutoWeld;

/**
 * An abstract base class for {@code HR} tests which need a persistence context on the in-memory database, against the
 * {@value _Persistence_Test_Producer#PERSISTENCE_UNIT_NAME} persistence unit.
 *
 * @param <T> the type of the entity under test.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote This class exists to name the module's producer: the container annotations have to sit where the producer is
 * known, and {@link __Persistence_Test} is shared by every module. Everything a subclass uses is inherited from there;
 * this adds nothing but the wiring.
 */
@AddBeanClasses(_Persistence_Test_Producer.class)
@EnableAutoWeld
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // "abstract" classes should not have "public" constructors
})
abstract class _Persistence_Test<T> extends __Persistence_Test<T> {

    /**
     * Creates a new instance for the specified persistence class.
     *
     * @param targetClass the class of the entity under test.
     */
    protected _Persistence_Test(final Class<T> targetClass) {
        super(targetClass);
    }
}
