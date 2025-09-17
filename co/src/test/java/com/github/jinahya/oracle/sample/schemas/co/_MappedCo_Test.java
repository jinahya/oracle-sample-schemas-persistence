package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Mapped_Test;

abstract class _MappedCo_Test<MAPPED extends _MappedCo> extends __Mapped_Test<MAPPED> {

    _MappedCo_Test(final Class<MAPPED> mappedClass) {
        super(mappedClass);
    }
}
