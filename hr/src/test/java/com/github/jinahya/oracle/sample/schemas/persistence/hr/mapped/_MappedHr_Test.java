package com.github.jinahya.oracle.sample.schemas.persistence.hr.mapped;

import com.github.jinahya.persistence.mapped.test.__Mapped_Test;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public abstract class _MappedHr_Test<MAPPED extends _MappedHr> extends __Mapped_Test<MAPPED> {

    protected _MappedHr_Test(final Class<MAPPED> mappedClass) {
        super(mappedClass);
    }
}
