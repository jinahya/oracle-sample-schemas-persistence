package com.github.jinahya.oracle.sample.schemas.co;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity class for mapping {@value Inventory#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(
        name = "Inventory.selectListWhereProductEqual",
        query = """
                SELECT e
                FROM Inventory AS e
                WHERE e.product = :product
                """
)
@NamedQuery(
        name = "Inventory.selectSingleWhereStoreEqualAndProductEqual",
        query = """
                SELECT e
                FROM Inventory AS e
                WHERE e.store = :store AND e.product = :product"""
)
@NamedQuery(
        name = "Inventory.selectListWhereStoreEqualOrderByProductInventoryAsc",
        query = """
                SELECT e
                FROM Inventory AS e
                WHERE e.store = :store
                ORDER BY e.productInventory ASC
                """
)
@NamedQuery(
        name = "Inventory.selectListWhereStoreEqual",
        query = """
                SELECT e
                FROM Inventory AS e
                WHERE e.store = :store"""
)
@Entity
@Table(
        name = Inventory.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                _MappedInventory.COLUMN_NAME_STORE_ID,
                                _MappedInventory.COLUMN_NAME_PRODUCT_ID
                        }
                )
        }
)
public class Inventory extends _MappedInventory<Inventory> {

    protected Inventory() {
        super();
    }
}
