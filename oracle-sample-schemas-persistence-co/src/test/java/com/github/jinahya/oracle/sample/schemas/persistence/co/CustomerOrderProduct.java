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

import com.github.jinahya.oracle.sample.schemas.persistence.co.mapped.MappedCustomerOrderProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value MappedCustomerOrderProduct#TABLE_NAME} view.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "CustomerOrderProduct.selectOneByOrderId",
            query = """
                    SELECT e
                    FROM CustomerOrderProduct e
                    WHERE e.orderId = :orderId"""
)
@NamedQuery(name = "CustomerOrderProduct.selectListOrderByOrderIdAsc",
            query = """
                    SELECT e
                    FROM CustomerOrderProduct e
                    ORDER BY e.orderId ASC"""
)
@NamedQuery(name = "CustomerOrderProduct.selectListOrderByOrderIdAscOrderIdGt",
            query = """
                    SELECT e
                    FROM CustomerOrderProduct e
                    WHERE e.orderId > :orderIdMinExclusive
                    ORDER BY e.orderId ASC"""
)
@NamedQuery(name = "CustomerOrderProduct.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM CustomerOrderProduct e"""
)
@Entity
@Table(name = MappedCustomerOrderProduct.TABLE_NAME)
public class CustomerOrderProduct
        extends MappedCustomerOrderProduct {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CustomerOrderProduct() {
        super();
    }
}
