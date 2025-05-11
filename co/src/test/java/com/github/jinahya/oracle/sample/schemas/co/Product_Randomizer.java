package com.github.jinahya.oracle.sample.schemas.co;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_Randomizer;
import com.github.jinahya.oracle.sample.schemas.___Base_Randomizer_Utils;
import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Product_Randomizer extends __MappedEntity_Randomizer<Product> {

    Product_Randomizer() {
        super(Product.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy dataProviderStrategy() {
        return super.dataProviderStrategy()
                .addOrReplaceAttributeStrategy(entityClass, "productId", (t, a) -> {
                    return null;
                })
                .addOrReplaceAttributeStrategy(BigDecimal.class, "unitPrice", (t, a) -> {
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        return null;
                    } else {
                        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1000, 10000));
                    }
                })
                ;
    }

    @Override
    protected PodamFactory podamFactory() {
        return super.podamFactory();
    }

    @Override
    protected ClassInfoStrategy classInfoStrategy() {
//        return super.classInfoStrategy();
        return new AbstractClassInfoStrategy() {
            // https://github.com/mtedone/podam/pull/84
            @Override
            public boolean approve(final ClassAttribute attribute) {
                return !Objects.equals(attribute.getName(), "productId");
            }
        };
    }

    @Override
    protected Product manufacturePojo() {
        final var pojo = super.manufacturePojo();
        assertThat(pojo.getProductId()).isNull();
        pojo.setUnitPrice(
                ThreadLocalRandom.current().nextBoolean()
                ? null
                : BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(.0d, 10000.d))
        );
        pojo.setProductDetails(
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
        return pojo;
    }
}