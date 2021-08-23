# projects-management-app


## Running the project

In command line execute the following commands from project directory:

1. `./mvnw clean install` (or `./mvnw.cmd clean install` if you are on Windows platform)
2. `./mvnw spring-boot:run`

After that application should be up and running on `http://localhost:8080`
Please mind that it can take a while during first startup as all required dependencies have to be downloaded

## Database

As a starting point I decided to use in memory H2 database that is automatically populated with each application start
from file /src/main/resources/data.sql . Fell free to add additional entries for testing.

Passwords are hashed, so I am putting list of plain text credentials below:
- r.wiktorski:test
- r.oppenheimer:kaboom
- h.bethe:science


## Remarks

Application is not finished and has many points that require additional work which I didn't manage to finish on time. These are:
- finishing client functionalities
- making UI prettier
- finishing security configuration for more granular access rights
- covering server/client side with tests

Happy reviewing !!!! :)
