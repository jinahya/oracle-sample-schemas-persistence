package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

public abstract class _MappedShEntity_Persister<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_Persister<ENTITY, ID> {

    protected _MappedShEntity_Persister(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final ENTITY entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
