package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityBuilder_Test;

class CustomerBuilder_Test extends __MappedEntityBuilder_Test<CustomerBuilder, Customer, Long> {

    CustomerBuilder_Test() {
        super(CustomerBuilder.class, Customer.class, Long.class);
    }
}
