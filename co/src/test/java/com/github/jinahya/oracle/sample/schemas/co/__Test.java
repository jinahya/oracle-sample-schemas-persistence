package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.test.util.__InstantiatorUtils;
import com.github.jinahya.persistence.test.util.__RandomizerUtils;

import java.util.Objects;
import java.util.Optional;

abstract class __Test<T> {

    __Test(final Class<T> targetClass) {
        super();
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    T newTargetInstance() {
        return __InstantiatorUtils.newInstantiatedInstanceOf(targetClass);
    }

    Optional<T> newRandomizedTargetInstance() {
        return __RandomizerUtils.newRandomizedInstanceOf(targetClass);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<T> targetClass;
}
