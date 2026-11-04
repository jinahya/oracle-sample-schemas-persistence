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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents how the database stores the table the annotated class maps: its organization, its
 * partitioning, and its compression.
 * <p>
 * Jakarta Persistence has no annotation for storage;
 * {@link jakarta.persistence.Table#options() options} only carries DDL text.
 *
 * @see <a
 *         href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/CREATE-TABLE
 *         .html">CREATE
 *         TABLE</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface OraStorage {

    /**
     * How the rows of a table are organized.
     */
    enum Organization {

        /**
         * {@code ORGANIZATION HEAP}, the default; the rows are stored in no particular order.
         */
        HEAP,

        /**
         * {@code ORGANIZATION INDEX}; the rows are stored in an index on the primary key.
         */
        INDEX,

        /**
         * {@code ORGANIZATION EXTERNAL}; the rows are read from files outside the database.
         */
        EXTERNAL
    }

    /**
     * A method of partitioning a table.
     */
    enum Partitioning {

        /**
         * Not partitioned.
         */
        NONE,

        /**
         * {@code PARTITION BY RANGE}.
         */
        RANGE,

        /**
         * {@code PARTITION BY LIST}.
         */
        LIST,

        /**
         * {@code PARTITION BY HASH}.
         */
        HASH
    }

    /**
     * The organization of the table.
     *
     * @return the organization of the table.
     */
    Organization organization() default Organization.HEAP;

    /**
     * The method the table is partitioned by.
     *
     * @return the method the table is partitioned by.
     */
    Partitioning partitioning() default Partitioning.NONE;

    /**
     * The columns of the partitioning key, {@code time_id} say; empty if the table is not
     * partitioned.
     *
     * @return the columns of the partitioning key, {@code time_id} say; empty if the table is not
     *         partitioned.
     */
    String[] partitionColumns() default {};

    /**
     * The compression of the table, or of its partitions, {@code COMPRESS} say; empty if none.
     *
     * @return the compression of the table, or of its partitions, {@code COMPRESS} say; empty if
     *         none.
     */
    String compression() default "";
}
