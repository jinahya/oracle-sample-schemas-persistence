package com.github.jinahya.oracle.sample.schemas;

import lombok.extern.slf4j.Slf4j;

//@AddBeanClasses({
//        __PersistenceProducer.class
//})
//@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
public abstract class _MappedEntity_PersistenceIT<ENTITY extends _MappedEntity<ENTITY>>
        extends __MappedEntity_PersistenceIT<ENTITY, Long> {

    protected _MappedEntity_PersistenceIT(final Class<ENTITY> entityClass) {
        super(entityClass, Long.class);
    }
}