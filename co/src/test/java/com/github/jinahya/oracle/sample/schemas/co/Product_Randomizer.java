package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.persistence.mapped.test.__MappedEntityRandomizer;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

@Slf4j
class Product_Randomizer extends __MappedEntityRandomizer<Product, Long> {

    Product_Randomizer() {
        super(Product.class,
              Long.class,
              "productId",
//              "unitPrice",
              "productDetails",
              "productImage", "productMimeType", "productFilename", "productCharset", "imageLastUpdated"
        );
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
    public Product get() {
        return super.get();
    }
}
