/**
 * Mapped superclasses for the {@code HR} schema of the Oracle Database Sample Schemas, one per
 * table and one per view, plus one per composite identifier.
 * <p>
 * Each holds what the schema says about its table -- the names, the types, the lengths, the
 * nullability, the check constraints as validation constraints -- and nothing an application
 * decides: no association, no fetch type, no cascade, no named query. An entity which extends one
 * adds those, and names the table with {@link jakarta.persistence.Table @Table}. A foreign-key
 * column is mapped read-only here, as a basic attribute; the extending entity owns its writable
 * mapping, through an association's {@link jakarta.persistence.JoinColumn
 *
 * @JoinColumn}. The persistence provider is the entity's choice as well; the classes here
 *         are plain Jakarta Persistence.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
package com.github.jinahya.oracle.sample.persistence.hr.mapped;

/*-
 * #%L
 * hr
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
