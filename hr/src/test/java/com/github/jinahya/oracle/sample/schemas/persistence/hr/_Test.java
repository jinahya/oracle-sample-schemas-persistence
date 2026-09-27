package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

abstract class _Test<T> extends __Test<T> {

    _Test(final Class<T> persistenceClass) {
        super(persistenceClass);
    }

    // ------------------------------------------------------------------------------------------------ equals/hashCode
    SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return EqualsVerifier.simple().forClass(persistenceClass);
    }

    @Test
    void equals_verify_() {
        final var verifier = equals_verifier_();
        verifier.verify();
    }
}
