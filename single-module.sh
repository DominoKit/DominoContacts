#!/bin/bash
mvn archetype:generate \
    -DarchetypeGroupId=org.dominokit.domino.archetypes \
    -DarchetypeArtifactId=domino-gwt-single-module-archetype \
    -DarchetypeVersion=1.0-rc.4-SNAPSHOT \
    -DarchetypeParentGroupId=org.dominokit.samples \
    -DarchetypeParentArtifactId=DominoContacts \
    -DarchetypeParentVersion=1.0-SNAPSHOT \
    -DgroupId=org.dominokit.samples \
    -DartifactId=$1 \
    -Dmodule=${1^} \
    -Dsubpackage=$1

