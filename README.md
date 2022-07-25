# Applifting-monitoring-service
 Java Spring Boot microservice using MySQL as database to monitor selected http/https urls at chosen intervals for its users.
 The service is adhering to most clean architecture principles in separating domain/data/presentation layers of the app.
 It contains few basic unit tests for each layer.
 
## How to run in Docker
1. Have Java version 17 installed on your machine.
2. Build the project using gradle command such as ```gradlew assemble```.  After that you should have a ```.jar``` file in ```build/libs``` directory of the project.
3. Run ```docker-compose up``` command in the directory with docker-compose file.
4. Service should be now available at ```localhost:8080``` through created container. (Container might restart once so the database initiliazes.)

## How to use the service
All service endpoints except one require access token in ```Authorization``` header of the request which enables them to manage and see endpoints and monitoring results.
### Users
- ```GET``` ```/users/all```  -> You can use this ```Unauthorized``` endpoint to see all seeded users and copy their accessTokens.
### Endpoints

- ```POST``` ```/endpoints/create```  -> Creates an endpoint to monitor:
```
{
  "name": "seznam",
  "monitoredInterval": 5,
  "url": "https://seznam.cz"
}
```

- ```GET``` ```/endpoints/all```  -> Shows all monitored endpoints for current authorized user.
- ```DELETE``` ```/endpoints/{id}```  -> Deletes the endpoint with given {id} and all its results. Only works on the endpoints of the authorized user.

- ```PUT``` ```/endpoints/rename/{id}```  -> Renames endpoint with given {id}:
```
{
  "name": "seznam2",
}
```
- ```PUT``` ```/endpoints/change-interval/{id}```  -> Changes the monitoring interval for endpoint with given {id} (minimum value of 5 sec allowed):
```
{
  "monitoredInterval": "1000",
}
```

### Monitoring
- ```GET``` ```/monitoring/{id}```  -> Shows 10 last results for monitored endpoint with given {id} for current authorized user.
