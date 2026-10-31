/**
 * Mapped superclasses for the {@code SH} schema of the Oracle Database Sample Schemas, one per table and one per
 * view, plus one per composite identifier, with the embeddables and converters which carry no association.
 * <p>
 * Each holds what the schema says about its table -- the names, the types, the lengths, the nullability, the check
 * constraints as validation constraints -- and nothing an application decides: no association, no fetch type, no
 * cascade, no named query. An entity which extends one adds those, and names the table with
 * {@link jakarta.persistence.Table @Table}. The persistence provider is the entity's choice as well; the classes here
 * are plain Jakarta Persistence.
 * <p>
 * A converter here is applied only where an attribute names it with {@link jakarta.persistence.Convert @Convert}; a
 * persistence unit which excludes unlisted classes has to list it.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://github.com/oracle-samples/db-sample-schemas">Oracle Database Sample Schemas</a>
 */
package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

/*-
 * #%L
 * sh
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
