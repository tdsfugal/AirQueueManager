# AirQueueManager

The air queue manager package maintains a sorted queue of aircraft that satisfies the following policies:

1) **Type** - Passenger aircraft dequeue before cargo aircraft
2) **Size** - Large aircraft dequeue before small aircraft if they are the same type
3) **Age in Queue** - Aircraft that have been in the queue longer dequeued before other aircraft of similar type and size 
 
The JSON schema for aircraft in AirQueueManager is:

 ```json
 {
   id: <String>,
   type: <"PASSENGER" or "CARGO">,
   size: <"LARGE" or "SMALL">
 }
 
 ```

The aircraft id string is optional.  If one is not provided then the system will create a unique id for the aircraft so it can 
be tracked in the queue by the user.  The system makes no use of the id field. 
 
Both type and size defalut to UNKNOWN if the provided string is not of the proper form.  
 
## API

The API accepts the following calls:

1) GET "/" - Starts the queue

```http
GET / HTTP/1.1
Host: <host>
Content-Type: application/json
```

2) POST "/enqueue" - Adds an aircraft to the queue. 

```http
POST /enqueue HTTP/1.1
Host: <host>
Accept: application/json
Content-Type: application/json

{
	id: "LNM-237",
	size: "LARGE",
	type: "CARGO"
}
```

3) GET "/dequeue" - Removes the highest priority aircraft from the queue

```http
GET /dequeue HTTP/1.1
Host: <host>
Content-Type: application/json
```

which returns a Json string of the next aircraft if there are any in the queue, and an empty Json object if the queue is empty. 

## USE

Clone the repository and run the RUNME.sh shell

```bash

$ git clone https://github.com/tdsfugal/AirQueueManager.git
$ cd AirQueueManager
$ . RUNME.sh
```

The API should now be running on LOCALHOST:8080.  


## BUILD

AirQueueManager is a stand-alone SpringBoot project built with Java 1.8 and Maven.  The repository contains a stable build of the 
system as a .jar file in the /target directory.  These instructions are for developers.

(1) Verify that you have Java 1.8 installed and the environment variable JAVA_HOME is set to point to the JDK 
installation directory.  You should get something similar to this as output from running these 
shell commands (From an OSX install):

```bash
$ java -version
java version "1.8.0_131"
Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)

$ echo $JAVA_HOME
/Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home
```

If you need to install java go to www.oracle.com/technetwork/java/javase/downloads/index.html

(2) Verify that you have Apache Maven installed:

```bash
$ mvn -version
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T15:39:06-04:00)
Maven home: /Users/rezoomme/Applications/apache-maven-3.5.0
Java version: 1.8.0_131, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.12.6", arch: "x86_64", family: "mac"
```

If you need to install Maven go to maven.apache.org  

(3) Clone the repository and run maven to compile and pacakge the jar file before running it:

```bash
$ git clone https://github.com/tdsfugal/AirQueueManager.git
$ cd AirQueueManager
$ mvn clean package
$ . RUNME.sh
```
