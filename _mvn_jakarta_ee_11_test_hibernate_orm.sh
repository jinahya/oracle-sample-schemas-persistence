#!/bin/sh
# Runs the unit tests against hibernate-orm only.
#
# Naming any profile on the command line deactivates every activeByDefault profile, so the provider
# is named here even when it is the default one.
#
# `clean` is not optional. The annotation processor writes a metamodel per provider, and the two do
# not generate the same one; without a clean in between, a run compiles against the other provider's
# leftovers and fails with "cannot find symbol" on generated classes -- a failure that is silent
# about its cause and reads exactly like the profiles being broken, which they are not.
#
# For both providers in one go: ./_mvn_jakarta_ee_11.sh test
cd "$(dirname "$0")" || exit 1

exec ./mvnw -Pjakarta-ee-11-hibernate-orm clean test "$@"
