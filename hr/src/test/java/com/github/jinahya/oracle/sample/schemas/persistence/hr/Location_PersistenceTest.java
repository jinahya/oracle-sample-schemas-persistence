package com.github.jinahya.oracle.sample.schemas.persistence.hr;

import com.github.jinahya.persistence.mapped.test.__MappedEntity_PersistenceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class Location_PersistenceTest extends __MappedEntity_PersistenceTest<Location, Integer> {

    Location_PersistenceTest() {
        super(Location.class, Integer.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("Select_WhereCountryIdEqual_OrderByLocationIdAsc")
    @Nested
    class Select_WhereCountryIdEqual_OrderByLocationIdAsc_Test {

        @Test
        void __() {
            final var result = applyEntityManager(em -> {
                return em.createNamedQuery("Location.select_WhereCountryIdEqual_OrderByLocationIdAsc", Location.class)
                        .setParameter("countryId", "KR")
                        .setMaxResults(10)
                        .getResultList();
            });
            assertThat(result).satisfiesAnyOf(
                    l -> {
                        assertThat(l).isEmpty();
                    },
                    l -> {
                        assertThat(l)
                                .extracting(Location::getLocationId)
                                .isSortedAccordingTo(Comparator.naturalOrder());
                    }
            );
        }
    }
}
