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

/**
 * Jakarta Persistence mappings for the {@code SH} (Sales History) schema of the Oracle Database Sample Schemas.
 * <p>
 * The persistence and validation APIs appear in the entities' public signatures, so a reader of this module reads them
 * too. So do the nullness annotations of {@code jakarta.annotation}, but that one is also static: they are not needed
 * at run time.
 * <p>
 * Both packages are opened, not only exported, because a persistence provider and a validation provider reach the
 * entities' private fields reflectively; neither can be named here, since the provider is the consumer's choice.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
module com.github.jinahya.oracle.sample.schemas.persistence.sh {

    requires static transitive jakarta.annotation;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;

    exports com.github.jinahya.oracle.sample.schemas.persistence.sh;

    opens com.github.jinahya.oracle.sample.schemas.persistence.sh;
}
