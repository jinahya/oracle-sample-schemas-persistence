package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class maps to {@value _MappedStore#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Store.selectListWhereStoreNameIn",
        query = """
                SELECT e
                FROM Store AS e
                WHERE e.storeName IN :storeNames"""
)
@NamedQuery(
        name = "Store.selectListWhereStoreNameLike",
        query = """
                SELECT e
                FROM Store AS e
                WHERE e.storeName LIKE :storeNamePattern"""
)
@NamedQuery(
        name = "Store.selectSingleWhereStoreNameEqual",
        query = """
                SELECT e
                FROM Store AS e
                WHERE e.storeName = :storeName"""
)
@Entity
@Table(name = Store.TABLE_NAME)
public class Store extends _MappedStore<Store> {

    protected Store() {
        super();
    }
}
