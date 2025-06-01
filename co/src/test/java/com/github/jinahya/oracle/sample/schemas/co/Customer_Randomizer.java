package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Customer_Randomizer extends __MappedEntity_Randomizer<Customer> {

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR
    Customer_Randomizer() {
        super(Customer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceAttributeStrategy(entityClass, "customerId", (t, a) -> {
                    return null;
                });
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
//        return super.classInfoStrategy();
        return new AbstractClassInfoStrategy() {
            // https://github.com/mtedone/podam/pull/84
            @Override
            public boolean approve(final ClassAttribute attribute) {
                return !Objects.equals(attribute.getName(), "customerId");
            }
        };
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected Customer manufacturePojo() {
        log.debug("manufacturing customer...");
        final var customer = super.manufacturePojo();
        log.debug("customer: {}", customer);
        assertThat(customer.getCustomerId()).isNull();
        return customer;
    }
}