# eeze-streaming-video
Java developer test

### Compile:
1. Open the project on your prefered IDE.
2. Execute command: mvn clean install from a terminal or clicking right on the project -> build|compile|install depending of your IDE.

### Run it local:
1. Execute ***run*** option from your IDE. or Execute the command java -jar <path>/eeze-streaming-video/target/streaming-1.0.0.jar.
2. Open the site http://localhost:8080/swagger-ui/index.html. That contains all the APIs.
3. Select an API and click on ***try it out*** to test it.
> note: three records will be preloaded at the moment of run.
## APIs
**POST /videos**: allows to add/publish a video

**PUT /videos/{id}**: allows to update a video

**DELETE /videos/{id}**: allows to delete (logicaly) a video

**GET /videos/{id}?action=load|play**: if _action=load_ that will retrieve a brief information about the video, if it's _play_ only the content will be retrieved.

**GET /videos/{id}/engagements**: allows to consult the engagement statistics for a video.

**GET /videos?director=pepito&releasedYear=2025**: allows to consult the list of saved videos. It allows to filter by _director_ and/or _releasedYear_ but if no filter is given, it'll retrieve all videos
   
