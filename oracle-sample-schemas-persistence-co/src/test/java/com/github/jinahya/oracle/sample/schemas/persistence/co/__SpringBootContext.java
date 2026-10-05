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

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.persistenceunit.PersistenceManagedTypes;

/**
 * The Spring Boot configuration which {@code CO}'s Spring tests start from.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @apiNote Declares no component scan; the only bean is the list of managed types.
 */
@EnableAutoConfiguration
@SpringBootConfiguration
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class __SpringBootContext {

    /**
     * Returns the managed types of the persistence unit, which replace the ones Spring Boot would scan for.
     *
     * @return the managed types of the persistence unit.
     * @implNote Lists what {@code META-INF/persistence.xml} lists, for the same reason it lists them: {@code co} maps
     * {@code ORDER_ITEMS} and {@code PRODUCT_ORDERS} two ways each, under one entity name, and a unit with both
     * flavours is not a working unit. A scan of the package would find both.
     */
    @Bean
    PersistenceManagedTypes persistenceManagedTypes() {
        return PersistenceManagedTypes.of(
                Customer.class.getName(),
                Inventory.class.getName(),
                Order.class.getName(),
                OrderItemWithEmbeddedId.class.getName(),
                Product.class.getName(),
                Shipment.class.getName(),
                Store.class.getName(),
                CustomerOrderProduct.class.getName(),
                ProductOrderId.class.getName(),
                ProductOrder.class.getName()
        );
    }
}
