#!/bin/sh
mvn -q -Pfailsafe,persistence,persistence-hibernate clean verify
