package com.github.jinahya.oracle.sample.schemas.persistence.co;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import uk.co.jemos.podam.api.ClassInfoStrategy;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.api.PodamFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * A randomizer which produces randomized {@link Product} instances.
 * <p>
 * The generated {@code productId}, and the {@code orderItems} and {@code inventories} collections, are excluded from
 * randomization; the inverse side of a one-to-many is left to whichever test needs it. The {@code productDetails} is
 * assigned after the fact, because PODAM fills it with arbitrary bytes, not a JSON document; it is set to a randomized
 * {@link ProductDetails} written as JSON.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Product_Randomizer extends PodamObjectRandomizer<Product> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // -----------------------------------------------------------------------------------------------------------------
    Product_Randomizer() {
        super(Product.class, List.of(
                Product.ATTRIBUTE_NAME_PRODUCT_ID,
                Product.ATTRIBUTE_NAME_ORDER_ITEMS,
                Product.ATTRIBUTE_NAME_INVENTORIES
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected DataProviderStrategy getDataProviderStrategy() {
        return super.getDataProviderStrategy();
    }

    @Override
    protected ClassInfoStrategy getClassInfoStrategy() {
        return super.getClassInfoStrategy();
    }

    @Override
    protected PodamFactory getPodamFactory() {
        return super.getPodamFactory();
    }

    @Override
    public Product get() {
        final var value = super.get();
        // Podam fills the attribute with arbitrary bytes, which are not a JSON document; replace them with a
        // randomized ProductDetails, written as JSON.
        try {
            value.setProductDetails(
                    ProductDetails_TestUtils.toBytes(new ProductDetails_Randomizer().get(), OBJECT_MAPPER)
            );
        } catch (final IOException ioe) {
            throw new UncheckedIOException("failed to write product details as JSON", ioe);
        }
        return value;
    }
}
