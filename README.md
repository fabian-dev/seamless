# Tracking software compatibilities

This is more or less a tryout to model the domain of "Software compatibilities" with Spring Boot and MongoDb.

A Software (the Aggregate Root in terms of DDD) has a name and version (e.g. DATA Aeonia 10.0) and depends on various
other software components that must have specific versions (e.g. JBoss EAP 7.4).

This project covers an API for these use cases:

* the compatibility of a component with a software version is observed through a CI/CD job (e.g. DATA Aeonia 10.0 is
  observed to be compatible with JBoss 7.4)
* retrieve all compatibilities (e.g. for an out of bound review)
