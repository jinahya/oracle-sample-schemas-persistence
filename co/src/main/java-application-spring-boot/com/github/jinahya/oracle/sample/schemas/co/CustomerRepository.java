package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CustomerRepository extends __MappedEntityRepository<Customer, Long> {

    Optional<Customer> findByEmailAddress(final String emailAddress);

    // -----------------------------------------------------------------------------------------------------------------
    Page<Customer> findAllByFullName(final String fullName, final Pageable pageable);

    Page<Customer> findAllByFullNameLike(final String fullNamePattern, final Pageable pageable);
}
