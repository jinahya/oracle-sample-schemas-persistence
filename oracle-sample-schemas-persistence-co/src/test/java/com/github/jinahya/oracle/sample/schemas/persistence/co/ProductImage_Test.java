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

import com.github.jinahya.oracle.sample.schemas.persistence.test._Test;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the {@link ProductImage} embeddable class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class ProductImage_Test extends _Test<ProductImage> {

    ProductImage_Test() {
        super(ProductImage.class);
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * Does nothing; {@link ProductImage} is not a class {@code EqualsVerifier} has anything to verify.
     *
     * @implNote Neither {@link ProductImage} nor {@link _Binary}, which holds its state, declares
     * {@code equals(Object)}, so instances carry {@link Object}'s reference equality. There is no value equality to
     * verify, and suppressing every check that follows from that would leave the test asserting nothing anyway.
     */
    @Override
    @Test
    protected void equals_verify_() {
        // empty
    }
}
