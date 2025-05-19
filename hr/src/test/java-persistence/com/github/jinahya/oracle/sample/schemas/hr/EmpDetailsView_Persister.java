package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Persister;
import jakarta.persistence.EntityManager;

@Deprecated(forRemoval = true)
class EmpDetailsView_Persister extends __MappedEntity_Persister<EmpDetailsView> {

    EmpDetailsView_Persister() {
        super(EmpDetailsView.class);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected EmpDetailsView persist(final EntityManager entityManager, final EmpDetailsView entityInstance) {
        return super.persist(entityManager, entityInstance);
    }
}