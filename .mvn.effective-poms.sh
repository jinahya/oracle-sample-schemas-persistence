#!/bin/sh
mvn -Ppersistence,persistence-eclipselink,persistene-eclipselink-weaving help:effective-pom -Doutput=effective-pom-eclipselink.xml
mvn -Ppersistence,persistence-hibernate,persistene-eclipselink-weaving help:effective-pom -Doutput=effective-pom-hibernate.xml
mvn -Ppersistence,persistence-eclipselink,querydsl,querydsl-5 help:effective-pom -Doutput=effective-pom-querydsl-5.xml
mvn -Ppersistence,persistence-eclipselink,querydsl,querydsl-6 help:effective-pom -Doutput=effective-pom-querydsl-6.xml
