package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_Randomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Customer_Randomizer extends __MappedEntity_Randomizer<Customer, Long> {

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
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Nonnull
    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Nonnull
    @Override
    public Customer get() {
        final var value = super.get();
        {
            value.setEmailAddress(System.nanoTime() + "@" + System.nanoTime() + ".com");
        }
        return value;
    }
}
