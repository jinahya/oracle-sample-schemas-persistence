package com.github.jinahya.oracle.sample.schemas.persistence.sh.mapped;

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

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Verifies that no class of this package, the {@code mapped} mapping of the {@code SH} schema, depends on anything
 * in its parent package, the concrete mapping; the two are independent mappings, kept in step by hand.
 * <p>
 * Only main classes are imported. A constant the compiler inlines, a {@code COLUMN_NAME_*} for instance, leaves no
 * dependency in the class file, and so is not seen.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AnalyzeClasses(packagesOf = __MappedDomainEntity.class, importOptions = ImportOption.DoNotIncludeTests.class)
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class ___MappedPackageSeparation_Test {

    /**
     * The name of the package under test.
     */
    private static final String PACKAGE = __MappedDomainEntity.class.getPackageName();

    /**
     * The name of the parent package, the one this package must not depend on.
     */
    private static final String PARENT_PACKAGE = PACKAGE.substring(0, PACKAGE.lastIndexOf('.'));

    @ArchTest
    static final ArchRule classes_DoNotDependOnParentPackage_ =
            noClasses().that().resideInAPackage(PACKAGE)
                    .should().dependOnClassesThat().resideInAPackage(PARENT_PACKAGE);
}
