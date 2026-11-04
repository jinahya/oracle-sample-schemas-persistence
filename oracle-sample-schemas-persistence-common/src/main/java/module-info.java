/*-
 * #%L
 * oracle-sample-schemas-persistence-common
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
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

/**
 * What the schema modules of Jakarta Persistence mappings for the Oracle Database Sample Schemas
 * share.
 * <p>
 * The annotations of the {@code annotation} package document what the DDL declares and Jakarta
 * Persistence cannot express. They are retained in the source only, so a reader of this module
 * needs it at compile time alone; the persistence API is read for the types their documentation
 * refers to, and is static for the same reason.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
module com.github.jinahya.oracle.sample.persistence.common {

    requires static jakarta.persistence;
    requires static jakarta.validation;

    exports com.github.jinahya.oracle.sample.persistence.common.annotation;
    exports com.github.jinahya.oracle.sample.persistence.common;

    exports com.github.jinahya.oracle.sample.persistence.common.mapped;
    opens com.github.jinahya.oracle.sample.persistence.common.mapped;
}
