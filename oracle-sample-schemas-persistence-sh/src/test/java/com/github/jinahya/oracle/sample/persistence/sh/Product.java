package com.github.jinahya.oracle.sample.persistence.sh;

/*-
 * #%L
 * sh
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

import com.github.jinahya.oracle.sample.persistence.sh.mapped.MappedProduct;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * An entity class for mapping the {@value Product#TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@NamedQuery(name = "Product.selectOneByProdId",
            query = """
                    SELECT e
                    FROM Product e
                    WHERE e.prodId = :prodId"""
)
@NamedQuery(name = "Product.selectListOrderByProdIdAsc",
            query = """
                    SELECT e
                    FROM Product e
                    ORDER BY e.prodId ASC"""
)
@NamedQuery(name = "Product.selectListOrderByProdIdAscProdIdGt",
            query = """
                    SELECT e
                    FROM Product e
                    WHERE e.prodId > :prodIdMinExclusive
                    ORDER BY e.prodId ASC"""
)
@NamedQuery(name = "Product.countAll",
            query = """
                    SELECT COUNT(e)
                    FROM Product e"""
)
@Entity
@Table(name = MappedProduct.TABLE_NAME)
public class Product extends MappedProduct {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Product() {
        super();
    }
}
