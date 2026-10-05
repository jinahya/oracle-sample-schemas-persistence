package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

import com.github.jinahya.oracle.sample.schemas.persistence.sh._DomainConstants;

/**
 * The superclass of the constants shared by the classes of the {@code SH} schema; it permits only
 * {@link _DomainConstants}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public sealed abstract class _MappedDomainConstants permits _DomainConstants {

    /**
     * Creates a new instance.
     */
    protected _MappedDomainConstants() {
        super();
    }
}
