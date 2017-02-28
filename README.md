## Demonstration Client application

### Build

`mvn package`

### Run

Run the war in a tomcat docker container with:

`./run.sh`

Alternatively, the war can be deployed to an existing application server. In this case, the URL of the servlet application must be set as a system property. See run.sh.

### Cleanup docker containers

`./cleanup.sh`

### See also

khs-bjc-servlet
