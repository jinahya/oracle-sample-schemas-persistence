package com.github.jinahya.oracle.sample.schemas.co.mapped;

/*-
 * #%L
 * co
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

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.lang.invoke.MethodHandles;

@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public class _MappedCoLoggingEntityListener {

    private static final System.Logger logger = System.getLogger(MethodHandles.lookup().lookupClass().getName());

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public _MappedCoLoggingEntityListener() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    // --------------------------------------------------------------------------------------------- Jakarta-Persistence
    @PrePersist
    void doOnPrePersist(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "persisting: {0}", entity);
    }

    @PostPersist
    void doOnPostPersist(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "persisted: {0}", entity);
    }

    @PreUpdate
    void doOnPreUpdate(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "updating: {0}", entity);
    }

    @PostUpdate
    void doOnPostUpdate(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "updated: {0}", entity);
    }

    @PostLoad
    void doOnPostLoad(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "loaded: {0}", entity);
    }

    @PreRemove
    void doOnPreRemove(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "removing: {0}", entity);
    }

    @PostRemove
    void doOnPostRemove(final _MappedCoEntity<?> entity) {
        logger.log(System.Logger.Level.TRACE, "removed: {0}", entity);
    }
}
