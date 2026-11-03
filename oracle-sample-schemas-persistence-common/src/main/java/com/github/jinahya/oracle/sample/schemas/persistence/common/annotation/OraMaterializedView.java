package com.github.jinahya.oracle.sample.schemas.persistence.common.annotation;

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
 * Documents that the view the annotated class maps is a materialized view: the database stores the
 * result of its query, and may rewrite other queries to read it.
 * <p>
 * Jakarta Persistence has no annotation for a materialized view; it maps one as it maps a table.
 *
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/CREATE-MATERIALIZED-VIEW.html">CREATE MATERIALIZED VIEW</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface OraMaterializedView {

    /**
     * Whether query rewrite is enabled ({@code ENABLE QUERY REWRITE}): the optimizer may answer a query
     * on the base tables from the materialized view.
     *
     * @return whether query rewrite is enabled ({@code ENABLE QUERY REWRITE}): the optimizer may answer a
     *         query on the base tables from the materialized view.
     */
    boolean queryRewrite() default false;

    /**
     * The refresh clause of the materialized view, {@code REFRESH FAST ON COMMIT} say; empty for the
     * default.
     *
     * @return the refresh clause of the materialized view, {@code REFRESH FAST ON COMMIT} say; empty for
     *         the default.
     */
    String refresh() default "";
}
