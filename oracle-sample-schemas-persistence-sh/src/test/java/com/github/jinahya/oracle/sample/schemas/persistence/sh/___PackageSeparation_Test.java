package com.github.jinahya.oracle.sample.schemas.persistence.sh;

/*-
 * #%L
 * oracle-sample-schemas-persistence-sh
 * %%
 * Copyright (C) 2024 - 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped.__MappedDomainEntity;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * Verifies, on the {@code mapped} branch, that every class of this package which maps a table or a view of the
 * {@code SH} schema -- every implementation of {@link __DomainEntity} -- is a {@link __MappedDomainEntity} too, which on
 * this branch it is by extending its {@code mapped} counterpart.
 * <p>
 * This is this branch's own copy, kept through merges from {@code develop} by {@code merge=ours}; {@code develop}'s
 * asserts the opposite, that this package depends on nothing in {@code mapped}. The converse,
 * {@code mapped.___MappedPackageSeparation_Test}, holds on both.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AnalyzeClasses(packagesOf = __DomainEntity.class, importOptions = ImportOption.DoNotIncludeTests.class)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class ___PackageSeparation_Test {

    /**
     * The name of the package under test.
     */
    private static final String PACKAGE = __DomainEntity.class.getPackageName();

    @ArchTest
    static final ArchRule domainEntities_AreMappedDomainEntities_ =
            classes().that().resideInAPackage(PACKAGE).and().implement(__DomainEntity.class)
                    .should().beAssignableTo(__MappedDomainEntity.class);
}
