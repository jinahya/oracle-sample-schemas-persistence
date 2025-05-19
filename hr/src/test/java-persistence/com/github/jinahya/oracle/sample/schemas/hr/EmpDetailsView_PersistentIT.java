package com.github.jinahya.oracle.sample.schemas.hr;

import com.github.jinahya.oracle.sample.schemas.Persistence_TestUtils;
import com.github.jinahya.oracle.sample.schemas.__MappedEntity_PersistenceIT;
import lombok.extern.slf4j.Slf4j;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void select__() {
        final var entityManager = entityManager();
        final var criteriaBuilder = entityManager.getCriteriaBuilder();
        final var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        final var root = criteriaQuery.from(EmpDetailsView.class);
        criteriaQuery.select(root);
        final var fetch = false;
        if (fetch) {
            root.fetch(EmpDetailsView_.employee);
            root.fetch(EmpDetailsView_.job);
            root.fetch(EmpDetailsView_.manager);
            root.fetch(EmpDetailsView_.department);
            root.fetch(EmpDetailsView_.location);
            root.fetch(EmpDetailsView_.country);
        }
        final var typedQuery = entityManager.createQuery(criteriaQuery);
        Persistence_TestUtils.acceptCountAndRandomIndex(entityManager(), entityClass, c -> i -> {
            typedQuery.setFirstResult(Math.toIntExact(i));
            typedQuery.setMaxResults(1);
        });
        if (!fetch) {
            final var rootGraph = entityManager.createEntityGraph(entityClass);
            rootGraph.addAttributeNodes(EmpDetailsView_.employee);
//            rootGraph.addAttributeNodes("employee");
            rootGraph.addAttributeNodes(EmpDetailsView_.job);
//            rootGraph.addAttributeNodes("job");
            rootGraph.addAttributeNodes("manager");
            rootGraph.addAttributeNodes("department");
            rootGraph.addAttributeNodes("location");
            rootGraph.addAttributeNodes("country");
            final var employeeSubgraph = rootGraph.addSubgraph(EmpDetailsView_.employee);
            employeeSubgraph.addAttributeNodes(Employee_.job);
//            employeeSubgraph.addAttributeNodes("job");
            employeeSubgraph.addAttributeNodes(Employee_.manager);
//            employeeSubgraph.addAttributeNodes("manager");
            employeeSubgraph.addAttributeNodes(Employee_.department);
            final var departmentSubgraph = employeeSubgraph.addSubgraph(Employee_.department);
            departmentSubgraph.addAttributeNodes(Department_.location);
            final var locationSubgraph = departmentSubgraph.addSubgraph(Department_.location);
            locationSubgraph.addAttributeNodes(Location_.country);
            final var countrySubgraph = locationSubgraph.addSubgraph(Location_.country);
            countrySubgraph.addAttributeNodes(Country_.region);
            typedQuery.setHint("jakarta.persistence.loadgraph", rootGraph);
//            typedQuery.setHint("javax.persistence.fetchgraph", rootGraph);
            log.debug("typedQuery.hints: {}", typedQuery.getHints());
        }
        final var selected = typedQuery.getSingleResult();
        log.debug("selected: {}", selected);
//        Hibernate.initialize(selected);
        log.debug(".employee: {}", selected.getEmployee());
        {
            log.debug(".employee.manager: {}", selected.getEmployee().getManager());
            log.debug(".manager.manager: {}", selected.getManager());
            assertThat(selected.getEmployee().getManager()).isEqualTo(selected.getManager());
        }
        {
            log.debug(".department: {}", selected.getDepartment());
            log.debug(".employee.department: {}", selected.getEmployee().getDepartment());
        }
        {
            log.debug(".job: {}", selected.getJob());
            log.debug(".employee.job: {}", selected.getEmployee().getJob());
            assertThat(selected.getEmployee().getDepartment()).isEqualTo(selected.getDepartment());
        }
        {
            log.debug(".location: {}", selected.getLocation());
            log.debug(".department.location: {}",
                      Optional.ofNullable(selected.getDepartment()).map(Department::getLocation).orElse(null));
        }
        {
            log.debug(".country: {}", selected.getCountry());
            log.debug(".location.country: {}",
                      Optional.ofNullable(selected.getLocation()).map(Location::getCountry).orElse(null));
        }
    }
}