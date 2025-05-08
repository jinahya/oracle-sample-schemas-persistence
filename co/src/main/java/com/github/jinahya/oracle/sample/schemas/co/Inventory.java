package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedSuperclass;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;

@Entity
@Table(
        name = Inventory.TABLE_NAME,
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                Inventory.COLUMN_NAME_STORE_ID,
                                Inventory.COLUMN_NAME_PRODUCT_ID
                        }
                )
        }
)
public class Inventory extends __MappedSuperclass<Inventory, Long> {

    @Serial
    private static final long serialVersionUID = 8267162929628231890L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "INVENTORY";

    // ---------------------------------------------------------------------------------------------------- INVENTORY_ID
    public static final String COLUMN_NAME_INVENTORY_ID = "INVENTORY_ID";

    // -------------------------------------------------------------------------------------------------------- STORE_ID
    public static final String COLUMN_NAME_STORE_ID = "STORE_ID";

    // ------------------------------------------------------------------------------------------------------ PRODUCT_ID
    public static final String COLUMN_NAME_PRODUCT_ID = "PRODUCT_ID";

    // ----------------------------------------------------------------------------------------------- PRODUCT_INVENTORY
    public static final String COLUMN_NAME_PRODUCT_INVENTORY = "PRODUCT_INVENTORY";

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // ------------------------------------------------------------------------------------------------------ super._id_

    @Override
    protected Long _id_() {
        return inventoryId;
    }

    @Override
    protected void _id_(final Long _id_) {
        inventoryId = _id_;
    }

    // ----------------------------------------------------------------------------------------------------- inventoryId

    // ----------------------------------------------------------------------------------------------------------- store

    // --------------------------------------------------------------------------------------------------------- product

    // ------------------------------------------------------------------------------------------------ productInventory

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INVENTORY_ID, nullable = false, insertable = false, updatable = false)
    private Long inventoryId;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, referencedColumnName = Store.COLUMN_NAME_STORE_ID, nullable = false,
                insertable = true, updatable = false)
    private Store store;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID, referencedColumnName = Product.COLUMN_NAME_PRODUCT_ID, nullable = false,
                insertable = true, updatable = false)
    private Product product;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_INVENTORY, nullable = false, insertable = true, updatable = true)
    private Long productInventory;
}