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
 * Documents a DML trigger on the table the annotated class maps.
 * <p>
 * Jakarta Persistence has no annotation for a trigger.
 *
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/lnpls/CREATE-TRIGGER-statement.html">CREATE TRIGGER</a>
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/lnpls/ALTER-TRIGGER-statement.html">ALTER TRIGGER</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(OraTrigger.List.class)
public @interface OraTrigger {

    /**
     * When a trigger fires, relative to the statement which fires it.
     */
    enum Timing {

        /**
         * {@code BEFORE}; the trigger fires before the statement runs.
         */
        BEFORE,

        /**
         * {@code AFTER}; the trigger fires after the statement runs.
         */
        AFTER,

        /**
         * {@code INSTEAD OF}; the trigger runs in place of the statement, on a view.
         */
        INSTEAD_OF
    }

    /**
     * A data modification statement which fires a trigger.
     */
    enum Event {

        /**
         * {@code INSERT}.
         */
        INSERT,

        /**
         * {@code UPDATE}, or {@code UPDATE OF} the {@link #columns() columns}.
         */
        UPDATE,

        /**
         * {@code DELETE}.
         */
        DELETE
    }

    /**
     * The name of the trigger, {@code update_job_history} say.
     *
     * @return the name of the trigger, {@code update_job_history} say.
     */
    String name();

    /**
     * When the trigger fires.
     *
     * @return when the trigger fires.
     */
    Timing timing();

    /**
     * The statements which fire the trigger.
     *
     * @return the statements which fire the trigger.
     */
    Event[] events();

    /**
     * The columns of {@code UPDATE OF}, {@code job_id} and {@code department_id} say; empty if any
     * {@code UPDATE} fires the trigger.
     *
     * @return the columns of {@code UPDATE OF}, {@code job_id} and {@code department_id} say; empty if
     *         any {@code UPDATE} fires the trigger.
     */
    String[] columns() default {};

    /**
     * Whether the trigger is a row trigger ({@code FOR EACH ROW}), fired once per affected row, rather
     * than a statement trigger, fired once per statement.
     *
     * @return whether the trigger is a row trigger ({@code FOR EACH ROW}), fired once per affected row,
     *         rather than a statement trigger, fired once per statement.
     */
    boolean forEachRow() default false;

    /**
     * Whether the trigger is enabled; {@code false} if it is {@code DISABLE}d.
     *
     * @return whether the trigger is enabled; {@code false} if it is {@code DISABLE}d.
     */
    boolean enabled() default true;

    /**
     * Holds the repeated {@link OraTrigger @OraTrigger} annotations of an element.
     */
    @Documented
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.SOURCE)
    @interface List {

        /**
         * Returns the repeated annotations.
         *
         * @return the repeated annotations.
         */
        OraTrigger[] value();
    }
}
