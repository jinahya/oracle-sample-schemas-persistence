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
 * Documents the {@code COMMENT} of the table, or view, the annotated class maps, or of the column the
 * annotated attribute maps.
 *
 * @deprecated Jakarta Persistence covers it; use {@link jakarta.persistence.Table#comment()
 *             @Table(comment = ...)} or {@link jakarta.persistence.Column#comment() @Column(comment = ...)}.
 *
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/COMMENT.html">COMMENT</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/Table.html#comment()">jakarta.persistence.Table#comment()</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/Column.html#comment()">jakarta.persistence.Column#comment()</a>
 */
@Deprecated(forRemoval = true)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface OraComment {

    /**
     * The comment, as the database defines it.
     *
     * @return the comment, as the database defines it.
     */
    String value();
}
