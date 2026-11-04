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

/**
 * Jakarta Persistence mappings for the {@code HR} (Human Resources) schema of the Oracle Database
 * Sample Schemas.
 * <p>
 * The module holds mapped superclasses, one per table and view, which an application extends with
 * its own entities. The persistence and validation APIs appear in the mapped superclasses' public
 * signatures, so a reader of this module reads them too. So do the nullness annotations of
 * {@code jakarta.annotation}, but that one is also static: they are not needed at run time.
 * <p>
 * The package is opened, not only exported, because a persistence provider and a validation
 * provider reach the mapped superclasses' private fields reflectively; neither can be named here,
 * since the provider is the consumer's choice.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
module com.github.jinahya.oracle.sample.persistence.hr {

    requires static com.github.jinahya.oracle.sample.persistence.common;
    requires static transitive jakarta.annotation;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;

    exports com.github.jinahya.oracle.sample.persistence.hr.mapped;

    opens com.github.jinahya.oracle.sample.persistence.hr.mapped;
}
