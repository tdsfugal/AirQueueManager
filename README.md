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

