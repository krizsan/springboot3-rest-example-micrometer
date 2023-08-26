# REST-Example - Spring Boot 3 version
Example RESTful web service implemented with Spring Boot 3 and regular (as opposed to reactive) REST controllers.<br/>

## Start the application
In a development environment, the application can be started by launching the RestExampleApplication class.<br/>
Alternatively, the application can be started using Gradle:
```bash
gradle bootRun
```
## Tracing
Micrometer with Zipkin exporter are used for tracing. When running the application in a development
environment, a Zipkin server will be started in a Docker container - see the file compose.yml in the project root.<br/>
The following command will open the default browser and view the Zipkin web UI:
```bash
python3 -m webbrowser  http://localhost:9411
```
If Python or the webbrowser module is not available on your computer, copy-paste the URL into a web browser.
## Test-requests
The file "Test Requests.http" in the project root contain a number of requests that can be used with the
IntelliJ IDEA HTTP client to send requests to the application.

## Articles
TBD
