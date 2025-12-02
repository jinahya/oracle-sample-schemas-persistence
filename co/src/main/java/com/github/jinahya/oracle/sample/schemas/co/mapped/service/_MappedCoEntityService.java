package com.github.jinahya.oracle.sample.schemas.co.mapped.service;

import com.github.jinahya.oracle.sample.schemas.co.mapped._MappedCoEntity;
import com.github.jinahya.persistence.JinahyaEntityManagerUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S119"  // Type parameter names should comply with a naming convention
})
public abstract class _MappedCoEntityService<ENTITY extends _MappedCoEntity<ID>, ID> {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS
    protected _MappedCoEntityService(final @Nonnull Class<ENTITY> entityClass, final @Nonnull Class<ID> idClass,
                                     final @Nonnull EntityManager entityManager) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
        this.idClass = Objects.requireNonNull(idClass, "idClass is null");
        this.entityManager = Objects.requireNonNull(entityManager, "entityManager is null");
    }

    // ----------------------------------------------------------------------------------------------------- entityClass

    // --------------------------------------------------------------------------------------------------------- idClass

    // --------------------------------------------------------------------------------------------------- entityManager
    protected <R> R applyEntityManager(final @Nonnull Function<EntityManager, R> function, final boolean transactional,
                                       final boolean rollback) {
        Objects.requireNonNull(function, "function is null");
        if (transactional) {
            return JinahyaEntityManagerUtils.applyInTransaction(
                    entityManager,
                    function,
                    rollback
            );
        }
        return function.apply(entityManager);
    }

    protected <R> R applyCriteriaBuilder(final Function<
                                                 ? super EntityManager,
                                                 ? extends Function<
                                                         ? super CriteriaBuilder,
                                                         ? extends R
                                                         >
                                                 > function,
                                         final boolean transactional,
                                         final boolean rollback) {
        Objects.requireNonNull(function, "function is null");
        return applyEntityManager(
                em -> function.apply(em).apply(em.getCriteriaBuilder()),
                transactional,
                rollback
        );
    }

    protected <R> R applyCriteriaQuery(final Function<
                                               ? super EntityManager,
                                               ? extends Function<
                                                       ? super CriteriaBuilder,
                                                       ? extends Function<
                                                               ? super CriteriaQuery<ENTITY>,
                                                               ? extends R
                                                               >
                                                       >> function,
                                       final boolean transactional,
                                       final boolean rollback) {
        return applyCriteriaBuilder(
                em -> b -> function.apply(em).apply(b).apply(b.createQuery(entityClass)),
                transactional,
                rollback
        );
    }

    protected <R> R applyRoot(final Function<
                                      ? super EntityManager,
                                      ? extends Function<
                                              ? super CriteriaBuilder,
                                              ? extends Function<
                                                      ? super CriteriaQuery<ENTITY>,
                                                      ? extends Function<
                                                              ? super Root<ENTITY>,
                                                              ? extends R
                                                              >
                                                      >
                                              >
                                      > function,
                              final boolean transactional,
                              final boolean rollback) {
        return applyCriteriaQuery(
                em -> b -> q -> function.apply(em).apply(b).apply(q).apply(q.from(entityClass)),
                transactional,
                rollback
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;

    // -----------------------------------------------------------------------------------------------------------------
    private EntityManager entityManager;
}
