package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilderTest;

class CustomerBuilder_Test extends __MappedEntityBuilderTest<CustomerBuilder, Customer, Long> {

    CustomerBuilder_Test() {
        super(CustomerBuilder.class, Customer.class, Long.class);
    }
}
