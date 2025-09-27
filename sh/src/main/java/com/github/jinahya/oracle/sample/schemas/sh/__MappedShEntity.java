package com.github.jinahya.oracle.sample.schemas.sh;

import com.github.jinahya.persistence.mapped.__MappedEntity;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class __MappedShEntity<ID> implements __MappedEntity<ID> {

    protected __MappedShEntity() {
        super();
    }
}
