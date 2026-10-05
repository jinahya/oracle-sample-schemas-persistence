package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

/**
 * A superclass for the search criteria of {@link MappedInventory}, the {@value MappedInventory#TABLE_NAME} table.
 *
 * @param <T> the type of the mapped class.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class MappedInventorySearchCriteria<T extends MappedInventory>
        implements __MappedSearchCriteria<T, Long> {

    /**
     * Creates a new instance.
     */
    protected MappedInventorySearchCriteria() {
        super();
    }
}
