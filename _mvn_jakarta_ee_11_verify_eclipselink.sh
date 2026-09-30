#!/bin/sh
# Runs `verify` against eclipselink only: the whole lifecycle -- compile, package, javadoc,
# enforcer -- with both the unit tests and the integration tests executed.
#
# The ITs talk to a real Oracle at localhost:1521/freepdb1, so bring it up with
# ./_docker-compose-up.sh first; otherwise they fail on the connection rather than on a mapping.
#
# `failsafe` is the profile that binds maven-failsafe-plugin, and naming any profile on the command
# line deactivates the activeByDefault provider, so the two have to be named together.
#
# As of 3.6.0 failsafe no longer binds its own skipTests parameter to the skipTests user property,
# so the two halves are selected separately -- and that is a property of ${version.maven-failsafe};
# under 3.5.x and earlier -DskipTests reached both:
#
#     ./_mvn_jakarta_ee_11_verify_eclipselink.sh -DskipTests   # the ITs alone
#     ./_mvn_jakarta_ee_11_verify_eclipselink.sh -DskipITs     # the unit tests alone
#     ./_mvn_jakarta_ee_11_verify_notests_eclipselink.sh       # neither
#
# `clean` is not optional. The annotation processor writes a metamodel per provider, and the two do
# not generate the same one; without a clean in between, a run compiles against the other provider's
# leftovers and fails with "cannot find symbol" on generated classes -- a failure that is silent
# about its cause and reads exactly like the profiles being broken, which they are not.
cd "$(dirname "$0")" || exit 1

exec ./mvnw -Pfailsafe,jakarta-ee-11-eclipselink clean verify "$@"
