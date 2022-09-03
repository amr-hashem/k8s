### Run
* ` docker container run -p 8080:8080 --name thecollective --network=thecollectivetask_default amrhashem/thecollective-task:k8s.version`
* `docker container run -p 9595:9595 --network=thecollectivetask_default -e THECOLLECTIVE_SERVICE_HOST=thecollective amrhashem/thecollectiveplantintegration:latest`
### Test
#### first service
`curl http://localhost:8080/api/v1/plants/1`
#### second service
`curl http://localhost:9595/api/v2/integrationService/getPlantDetails/1`
