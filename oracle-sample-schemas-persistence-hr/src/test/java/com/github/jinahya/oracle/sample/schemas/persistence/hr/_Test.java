package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.oracle.sample.schemas.persistence.test.__Test;

abstract class _Test<T> extends __Test<T> {

    _Test(final Class<T> targetClass) {
        super(targetClass);
    }
}
