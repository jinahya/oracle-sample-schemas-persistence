#!/bin/sh
mvn -q -Pfailsafe,persistence,persistence-eclipselink clean verify
