package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.sh._DomainConstants;

public sealed abstract class _MappedDomainConstants permits _DomainConstants {

    protected _MappedDomainConstants() {
        super();
    }
}
