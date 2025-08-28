package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityBuilder;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@SuppressWarnings({
        "unchecked"
})
public abstract class MappedOrderBuilder<
        SELF extends MappedOrderBuilder<SELF, ORDER, CUSTOMER, STORE>,
        ORDER extends MappedOrder<CUSTOMER, STORE, ?>,
        CUSTOMER extends MappedCustomer,
        STORE extends MappedStore
        >
        extends __MappedEntityBuilder<SELF, ORDER> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderBuilder(final Class<ORDER> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------------------- orderId
    @Deprecated(forRemoval = true)
    public Long orderId() {
        return orderId;
    }

    @Deprecated(forRemoval = true)
    public SELF orderId(Long orderId) {
        this.orderId = orderId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- orderTms
    public LocalDateTime orderTms() {
        return orderTms;
    }

    SELF orderTms(final LocalDateTime orderTms) {
        this.orderTms = orderTms;
        return (SELF) this;
    }

    // ------------------------------------------------------------------------------------------------------ customerId
    @Deprecated(forRemoval = true)
    public Long customerId() {
        return customerId;
    }

    @Deprecated(forRemoval = true)
    public SELF customerId(final Long customerId) {
        this.customerId = customerId;
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public CUSTOMER customer() {
        return customer;
    }

    public SELF customer(final CUSTOMER customer) {
        this.customer = customer;
        return (SELF) this;
    }

    // ----------------------------------------------------------------------------------------------------- orderStatus

    public String orderStatus() {
        return orderStatus;
    }

    public SELF orderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- storeId
    @Deprecated(forRemoval = true)
    public Long storeId() {
        return storeId;
    }

    @Deprecated(forRemoval = true)
    public SELF storeId(final Long storeId) {
        this.storeId = storeId;
        return (SELF) this;
    }

    // --------------------------------------------------------------------------------------------------------- store
    public STORE store() {
        return store;
    }

    public SELF store(final STORE store) {
        this.store = store;
        return (SELF) this;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long orderId;

    private LocalDateTime orderTms;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long customerId;

    private CUSTOMER customer;

    // -----------------------------------------------------------------------------------------------------------------
    private String orderStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @Deprecated(forRemoval = true)
    private Long storeId;

    private STORE store;
}
