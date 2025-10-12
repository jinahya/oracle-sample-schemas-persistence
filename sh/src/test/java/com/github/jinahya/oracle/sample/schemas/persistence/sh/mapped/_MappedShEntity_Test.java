package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Test;
import jakarta.annotation.Nonnull;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

public abstract class _MappedShEntity_Test<ENTITY extends _MappedShEntity<ID>, ID>
        extends __MappedEntity_Test<ENTITY, ID> {

    protected _MappedShEntity_Test(final Class<ENTITY> entityClass, final Class<ID> idClass) {
        super(entityClass, idClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected SingleTypeEqualsVerifierApi<ENTITY> equals_Verify_(
            @Nonnull final SingleTypeEqualsVerifierApi<ENTITY> equalsVerifier) {
        return super.equals_Verify_(equalsVerifier);
    }
}
