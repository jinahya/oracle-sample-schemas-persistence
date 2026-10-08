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

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.MethodHandles;

/**
 * Produces, as CDI beans, the {@link EntityManagerFactory} of a persistence unit, and the {@link EntityManager}s it
 * creates.
 * <p>
 * The unit is the one named {@value #PERSISTENCE_UNIT_NAME}, which this module's {@code META-INF/persistence.xml}
 * declares. A persistence unit name is only required to be unique within the archive that declares it, and every
 * module of this build may end up on one classpath -- an IDE running every test of the project, say -- so each module
 * names its units after its own schema, {@code CO}.
 * <p>
 * The unit is bootstrapped the Java SE way, through {@link Persistence#createEntityManagerFactory(String)}, so the
 * provider, the JDBC properties and the listed entity classes are whatever {@code META-INF/persistence.xml} says --
 * which is how the same producer works under either persistence provider profile without a change here.
 * <p>
 * The two scopes mirror the two lifetimes:
 * <ul>
 * <li>the factory is {@link ApplicationScoped}, because opening one is what costs -- the provider reads the
 * descriptor, builds the metamodel and connects to the database exactly once per container;</li>
 * <li>an entity manager is {@link Dependent}, and so is created afresh at each injection point and destroyed with
 * whatever it was injected into -- a persistence context is short-lived, and sharing one across tests would leak
 * managed instances from one into the next.</li>
 * </ul>
 * Each has a {@link Disposes disposer}, so neither is left open when the container shuts down: a test which opens a
 * factory by hand has to close it by hand, in an {@code @AfterAll} which runs even when a test throws; here,
 * destroying the container does it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see _Persistence_IT
 */
@ApplicationScoped
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class _Persistence_IT_Producer {

    /**
     * The name of the persistence unit this producer bootstraps. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = "co-it";

    private static final System.Logger logger = System.getLogger(MethodHandles.lookup().lookupClass().getName());

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A CDI qualifier for the beans of the persistence unit this producer bootstraps.
     * <p>
     * Each schema module declares two persistence units, so an unqualified {@link EntityManager} would be an
     * ambiguous-resolution failure rather than a second bean. The qualifier names the unit at both ends: the producer
     * declares which unit it produces, and an injection point declares which unit it wants.
     * <p>
     * Declaring it also removes {@link jakarta.enterprise.inject.Default @Default} from these beans, so an injection
     * point which asks for a bare {@code EntityManager} is left unsatisfied, deliberately: the physical database is
     * never touched by a test which meant the in-memory unit.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    @Documented
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
    public @interface __ItPU {

        /**
         * An {@link AnnotationLiteral} of {@link __ItPU}, for selecting a bean programmatically.
         *
         * @see jakarta.enterprise.inject.Instance#select(Class, java.lang.annotation.Annotation...)
         */
        final class Literal extends AnnotationLiteral<__ItPU> implements __ItPU {

            /**
             * The single instance of this literal; the annotation declares no member, so one is enough.
             */
            public static final Literal INSTANCE = new Literal();

            private static final long serialVersionUID = 1L;

            private Literal() {
                super();
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    protected _Persistence_IT_Producer() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Produces the factory of the persistence unit this producer bootstraps.
     *
     * @return a new factory, created from {@code META-INF/persistence.xml}.
     * @see Persistence#createEntityManagerFactory(String)
     */
    @Produces
    @__ItPU
    @ApplicationScoped
    protected EntityManagerFactory produceEntityManagerFactory() {
        final var persistenceUnitName = PERSISTENCE_UNIT_NAME;
        logger.log(System.Logger.Level.DEBUG, "creating a factory of {0}", persistenceUnitName);
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    /**
     * Closes the factory this producer produced, when the container destroys it.
     *
     * @param entityManagerFactory the factory to close.
     * @implNote Guarded by {@link EntityManagerFactory#isOpen()}: a test which closed it itself, which is allowed, must
     * not make the shutdown fail.
     */
    protected void disposeEntityManagerFactory(@Disposes @__ItPU final EntityManagerFactory entityManagerFactory) {
        if (entityManagerFactory.isOpen()) {
            logger.log(System.Logger.Level.DEBUG, "closing {0}", entityManagerFactory);
            entityManagerFactory.close();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Produces a new entity manager, from the factory of the persistence unit this producer bootstraps.
     *
     * @param entityManagerFactory the factory, injected by the {@link __ItPU} qualifier; the parameter of a producer
     *                             method is an injection point.
     * @return a new entity manager.
     * @implNote {@link Dependent}, not {@link ApplicationScoped}: a persistence context is not something to share. Note
     * that this leaves the transaction to the caller -- the unit is {@code RESOURCE_LOCAL}, so there is no container
     * transaction to join.
     * @see EntityManagerFactory#createEntityManager()
     */
    @Produces
    @__ItPU
    @Dependent
    protected EntityManager produceEntityManager(@__ItPU final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Closes an entity manager this producer produced, when the container destroys it.
     *
     * @param entityManager the entity manager to close.
     * @implNote Guarded by {@link EntityManager#isOpen()}, for the same reason the factory's disposer is: an entity
     * manager used in a try-with-resources is already closed by the time it is destroyed.
     */
    protected void disposeEntityManager(@Disposes @__ItPU final EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
