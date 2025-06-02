package com.github.jinahya.oracle.sample.schemas;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface _MappedEntityRepository<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntityRepository<ENTITY, Long> {

}
