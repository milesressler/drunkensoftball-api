#!/usr/bin/env bash

./gradlew clean build

scp build/libs/drunkensoftball-1.0-SNAPSHOT.war root@milessmiles:/var/lib/tomcat8/webapps/drunkensoftball.war