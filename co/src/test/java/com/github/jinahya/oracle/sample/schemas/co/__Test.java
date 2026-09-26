package com.github.jinahya.oracle.sample.schemas.co;

import java.util.Objects;

abstract class __Test<T> {

    __Test(final Class<T> persistenceClass) {
        super();
        this.persistenceClass = Objects.requireNonNull(persistenceClass, "persistenceClass is null");
    }

    final Class<T> persistenceClass;
}
