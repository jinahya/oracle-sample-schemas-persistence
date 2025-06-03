package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
@Slf4j
class EmpDetailsView_PersistentIT extends __MappedEntity_PersistenceIT<EmpDetailsView, Integer> {

    EmpDetailsView_PersistentIT() {
        super(EmpDetailsView.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Disabled(value = "it's a view!!!")
    @Test
    @Override
    protected void persistRandom() {
        super.persistRandom();
    }
}