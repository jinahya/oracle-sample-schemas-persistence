package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.___Base_Randomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.util.List;

class ProductDetails_Randomizer extends ___Base_Randomizer<ProductDetails> {

    ProductDetails_Randomizer() {
        super(ProductDetails.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected ProductDetails manufacturePojo() {
        final var manufacturedPojo = super.manufacturePojo();
        manufacturedPojo.setSizes(List.of(1, 2));
        return manufacturedPojo;
    }
}