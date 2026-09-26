package com.github.jinahya.oracle.sample.schemas.co;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Boots the persistence unit against the in-memory database and checks that the provider accepts every mapping in the
 * {@code CO} schema.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class PersistenceUnit_Test {

    @DisplayName("the persistence unit bootstraps")
    @Test
    void entityManagerFactory_IsOpen_() {
        assertThat(_Persistence_Test_Utils.entityManagerFactory().isOpen()).isTrue();
    }

    @DisplayName("every mapped entity reaches the metamodel with an id")
    @Test
    void metamodel_HasEveryEntityWithAnId_() {
        final var entities = _Persistence_Test_Utils.entityManagerFactory().getMetamodel().getEntities();
        assertThat(entities)
                .as("entity types of %s", _Persistence_Test_Utils.PERSISTENCE_UNIT_NAME)
                .isNotEmpty()
                .allSatisfy(e -> assertThat(e.hasSingleIdAttribute() || !e.getIdClassAttributes().isEmpty())
                        .as("%s has an id", e.getName())
                        .isTrue());
    }

    @DisplayName("the schema generated from the mappings is queryable")
    @Test
    void generatedSchema_IsQueryable_() {
        _Persistence_Test_Utils.entityManagerFactory().getMetamodel().getEntities().forEach(e -> {
            final var count = _Persistence_Test_Utils.applyEntityManagerInRolledBackTransaction(
                    em -> em.createQuery("SELECT COUNT(x) FROM " + e.getName() + " x", Long.class)
                            .getSingleResult());
            assertThat(count).as("row count of %s", e.getName()).isNotNegative();
        });
    }
}
