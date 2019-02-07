# README #

This application is built with Spring boot. 
The application runs at port 8083.

### Assumption
If the recipe contains unknown ingredients which they are not in the ingredients list. 
Then the recipe will be considered as invalid.

### Requirement

1. Java 1.8+ JRE
2. Java 1.8+ JDK for development
3. gradle

### How do I get set up? ###

1. Download the source code from the git hub
2. And set up System environment variables for connecting to external API(Optional)

```
KITCHEN_URL=
LOCAL_DATA=
```

The default value of kitchen external API endpoint  `http://188.166.243.95`

- http://188.166.243.95/recipes.php
- http://188.166.243.95/ingredients.php

The default value of LOCAL_DATA is true which means it loads local JSON file.



3. go to project root and run the following command to generate the artifact.
`./gradlew clean build`

You can skip testing by running 
`./gradlew clean build -x test`

4. You can run the application by the following command if you set the system variables.
`java -jar /project/root/build/libs/lunch-0.0.0.0.jar`

Otherwise, you add `-DKITCHEN_URL=` and `-DLOCAL_DATA=true` in the command.

Alternative, you can run it via your IDE.
The main class is 
`com.example.lunch.LunchApplication`

### Deployment

You can run the application as service.
In linux 
```[Unit]
   Description=lunch
   
   [Service]
   User=[user]
   Group=[group]
   EnvironmentFile=[FILE_PATH]
   ExecStart=/usr/bin/java -jar -Xms64M -Xmx256M /path/lunch-0.0.0.0.jar
   ExecReload=/bin/kill -s HUP $MAINPID
   ExecStop=/bin/kill -S TERM $MAINPID
   #Restart=on-failure
   PrivateTmp=true
   [Install]
   WantedBy=multi-user.target

```


### Feature

Swagger documentation 

### Demo
Demo API : `http://188.166.243.95:8083`

Swagger documentation 
`http://188.166.243.95:8083/swagger-ui.html`


### Docker 
please run the following command in the project directory
```
./gradlew clean build
docker build --build-arg JAR_FILE=build/libs/lunch-0.0.0.0.jar -t lunch/api .
docker run -p 8083:8083 lunch/api
```
You can access the application at port 8083.

### Efficiency

The efficiency of checking the ingredients is O(n).
The efficiency of checking the recipes is O(n^2). Getting status from hash map is O(1).
So overall efficency is `O(n^2 +n) = O(n^2)`

