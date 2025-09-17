package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.__MappedEntityCriteria;

@SuppressWarnings({
        "java:S119" // Type parameter names should comply with a naming convention
})
public interface MappedCustomerCriteria<ENTITY extends MappedCustomer>
        extends __MappedEntityCriteria<ENTITY, Long> {

}
