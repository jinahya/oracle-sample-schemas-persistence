package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.__MappedBuilder;

public abstract class __MappedShBuilder<
        SELF extends __MappedShBuilder<SELF, TARGET>,
        TARGET extends __MappedSh
        >
        extends __MappedBuilder<SELF, TARGET> {

    protected __MappedShBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }
}
