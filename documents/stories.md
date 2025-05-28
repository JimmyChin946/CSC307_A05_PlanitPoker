# Stories

### As a user I want to be able to log in
Tasks: Jude Shin
- display the "login" JPanel 
- display a "submit" JButton in the "login" panel
- create the controller class for the JPanel 
- associate the "login" button with "observable and observer" schema in the controller class
    - update the currentUser with the given username in the singleton when the "login" button is pressed
    - redirect the user to the "ChoicePanel" by changing the main content 

### As a user I want to be able to choose between making a new room and joining an existing room
Tasks: Jude Shin
- display the "choice" JPanel
- display a "Join a Room" JButton in the "choice" panel
- display a "Create a Room" JButton in the "choice" panel
- create the controller class for the JPanel
- associate the "Join a Room" button with "observable and observer" schema in the controller class
    - redirect the user to the "JoinRoom" Panel by changing the main content
- associate the "Create a Room" button with "observable and observer" schema in the controller class
    - redirect the user to the "CreateRoom" Panel by changing the main content

## As a user I want to be able to send information through MQTT
Tasks: Jude Shin
- create a ByteConverter class that can take custom data structures and convert them into an array of bytes
- make all classes that will hold information to be sent into a serializable class with a UUID Long variable
- create a PublishItem that holds a topic (String) and a message (byte[])
- in the singleton, create a PublishQueue which will be populated whenever anyone wants to push information to the cloud
    - this is not really needed for this application, as it is not a time sensitive videogame, but it keeps thing organized and it allows for the publisher class to only publish pertinent information
- create a publisher class that will constantly publish information to the cloud based on if the queue has anything or not
- create a subscriber class that will update the singleton with the information based on the given topic of the incoming message

## As a user I want to be able to...



