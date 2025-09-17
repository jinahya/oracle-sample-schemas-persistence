package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__Mapped_Randomizer;

abstract class _MappedCo_Randomizer<MAPPED extends _MappedCo> extends __Mapped_Randomizer<MAPPED> {

    _MappedCo_Randomizer(final Class<MAPPED> mappedClass) {
        super(mappedClass);
    }
}
