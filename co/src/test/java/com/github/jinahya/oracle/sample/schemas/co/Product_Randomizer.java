package com.github.jinahya.oracle.sample.schemas.co;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.___Base_Randomizer_Utils;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Product_Randomizer extends __MappedEntity_Randomizer<Product> {

    Product_Randomizer() {
        super(Product.class,
              "productId",
//              "unitPrice",
              "productDetails",
              "productImage", "productMimeType", "productFilename", "productCharset", "imageLastUpdated"
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
        return super.classInfoStrategy();
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected Product manufacturePojo() {
        log.debug("manufacturing product...");
        final var product = super.manufacturePojo();
        log.debug("product manufactured: {}", product);
        assertThat(product.getProductId()).isNull();
//        assertThat(product.getUnitPrice()).isNull();
        product.setUnitPrice(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(.0d, 10000.d))
        );
        product.setProductDetails(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : ___Base_Randomizer_Utils.newRandomizedInstanceOf(ProductDetails.class).map(v -> {
                            try {
                                return ProductDetails_TestUtils.toBytes(v, new ObjectMapper());
                            } catch (final IOException ioe) {
                                throw new RuntimeException(ioe);
                            }
                        })
                        .orElse(null)
        );
        return product;
    }
}