#!/bin/sh
# Runs `verify` against eclipselink only: the whole lifecycle -- compile, package, javadoc,
# enforcer -- with no test executed. The tests are still COMPILED, which is half of what this is
# for; -Dmaven.test.skip would drop that too and is deliberately not used here.
#
# Needs no database: this is the variant to reach for when Oracle is not up.
#
# `failsafe` is the profile that binds maven-failsafe-plugin, and naming any profile on the command
# line deactivates the activeByDefault provider, so the two have to be named together.
#
# Both flags are needed. As of 3.6.0 failsafe no longer binds its own skipTests parameter to the
# skipTests user property, so -DskipTests reaches surefire alone and the ITs would still run;
# -DskipITs is what stops them. For either half on its own, see
# ./_mvn_jakarta_ee_11_verify_eclipselink.sh.
#
# `clean` is not optional. The annotation processor writes a metamodel per provider, and the two do
# not generate the same one; without a clean in between, a run compiles against the other provider's
# leftovers and fails with "cannot find symbol" on generated classes -- a failure that is silent
# about its cause and reads exactly like the profiles being broken, which they are not.
cd "$(dirname "$0")" || exit 1

exec ./mvnw -Pfailsafe,jakarta-ee-11-eclipselink clean verify -DskipTests -DskipITs "$@"
