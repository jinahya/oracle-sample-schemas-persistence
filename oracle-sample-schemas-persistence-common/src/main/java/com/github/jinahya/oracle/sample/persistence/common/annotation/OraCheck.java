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
 * Documents a {@code CHECK} constraint of the table the annotated class maps, or of the column the
 * annotated attribute maps.
 *
 * @deprecated Jakarta Persistence 3.2 covers it; use a {@link jakarta.persistence.CheckConstraint
 *             @CheckConstraint} in {@link jakarta.persistence.Table#check() @Table(check = ...)} or
 *             {@link jakarta.persistence.Column#check() @Column(check = ...)}.
 *
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/constraint.html">constraint</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/CheckConstraint.html">jakarta.persistence.CheckConstraint</a>
 */
@Deprecated(forRemoval = true)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(OraCheck.List.class)
public @interface OraCheck {

    /**
     * The name of the constraint, {@code emp_salary_min} say; empty if the constraint is
     * system-named.
     *
     * @return the name of the constraint, {@code emp_salary_min} say; empty if the constraint is
     *         system-named.
     */
    String name() default "";

    /**
     * The condition of the constraint, as the database defines it, {@code salary > 0} say.
     *
     * @return the condition of the constraint, as the database defines it, {@code salary > 0} say.
     */
    String value();

    /**
     * Holds the repeated {@link OraCheck @OraCheck} annotations of an element.
     */
    @Deprecated(forRemoval = true)
    @Documented
    @Target({ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    @interface List {

        /**
         * Returns the repeated annotations.
         *
         * @return the repeated annotations.
         */
        OraCheck[] value();
    }
}
