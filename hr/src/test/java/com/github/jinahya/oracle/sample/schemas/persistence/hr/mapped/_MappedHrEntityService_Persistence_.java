package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

/*-
 * #%L
 * hr
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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

import com.github.jinahya.persistence.mapped.test.__PersistenceProducer;
import com.github.jinahya.persistence.mapped.test.__PersistenceUnit_Test;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.persistence.EntityManagerFactory;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

@AddBeanClasses({
        __PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHrEntityService_Persistence_<
        SERVICE extends _MappedHrEntityService<ENTITY, ID>,
        ENTITY extends _MappedHrEntity<ID>,
        ID
        > extends __PersistenceUnit_Test {

    private static final System.Logger logger = System.getLogger(MethodHandles.lookup().lookupClass().getName());

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     * @param idClass     the id class of the {@code entityClass}.
     */
    protected _MappedHrEntityService_Persistence_(final Class<SERVICE> serviceClass, final Class<ENTITY> entityClass,
                                                  final Class<ID> idClass) {
        super();
        this.serviceClass = Objects.requireNonNull(serviceClass, "serviceClass is null");
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PostConstruct
    protected void doOnPostConstruct() {
        logger.log(System.Logger.Level.TRACE, "onPostConstruct{0})");
        try {
            final var constructor = serviceClass.getDeclaredConstructor(EntityManagerFactory.class);
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            serviceInstance = constructor.newInstance(entityManagerFactory);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // https://stackoverflow.com/a/72628439/330457
    protected void onStartup(@Observes final Startup startup) {
        logger.log(System.Logger.Level.TRACE, "onStartup{0})", startup);
    }

    @PreDestroy
    protected void onPreDestroy() {
        logger.log(System.Logger.Level.TRACE, "onPreDestroy()");
    }

    // https://stackoverflow.com/a/72628439/330457
    protected void onShutdown(@Observes final Shutdown shutdown) {
        logger.log(System.Logger.Level.TRACE, "onShutdown({0})", shutdown);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<SERVICE> serviceClass;

    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;

    // -----------------------------------------------------------------------------------------------------------------
    private SERVICE serviceInstance;
}
