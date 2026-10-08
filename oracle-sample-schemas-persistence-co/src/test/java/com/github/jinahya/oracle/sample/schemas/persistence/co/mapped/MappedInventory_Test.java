package com.github.jinahya.oracle.sample.schemas.persistence.co.mapped;

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

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A class for testing the {@link MappedInventory} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class MappedInventory_Test {

    /**
     * A concrete extension, standing in for an entity.
     */
    private static class Concrete extends MappedInventory {
    }

    /**
     * Stands in for a lazy proxy: its own fields are never populated, and it reads through to a target by overriding
     * the {@code protected} getters, the way a provider's proxy does.
     */
    private static class Proxy extends Concrete {

        private Proxy(final MappedInventory target) {
            super();
            this.target = target;
        }

        @Override
        protected Long getStoreId() {
            return target.getStoreId();
        }

        @Override
        protected Long getProductId() {
            return target.getProductId();
        }

        private final MappedInventory target;
    }

    /**
     * Returns a new instance whose read-only attributes are set as a load would set them.
     */
    private static Concrete loaded(final Long storeId, final Long productId) {
        final var instance = new Concrete();
        try {
            final var storeIdField = MappedInventory.class.getDeclaredField(MappedInventory.ATTRIBUTE_NAME_STORE_ID);
            storeIdField.setAccessible(true);
            storeIdField.set(instance, storeId);
            final var productIdField = MappedInventory.class.getDeclaredField(
                    MappedInventory.ATTRIBUTE_NAME_PRODUCT_ID);
            productIdField.setAccessible(true);
            productIdField.set(instance, productId);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
        return instance;
    }

    // ------------------------------------------------------------------------------------------------- equals/hashCode

    /**
     * Verifies {@link MappedInventory#equals(Object)} and {@link MappedInventory#hashCode()}.
     *
     * @implNote Equality is by the business key ({@code storeId}, {@code productId}), not by the {@code @Id}, and an
     * instance missing either equals itself only, while the hash is constant. That takes three warnings suppressed:
     * {@link Warning#STRICT_HASHCODE} for the constant hash, {@link Warning#IDENTICAL_COPY_FOR_VERSIONED_ENTITY} for an
     * unloaded instance equalling itself only, and {@link Warning#ALL_FIELDS_SHOULD_BE_USED} for the fields outside
     * the key.
     */
    @Test
    void equals_verify_() {
        EqualsVerifier.simple().forClass(MappedInventory.class)
                .suppress(Warning.STRICT_HASHCODE,
                          Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY,
                          Warning.ALL_FIELDS_SHOULD_BE_USED)
                .verify();
    }

    @Nested
    class Unloaded {

        @Test
        void equals_True_Itself() {
            final var instance = new Concrete();
            assertThat(instance).isEqualTo(instance);
        }

        @Test
        void equals_False_AnotherUnloaded() {
            assertThat(new Concrete()).isNotEqualTo(new Concrete());
        }

        @Test
        void equals_False_Loaded() {
            final var unloaded = new Concrete();
            final var loaded = loaded(1L, 2L);
            assertThat(unloaded).isNotEqualTo(loaded);
            assertThat(loaded).isNotEqualTo(unloaded);
        }

        @Test
        void hashSet_KeepsEach() {
            final var set = new HashSet<MappedInventory>();
            assertThat(set.add(new Concrete())).isTrue();
            assertThat(set.add(new Concrete())).isTrue();
            assertThat(set).hasSize(2);
        }
    }

    @Nested
    class Loaded {

        @Test
        void equals_True_SameKey() {
            final var a = loaded(1L, 2L);
            final var b = loaded(1L, 2L);
            assertThat(a).isEqualTo(b);
            assertThat(b).isEqualTo(a);
            assertThat(a).hasSameHashCodeAs(b);
        }

        @Test
        void equals_False_DifferentKey() {
            assertThat(loaded(1L, 2L)).isNotEqualTo(loaded(1L, 3L));
            assertThat(loaded(1L, 2L)).isNotEqualTo(loaded(4L, 2L));
        }

        @Test
        void equals_False_PartialKey() {
            assertThat(loaded(1L, null)).isNotEqualTo(loaded(1L, null));
            assertThat(loaded(null, 2L)).isNotEqualTo(loaded(null, 2L));
        }
    }

    @Nested
    class Proxied {

        @Test
        void equals_True_BothWays() {
            final var target = loaded(1L, 2L);
            final var proxy = new Proxy(target);
            assertThat(proxy).isEqualTo(target);
            assertThat(target).isEqualTo(proxy);
            assertThat(proxy).isEqualTo(loaded(1L, 2L));
            assertThat(loaded(1L, 2L)).isEqualTo(proxy);
        }

        @Test
        void hashCode_Same_Target() {
            final var target = loaded(1L, 2L);
            assertThat(new Proxy(target)).hasSameHashCodeAs(target);
        }
    }
}
