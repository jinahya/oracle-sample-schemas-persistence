package com.github.jinahya.oracle.sample.persistence.hr.hr.schema;

/*-
 * #%L
 * hr
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

import com.github.jinahya.oracle.sample.persistence.hr.Department;
import com.github.jinahya.oracle.sample.persistence.hr.Department_;
import com.github.jinahya.oracle.sample.persistence.hr.Employee;
import com.github.jinahya.oracle.sample.persistence.hr.Employee_;
import com.github.jinahya.oracle.sample.persistence.hr.Job;
import com.github.jinahya.oracle.sample.persistence.hr.Location;
import com.github.jinahya.oracle.sample.persistence.hr.__Exercises_TestUtils;
import jakarta.persistence.criteria.JoinType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Answers {@code HR_SCHEMA_I_04} of {@code src/test/sql-exercises/__/02_intermediate.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@DisplayName("HR_SCHEMA_I_04: Company directory")
@Slf4j
class HR_SCHEMA_I_04_PersistenceIT {

    // The department and the location are outer-joined, so either may be null; the employee
    // without a department still has a row, as in the SQL.

    @Test
    void __QueryLanguage() {
        final var list = __Exercises_TestUtils.apply(
                em -> em.createQuery(
                                """
                                        SELECT e, j, d, l
                                        FROM Employee e
                                                 JOIN e.job j
                                                 LEFT JOIN e.department d
                                                 LEFT JOIN d.location l
                                        ORDER BY e.lastName ASC, e.firstName ASC""",
                                Object[].class
                        )
                        .getResultList()
        );
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Job) r[1]).getJobTitle(),
                            r -> Optional.ofNullable((Department) r[2])
                                    .map(Department::getDepartmentName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Location) r[3])
                                    .map(Location::getCity)
                                    .orElse(null))
                .containsExactly(
                        tuple(174, "Ellen", "Abel", "Sales Representative", "Sales", "Oxford"),
                        tuple(166, "Sundar", "Ande", "Sales Representative", "Sales", "Oxford"),
                        tuple(130, "Mozhe", "Atkinson", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(116, "Shelli", "Baida", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(167, "Amit", "Banda", "Sales Representative", "Sales", "Oxford"),
                        tuple(172, "Elizabeth", "Bates", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(192, "Sarah", "Bell", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(151, "David", "Bernstein", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(129, "Laura", "Bissot", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(169, "Harrison", "Bloom", "Sales Representative", "Sales", "Oxford"),
                        tuple(204, "Hermann", "Brown", "Public Relations Representative",
                              "Public Relations", "Munich"),
                        tuple(185, "Alexis", "Bull", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(187, "Anthony", "Cabrio", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(148, "Gerald", "Cambrault", "Sales Manager", "Sales", "Oxford"),
                        tuple(154, "Nanette", "Cambrault", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(110, "John", "Chen", "Accountant", "Finance", "Seattle"),
                        tuple(188, "Kelly", "Chung", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(119, "Karen", "Colmenares", "Purchasing Clerk", "Purchasing",
                              "Seattle"),
                        tuple(142, "Curtis", "Davies", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(202, "Pat", "Davis", "Marketing Representative", "Marketing",
                              "Toronto"),
                        tuple(186, "Julia", "Dellinger", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(189, "Jennifer", "Dilly", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(160, "Louise", "Doran", "Sales Representative", "Sales", "Oxford"),
                        tuple(147, "Alberto", "Errazuriz", "Sales Manager", "Sales", "Oxford"),
                        tuple(193, "Britney", "Everett", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(109, "Daniel", "Faviet", "Accountant", "Finance", "Seattle"),
                        tuple(197, "Kevin", "Feeney", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(181, "Jean", "Fleaur", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(170, "Tayler", "Fox", "Sales Representative", "Sales", "Oxford"),
                        tuple(121, "Adam", "Fripp", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(102, "Lex", "Garcia", "Administration Vice President", "Executive",
                              "Seattle"),
                        tuple(135, "Ki", "Gee", "Stock Clerk", "Shipping", "South San Francisco"),
                        tuple(183, "Girard", "Geoni", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(206, "William", "Gietz", "Public Accountant", "Accounting",
                              "Seattle"),
                        tuple(199, "Douglas", "Grant", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(178, "Kimberely", "Grant", "Sales Representative", null, null),
                        tuple(163, "Danielle", "Greene", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(108, "Nancy", "Gruenberg", "Finance Manager", "Finance", "Seattle"),
                        tuple(152, "Peter", "Hall", "Sales Representative", "Sales", "Oxford"),
                        tuple(205, "Shelley", "Higgins", "Accounting Manager", "Accounting",
                              "Seattle"),
                        tuple(118, "Guy", "Himuro", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(175, "Alyssa", "Hutton", "Sales Representative", "Sales", "Oxford"),
                        tuple(106, "Valli", "Jackson", "Programmer", "IT", "Southlake"),
                        tuple(203, "Susan", "Jacobs", "Human Resources Representative",
                              "Human Resources", "London"),
                        tuple(103, "Alexander", "James", "Programmer", "IT", "Southlake"),
                        tuple(179, "Charles", "Johnson", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(195, "Vance", "Jones", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(122, "Payam", "Kaufling", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(115, "Alexander", "Khoo", "Purchasing Clerk", "Purchasing",
                              "Seattle"),
                        tuple(156, "Janette", "King", "Sales Representative", "Sales", "Oxford"),
                        tuple(100, "Steven", "King", "President", "Executive", "Seattle"),
                        tuple(173, "Sundita", "Kumar", "Sales Representative", "Sales", "Oxford"),
                        tuple(137, "Renske", "Ladwig", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(127, "James", "Landry", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(165, "David", "Lee", "Sales Representative", "Sales", "Oxford"),
                        tuple(114, "Den", "Li", "Purchasing Manager", "Purchasing", "Seattle"),
                        tuple(177, "Jack", "Livingston", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(133, "Jason", "Mallin", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(128, "Steven", "Markle", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(131, "James", "Marlow", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(201, "Michael", "Martinez", "Marketing Manager", "Marketing",
                              "Toronto"),
                        tuple(164, "Mattea", "Marvins", "Sales Representative", "Sales", "Oxford"),
                        tuple(143, "Randall", "Matos", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(158, "Allan", "McEwen", "Sales Representative", "Sales", "Oxford"),
                        tuple(194, "Samuel", "McLeod", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(126, "Irene", "Mikkilineni", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(104, "Bruce", "Miller", "Programmer", "IT", "Southlake"),
                        tuple(124, "Kevin", "Mourgos", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(125, "Julia", "Nayer", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(107, "Diana", "Nguyen", "Programmer", "IT", "Southlake"),
                        tuple(198, "Donald", "OConnell", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(153, "Christopher", "Olsen", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(132, "TJ", "Olson", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(168, "Lisa", "Ozer", "Sales Representative", "Sales", "Oxford"),
                        tuple(146, "Karen", "Partners", "Sales Manager", "Sales", "Oxford"),
                        tuple(140, "Joshua", "Patel", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(191, "Randall", "Perkins", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(136, "Hazel", "Philtanker", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(113, "Luis", "Popp", "Accountant", "Finance", "Seattle"),
                        tuple(141, "Trenna", "Rajs", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(134, "Michael", "Rogers", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(184, "Nandita", "Sarchand", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(111, "Ismael", "Sciarra", "Accountant", "Finance", "Seattle"),
                        tuple(139, "John", "Seo", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(161, "Sarath", "Sewall", "Sales Representative", "Sales", "Oxford"),
                        tuple(145, "John", "Singh", "Sales Manager", "Sales", "Oxford"),
                        tuple(159, "Lindsey", "Smith", "Sales Representative", "Sales", "Oxford"),
                        tuple(171, "William", "Smith", "Sales Representative", "Sales", "Oxford"),
                        tuple(138, "Stephen", "Stiles", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(182, "Martha", "Sullivan", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(157, "Patrick", "Sully", "Sales Representative", "Sales", "Oxford"),
                        tuple(176, "Jonathon", "Taylor", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(180, "Winston", "Taylor", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(117, "Sigal", "Tobias", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(150, "Sean", "Tucker", "Sales Representative", "Sales", "Oxford"),
                        tuple(155, "Oliver", "Tuvault", "Sales Representative", "Sales", "Oxford"),
                        tuple(112, "Jose Manuel", "Urman", "Accountant", "Finance", "Seattle"),
                        tuple(144, "Peter", "Vargas", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(190, "Timothy", "Venzl", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(162, "Clara", "Vishney", "Sales Representative", "Sales", "Oxford"),
                        tuple(123, "Shanta", "Vollman", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(196, "Alana", "Walsh", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(120, "Matthew", "Weiss", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(200, "Jennifer", "Whalen", "Administration Assistant",
                              "Administration", "Seattle"),
                        tuple(105, "David", "Williams", "Programmer", "IT", "Southlake"),
                        tuple(101, "Neena", "Yang", "Administration Vice President", "Executive",
                              "Seattle"),
                        tuple(149, "Eleni", "Zlotkey", "Sales Manager", "Sales", "Oxford"));
    }

    @Test
    void __CriteriaApi() {
        final var list = __Exercises_TestUtils.apply(em -> {
            final var cb = em.getCriteriaBuilder();
            final var cq = cb.createQuery(Object[].class);
            final var e = cq.from(Employee.class);
            final var j = e.join(Employee_.job);
            final var d = e.join(Employee_.department, JoinType.LEFT);
            final var l = d.join(Department_.location, JoinType.LEFT);
            cq.select(cb.array(e, j, d, l))
                    .orderBy(cb.asc(e.get(Employee_.lastName)),
                             cb.asc(e.get(Employee_.firstName)));
            return em.createQuery(cq)
                    .getResultList();
        });
        list.forEach(r -> log.debug("{}", Arrays.toString(r)));
        assertThat(list)
                .extracting(r -> ((Employee) r[0]).getEmployeeId(),
                            r -> ((Employee) r[0]).getFirstName(),
                            r -> ((Employee) r[0]).getLastName(),
                            r -> ((Job) r[1]).getJobTitle(),
                            r -> Optional.ofNullable((Department) r[2])
                                    .map(Department::getDepartmentName)
                                    .orElse(null),
                            r -> Optional.ofNullable((Location) r[3])
                                    .map(Location::getCity)
                                    .orElse(null))
                .containsExactly(
                        tuple(174, "Ellen", "Abel", "Sales Representative", "Sales", "Oxford"),
                        tuple(166, "Sundar", "Ande", "Sales Representative", "Sales", "Oxford"),
                        tuple(130, "Mozhe", "Atkinson", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(116, "Shelli", "Baida", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(167, "Amit", "Banda", "Sales Representative", "Sales", "Oxford"),
                        tuple(172, "Elizabeth", "Bates", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(192, "Sarah", "Bell", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(151, "David", "Bernstein", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(129, "Laura", "Bissot", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(169, "Harrison", "Bloom", "Sales Representative", "Sales", "Oxford"),
                        tuple(204, "Hermann", "Brown", "Public Relations Representative",
                              "Public Relations", "Munich"),
                        tuple(185, "Alexis", "Bull", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(187, "Anthony", "Cabrio", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(148, "Gerald", "Cambrault", "Sales Manager", "Sales", "Oxford"),
                        tuple(154, "Nanette", "Cambrault", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(110, "John", "Chen", "Accountant", "Finance", "Seattle"),
                        tuple(188, "Kelly", "Chung", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(119, "Karen", "Colmenares", "Purchasing Clerk", "Purchasing",
                              "Seattle"),
                        tuple(142, "Curtis", "Davies", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(202, "Pat", "Davis", "Marketing Representative", "Marketing",
                              "Toronto"),
                        tuple(186, "Julia", "Dellinger", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(189, "Jennifer", "Dilly", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(160, "Louise", "Doran", "Sales Representative", "Sales", "Oxford"),
                        tuple(147, "Alberto", "Errazuriz", "Sales Manager", "Sales", "Oxford"),
                        tuple(193, "Britney", "Everett", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(109, "Daniel", "Faviet", "Accountant", "Finance", "Seattle"),
                        tuple(197, "Kevin", "Feeney", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(181, "Jean", "Fleaur", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(170, "Tayler", "Fox", "Sales Representative", "Sales", "Oxford"),
                        tuple(121, "Adam", "Fripp", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(102, "Lex", "Garcia", "Administration Vice President", "Executive",
                              "Seattle"),
                        tuple(135, "Ki", "Gee", "Stock Clerk", "Shipping", "South San Francisco"),
                        tuple(183, "Girard", "Geoni", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(206, "William", "Gietz", "Public Accountant", "Accounting",
                              "Seattle"),
                        tuple(199, "Douglas", "Grant", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(178, "Kimberely", "Grant", "Sales Representative", null, null),
                        tuple(163, "Danielle", "Greene", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(108, "Nancy", "Gruenberg", "Finance Manager", "Finance", "Seattle"),
                        tuple(152, "Peter", "Hall", "Sales Representative", "Sales", "Oxford"),
                        tuple(205, "Shelley", "Higgins", "Accounting Manager", "Accounting",
                              "Seattle"),
                        tuple(118, "Guy", "Himuro", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(175, "Alyssa", "Hutton", "Sales Representative", "Sales", "Oxford"),
                        tuple(106, "Valli", "Jackson", "Programmer", "IT", "Southlake"),
                        tuple(203, "Susan", "Jacobs", "Human Resources Representative",
                              "Human Resources", "London"),
                        tuple(103, "Alexander", "James", "Programmer", "IT", "Southlake"),
                        tuple(179, "Charles", "Johnson", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(195, "Vance", "Jones", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(122, "Payam", "Kaufling", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(115, "Alexander", "Khoo", "Purchasing Clerk", "Purchasing",
                              "Seattle"),
                        tuple(156, "Janette", "King", "Sales Representative", "Sales", "Oxford"),
                        tuple(100, "Steven", "King", "President", "Executive", "Seattle"),
                        tuple(173, "Sundita", "Kumar", "Sales Representative", "Sales", "Oxford"),
                        tuple(137, "Renske", "Ladwig", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(127, "James", "Landry", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(165, "David", "Lee", "Sales Representative", "Sales", "Oxford"),
                        tuple(114, "Den", "Li", "Purchasing Manager", "Purchasing", "Seattle"),
                        tuple(177, "Jack", "Livingston", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(133, "Jason", "Mallin", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(128, "Steven", "Markle", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(131, "James", "Marlow", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(201, "Michael", "Martinez", "Marketing Manager", "Marketing",
                              "Toronto"),
                        tuple(164, "Mattea", "Marvins", "Sales Representative", "Sales", "Oxford"),
                        tuple(143, "Randall", "Matos", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(158, "Allan", "McEwen", "Sales Representative", "Sales", "Oxford"),
                        tuple(194, "Samuel", "McLeod", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(126, "Irene", "Mikkilineni", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(104, "Bruce", "Miller", "Programmer", "IT", "Southlake"),
                        tuple(124, "Kevin", "Mourgos", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(125, "Julia", "Nayer", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(107, "Diana", "Nguyen", "Programmer", "IT", "Southlake"),
                        tuple(198, "Donald", "OConnell", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(153, "Christopher", "Olsen", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(132, "TJ", "Olson", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(168, "Lisa", "Ozer", "Sales Representative", "Sales", "Oxford"),
                        tuple(146, "Karen", "Partners", "Sales Manager", "Sales", "Oxford"),
                        tuple(140, "Joshua", "Patel", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(191, "Randall", "Perkins", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(136, "Hazel", "Philtanker", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(113, "Luis", "Popp", "Accountant", "Finance", "Seattle"),
                        tuple(141, "Trenna", "Rajs", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(134, "Michael", "Rogers", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(184, "Nandita", "Sarchand", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(111, "Ismael", "Sciarra", "Accountant", "Finance", "Seattle"),
                        tuple(139, "John", "Seo", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(161, "Sarath", "Sewall", "Sales Representative", "Sales", "Oxford"),
                        tuple(145, "John", "Singh", "Sales Manager", "Sales", "Oxford"),
                        tuple(159, "Lindsey", "Smith", "Sales Representative", "Sales", "Oxford"),
                        tuple(171, "William", "Smith", "Sales Representative", "Sales", "Oxford"),
                        tuple(138, "Stephen", "Stiles", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(182, "Martha", "Sullivan", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(157, "Patrick", "Sully", "Sales Representative", "Sales", "Oxford"),
                        tuple(176, "Jonathon", "Taylor", "Sales Representative", "Sales",
                              "Oxford"),
                        tuple(180, "Winston", "Taylor", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(117, "Sigal", "Tobias", "Purchasing Clerk", "Purchasing", "Seattle"),
                        tuple(150, "Sean", "Tucker", "Sales Representative", "Sales", "Oxford"),
                        tuple(155, "Oliver", "Tuvault", "Sales Representative", "Sales", "Oxford"),
                        tuple(112, "Jose Manuel", "Urman", "Accountant", "Finance", "Seattle"),
                        tuple(144, "Peter", "Vargas", "Stock Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(190, "Timothy", "Venzl", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(162, "Clara", "Vishney", "Sales Representative", "Sales", "Oxford"),
                        tuple(123, "Shanta", "Vollman", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(196, "Alana", "Walsh", "Shipping Clerk", "Shipping",
                              "South San Francisco"),
                        tuple(120, "Matthew", "Weiss", "Stock Manager", "Shipping",
                              "South San Francisco"),
                        tuple(200, "Jennifer", "Whalen", "Administration Assistant",
                              "Administration", "Seattle"),
                        tuple(105, "David", "Williams", "Programmer", "IT", "Southlake"),
                        tuple(101, "Neena", "Yang", "Administration Vice President", "Executive",
                              "Seattle"),
                        tuple(149, "Eleni", "Zlotkey", "Sales Manager", "Sales", "Oxford"));
    }

}
