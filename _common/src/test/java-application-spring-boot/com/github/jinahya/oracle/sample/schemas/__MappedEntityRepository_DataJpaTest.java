package com.github.jinahya.oracle.sample.schemas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.util.Objects;

@DataJpaTest
public abstract class __MappedEntityRepository_DataJpaTest<
        REPOSITORY extends __MappedEntityRepository<ENTITY, ID>,
        ENTITY extends __MappedEntity<ENTITY, ID>,
        ID extends Serializable
        > {

    @SuppressWarnings({"unchecked"})
    protected __MappedEntityRepository_DataJpaTest(final Class<REPOSITORY> repositoryClass) {
        super();
        this.repositoryClass = Objects.requireNonNull(repositoryClass, "repositoryClass is null");
        this.entityClass = Objects.requireNonNull(
                (Class<ENTITY>) ResolvableType.forClass(this.repositoryClass)
                        .as(__MappedEntityRepository.class)
                        .getGeneric(0)
                        .resolve(),
                "failed to resolve entity type of " + this.repositoryClass
        );
        this.idClass = Objects.requireNonNull(
                (Class<ID>) ResolvableType.forClass(this.repositoryClass)
                        .as(__MappedEntityRepository.class)
                        .getGeneric(1)
                        .resolve(),
                "failed to resolve id type of " + this.repositoryClass
        );
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("don't bother, just an initial check")
    @Test
    void _DontBother_() {
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<REPOSITORY> repositoryClass;

    protected final Class<ENTITY> entityClass;

    protected final Class<ID> idClass;
}