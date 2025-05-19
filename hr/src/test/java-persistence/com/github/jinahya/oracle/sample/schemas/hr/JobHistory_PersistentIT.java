package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@AddBeanClasses({
        _PersistenceProducer.class
})
@ExtendWith(WeldJunit5AutoExtension.class)
class JobHistory_PersistentIT extends __MappedEntity_PersistenceIT<JobHistory, JobHistoryId> {

    JobHistory_PersistentIT() {
        super(JobHistory.class, JobHistoryId.class);
    }
}