/**
 * Mapped superclasses for the {@code CO} schema, one per table and one per view, plus one per
 * composite identifier.
 * <p>
 * Each holds what the schema says about its table -- the names, the types, the lengths, the
 * nullability, the check constraints as validation constraints -- and nothing an application
 * decides: no association, no fetch type, no cascade, no named query. An entity which extends one
 * adds those, and names the table with {@link jakarta.persistence.Table @Table}. A foreign-key
 * column is mapped here read-only ({@code insertable = false, updatable = false}); the extending
 * entity owns its writable mapping, by an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 * <p>
 * The embeddables and the value types which hold no association -- {@link _Binary},
 * {@link ProductImage}, {@link StoreLogo}, {@link ProductDetails} -- are here as they are. A view
 * no column identifies is mapped by a plain class, not by a mapped superclass; see
 * {@link MappedProductReview} and {@link MappedStoreOrder}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
package com.github.jinahya.oracle.sample.persistence.co.mapped;

/*-
 * #%L
 * co
 * %%
 * Copyright (C) 2024 - 2025 Jinahya, Inc.
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
