package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

public abstract class MappedJobHistory_Service<
        ENTITY extends MappedJobHistory<ID>,
        ID extends MappedJobHistoryId
        >
        extends _MappedHrEntity_Service<ENTITY, ID> {

    protected MappedJobHistory_Service(final Class<ENTITY> entityClass, final Class<ID> idClass,
                                       final EntityManager entityManager) {
        super(entityClass, idClass, entityManager);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected List<ENTITY> findAllByEmployeeId(
            final Function<? super Root<ENTITY>, ? extends Path<String>> employeeIdPathFunction,
            final Function<? super Root<ENTITY>, ? extends Path<LocalDate>> startDatePathFunction,
            final String employeeId, final int firstResult, final int maxResults) {
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(entityClass);
        final var root = query.from(entityClass);
        query.select(root);
        query.where(builder.equal(employeeIdPathFunction.apply(root), employeeId));
        query.orderBy(builder.asc(startDatePathFunction.apply(root)));
        return entityManager.createQuery(query)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
}
