!#/usr/bin/env bash

rm -rfv extracted
rm -rfv aot

mvn -DskipTests -Pnative clean package

cp target/*.jar application.jar

java -Djarmode=tools -jar application.jar extract --destination aot

cd aot

java -XX:AOTCacheOutput=app.aot -Dspring.aot.enabled=true -Dspring.context.exist=onRefersh -Dspring.profiles.active=local -jar application.jar

rm -rf ../application.jar

java -XX:AOTCache=app.aot -Dspring.aot.enabled=true -Dspring.profiles.active=local -jar application.jar