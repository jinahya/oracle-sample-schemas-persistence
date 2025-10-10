package com.github.jinahya.oracle.sample.schemas.sh.mapped;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;

public abstract class __MappedShEntityBuilder<
        SELF extends __MappedShEntityBuilder<SELF, TARGET>,
        TARGET extends __MappedShEntity<?>
        >
        extends __MappedEntityBuilder<SELF, TARGET> {

    protected __MappedShEntityBuilder(final Class<TARGET> targetClass) {
        super(targetClass);
    }
}
