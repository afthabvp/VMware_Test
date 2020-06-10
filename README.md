#### Prerequisite

Installed: [Docker](https://www.docker.com/), [Java 1.8](https://www.oracle.com/technetwork/java/javase/overview/index.html), [gradle](https://gradle.org/install/), [git](https://www.digitalocean.com/community/tutorials/how-to-contribute-to-open-source-getting-started-with-git),[Docker-Compose](https://docs.docker.com/compose/install/)

#### Steps

##### Clone source code from git
```
$  git clone https://github.com/afthabvp/VMware_Test.git.
```
##### go to num_gen folder
```
$  cd num_gen
```

## Run with docker-compose 

Build and start the container by running 

```
$ docker-compose up -d 
```

##### Test application 

#### swagger url

http://localhost:8080/numgen/swagger-ui.html#

##### Stop Docker Container:
```
docker-compose down
```
