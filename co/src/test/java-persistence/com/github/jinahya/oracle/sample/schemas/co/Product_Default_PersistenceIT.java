package com.github.jinahya.oracle.sample.schemas.co;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assumptions.assumeThat;

@Slf4j
@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
class Product_Default_PersistenceIT extends Product__PersistenceIT {

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected void randomSelected__(@Nonnull final Product entity) {
        super.randomSelected__(entity);
        final var productDetails = entity.getProductDetails();
        assumeThat(productDetails).isNotNull();
        try {
            final var value = ProductDetails_TestUtils.from(productDetails, new ObjectMapper());
            log.debug("productDetails: {}", value);
        } catch (final IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}