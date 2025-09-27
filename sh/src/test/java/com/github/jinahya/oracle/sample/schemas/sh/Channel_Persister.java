package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Persister;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityManager;

class Channel_Persister extends __MappedEntity_Persister<Channel, Long> {

    Channel_Persister() {
        super(Channel.class, Long.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public void persist(@Nonnull final EntityManager entityManager, @Nonnull final Channel entityInstance) {
        super.persist(entityManager, entityInstance);
    }
}
