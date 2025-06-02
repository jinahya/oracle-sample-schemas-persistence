package com.github.jinahya.oracle.sample.schemas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface __MappedEntityRepository<ENTITY extends __MappedEntity<ENTITY, ID>, ID extends Serializable>
        extends JpaRepository<ENTITY, ID>,
                JpaSpecificationExecutor<ENTITY> {

}
