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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A naive test of the {@link Customer} class, written out in full, with no test base.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Customer_Test
 */
class Naive_Customer_Test {

    // -------------------------------------------------------------------------------------------------------- toString

    @DisplayName("toString() of a constructed instance is not blank")
    @Test
    void toString_NotBlank_Constructed() {
        final var instance = new Customer();
        assertThat(instance.toString()).isNotBlank();
    }

    @DisplayName("toString() of a randomized instance is not blank")
    @Test
    void toString_NotBlank_Randomized() {
        final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(Customer.class).orElseThrow();
        assertThat(instance.toString()).isNotBlank();
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    @DisplayName("equals/hashCode compare by emailAddress only")
    @Test
    void equals_verify() {
        EqualsVerifier.simple().forClass(Customer.class)
                .withOnlyTheseFields(Customer.ATTRIBUTE_NAME_EMAIL_ADDRESS)
                .verify();
    }
}
