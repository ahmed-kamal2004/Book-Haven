mvn install
cd target/
java -jar -Dspring.profiles.active="test" secure-0.0.1-SNAPSHOT.jar
cd ..
