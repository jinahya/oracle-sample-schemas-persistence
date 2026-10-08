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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import jakarta.persistence.EntityManager;

/**
 * A persister which persists {@link Product} instances.
 * <p>
 * A product references nothing, so an instance is persisted as it arrives.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class Product_Persister extends AbstractEntityPersister<Product> {

    Product_Persister() {
        super(Product.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Product apply(final EntityManager entityManager, final Product entityInstance) {
        return super.apply(entityManager, entityInstance);
    }
}
