package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityRandomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Customer_Randomizer extends __MappedEntityRandomizer<Customer, Long> {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    Customer_Randomizer() {
        super(Customer.class, Long.class, "customerId");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    public Customer get() {
        return super.get();
    }
}
