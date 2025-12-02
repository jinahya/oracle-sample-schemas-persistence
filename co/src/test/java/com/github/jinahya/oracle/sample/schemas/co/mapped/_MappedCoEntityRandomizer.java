package com.github.jinahya.oracle.sample.schemas.co.mapped;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;

public class _MappedCoEntityRandomizer<MAPPED extends _MappedCoEntity<ID>, ID>
        extends __MappedEntity_Randomizer<MAPPED, ID> {

    protected _MappedCoEntityRandomizer(final Class<MAPPED> mappedClass, final Class<ID> idClass,
                                        final Iterable<String> excludedFields) {
        super(mappedClass, idClass, excludedFields);
    }
}
