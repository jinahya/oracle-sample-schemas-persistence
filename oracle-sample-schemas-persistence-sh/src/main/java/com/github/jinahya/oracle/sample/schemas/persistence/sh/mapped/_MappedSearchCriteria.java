package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/**
 * A marker interface for the search criteria of a class which maps a table or a view of the {@code SH} schema.
 *
 * @param <T> the type of the mapped class.
 * @param <U> the type of the identifier of {@code T}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public interface _MappedSearchCriteria<T extends _MappedDomainEntity<U>, U> {

}
