package com.github.jinahya.oracle.sample.persistence.common.annotation;

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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents an index of the table the annotated class maps.
 *
 * @see <a
 *         href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/CREATE-INDEX
 *         .html">CREATE
 *         INDEX</a>
 * @see <a
 *         href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta
 *         .persistence/jakarta/persistence/Index.html">jakarta.persistence.Index</a>
 * @deprecated Jakarta Persistence covers it; use an {@link jakarta.persistence.Index @Index} in
 *         {@link jakarta.persistence.Table#indexes() @Table(indexes = ...)}.
 */
@Deprecated(forRemoval = true)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(OraIndex.List.class)
public @interface OraIndex {

    /**
     * The name of the index, {@code emp_department_ix} say.
     *
     * @return the name of the index, {@code emp_department_ix} say.
     */
    String name();

    /**
     * The key columns of the index, in order.
     *
     * @return the key columns of the index, in order.
     */
    String[] columns();

    /**
     * Whether the index is unique.
     *
     * @return whether the index is unique.
     */
    boolean unique() default false;

    /**
     * Holds the repeated {@link OraIndex @OraIndex} annotations of an element.
     */
    @Deprecated(forRemoval = true)
    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @interface List {

        /**
         * Returns the repeated annotations.
         *
         * @return the repeated annotations.
         */
        OraIndex[] value();
    }
}
