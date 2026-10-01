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

import com.github.jinahya.oracle.sample.schemas.persistence.test.__Persistence_IT_Producer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * Bootstraps the {@value #PERSISTENCE_UNIT_NAME} persistence unit for the {@code HR} module.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote The name carries the module, because every module of this build lands on one classpath whenever they are
 * built or run together, and a persistence unit name is only unique within the archive declaring it. Sharing one name
 * across the modules leaves the provider to take whichever descriptor it finds first, which it does silently.
 * @implNote The four bean methods are overridden only to carry their annotations. CDI does not inherit producer or
 * disposer methods from a superclass -- an inherited {@code @Produces} is simply not seen, and the injection point
 * fails with {@code WELD-001408} -- so the concrete producer has to declare them, even when the body is the
 * superclass's.
 */
@ApplicationScoped
class _Persistence_IT_Producer extends __Persistence_IT_Producer {

    /**
     * The name of the persistence unit this producer bootstraps. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = "__hr_itPU";

    // ---------------------------------------------------------------------------------------------- CONSTRUCTORS
    _Persistence_IT_Producer() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------
    @Override
    protected String persistenceUnitName() {
        return PERSISTENCE_UNIT_NAME;
    }

    // -----------------------------------------------------------------------------------------------------------
    @Produces
    @__ItPU
    @ApplicationScoped
    @Override
    protected EntityManagerFactory produceEntityManagerFactory() {
        return super.produceEntityManagerFactory();
    }

    @Override
    protected void disposeEntityManagerFactory(@Disposes @__ItPU final EntityManagerFactory entityManagerFactory) {
        super.disposeEntityManagerFactory(entityManagerFactory);
    }

    // -----------------------------------------------------------------------------------------------------------
    @Produces
    @__ItPU
    @Dependent
    @Override
    protected EntityManager produceEntityManager(@__ItPU final EntityManagerFactory entityManagerFactory) {
        return super.produceEntityManager(entityManagerFactory);
    }

    @Override
    protected void disposeEntityManager(@Disposes @__ItPU final EntityManager entityManager) {
        super.disposeEntityManager(entityManager);
    }
}
