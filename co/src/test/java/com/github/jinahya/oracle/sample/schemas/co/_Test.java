package com.github.jinahya.oracle.sample.schemas.co;

import org.junit.jupiter.api.Test;

abstract class _Test<T> extends __Test<T> {

    _Test(final Class<T> persistenceClass) {
        super(persistenceClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void _NotBlank_toString() {
    }
}
