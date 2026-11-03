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
 * Documents a sequence of the database, which the annotated class or attribute uses.
 *
 * @deprecated Jakarta Persistence covers it; use a {@link jakarta.persistence.SequenceGenerator
 *             @SequenceGenerator}, whose {@code initialValue} and {@code allocationSize} are the
 *             sequence's {@code START WITH} and {@code INCREMENT BY}.
 *
 * @see <a href="https://docs.oracle.com/en/database/oracle/oracle-database/23/sqlrf/CREATE-SEQUENCE.html">CREATE SEQUENCE</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/SequenceGenerator.html">jakarta.persistence.SequenceGenerator</a>
 */
@Deprecated(forRemoval = true)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface OraSequence {

    /**
     * The name of the sequence, {@code employees_seq} say.
     *
     * @return the name of the sequence, {@code employees_seq} say.
     */
    String name();

    /**
     * The value the sequence starts with.
     *
     * @return the value the sequence starts with.
     */
    long startWith() default 1L;

    /**
     * The value the sequence is incremented by.
     *
     * @return the value the sequence is incremented by.
     */
    long incrementBy() default 1L;

    /**
     * The largest value the sequence generates; {@link Long#MAX_VALUE} for {@code NOMAXVALUE}.
     *
     * @return the largest value the sequence generates; {@link Long#MAX_VALUE} for {@code NOMAXVALUE}.
     */
    long maxValue() default Long.MAX_VALUE;

    /**
     * How many values the database preallocates in memory; {@code 0} for {@code NOCACHE}.
     *
     * @return how many values the database preallocates in memory; {@code 0} for {@code NOCACHE}.
     */
    int cache() default 20;

    /**
     * Whether the sequence starts over after it reaches its {@link #maxValue() maxValue}.
     *
     * @return whether the sequence starts over after it reaches its {@link #maxValue() maxValue}.
     */
    boolean cycle() default false;
}
