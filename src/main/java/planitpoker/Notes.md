## MQTT
### things to send through mqtt
- host -> publishes -> client(s)
    - host sends over the stories (names and descriptions)
    - when the voting process is over, the host will broadcast the results (stories and their votes)
    - 
- client(s) -> publishes -> host
    - when a client votes on a story, it publishes to the host so that they can save it and calculate averages in one place
        - ORRRRR, when the voting process is over, all the votes are submitted all at once

- host subscriber (host is listening for the client's publish)
    - listens for the client's vote on a story (ORRRRRR it listens for all the voted stories, which will come in all at once at the end of the voting process)
- client subscriber (client is listening for the host's publish)
    - listens for the stories
    - listens for the results at the end of the voting process 

