package com.github.jinahya.oracle.sample.schemas;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public abstract class _MappedEntityRepository_DataJpaTest<
        REPOSITORY extends _MappedEntityRepository<ENTITY>,
        ENTITY extends _MappedEntity<ENTITY>
        >
        extends __MappedEntityRepository_DataJpaTest<REPOSITORY, ENTITY, Long> {

    protected _MappedEntityRepository_DataJpaTest(final Class<REPOSITORY> repositoryClass) {
        super(repositoryClass);
    }
}