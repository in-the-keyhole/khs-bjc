## Demonstration Hystrix Application

### Requirements

Maven is required to build the application and Docker (Linx/OS X) to run.

### Run

Run the client and server application with:

`./run.sh`

Applications can be run individually with the same command.

Alternatively, the wars can be deployed to an existing application server. In this case, the URL of the servlet application must be set as a system property for the client. See run.sh.

### Cleanup docker containers

`./cleanup.sh`

