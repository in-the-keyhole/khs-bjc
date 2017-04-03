# Hystrix Dashboard

Spring Clound Netflix OSS - Hystrix Dashboard

Installation
------------

1. Clone this repository
2. Import into an IDE and configure it as a Maven project
3. Start server by running the `com.keyolesoftware.hystrix.HystrixDashboard` as a Java Application
4. Browse to [http://localhost:7979/hystrix](http://localhost:7979/hystrix)

Monitoring a Service Circuits
------------------------------

The [API Gateway - Reference Application](https://github.com/in-the-keyhole/khs-api-gateway) reference application has been configured with Hystrix circuit breakers. 

If the API Gateway is running, you can monitor the URL shown below in a running Hystrix dashboard and metrics will be displayed and monitored.

     http://localhost:9090/hystrix.stream
