package com.github.jinahya.oracle.sample.schemas.co;

import com.github.jinahya.oracle.sample.schemas.__MappedEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CustomerRepository extends __MappedEntityRepository<Customer, Long> {

    /**
     * Finds the entity whose {@link Customer_#emailAddress emailAddress} attribute matches the specified value.
     *
     * @param emailAddress tne value of {@link Customer_#emailAddress emailAddress} attribute to match.
     * @return an optional of found entity; {@link Optional#empty() empty} if not found.
     */
    Optional<Customer> findByEmailAddress(final String emailAddress);

    // -----------------------------------------------------------------------------------------------------------------
    Optional<Customer> findByFullName(final String fullName, final Pageable pageable);

    Page<Customer> findAllByFullNameLike(final String fullNamePattern, final Pageable pageable);
}
