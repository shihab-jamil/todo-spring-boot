# todo-example
A simple todo api with spring boot and h2 db

Below http request scripts used from intellij to test the api:

### create user
POST http://localhost:8080/users
Content-Type: application/json

{
  "username": "rhidoy",
  "email": "rhido@gmail.com",
  "dateOfBirth": "1970-12-16",
  "firstName": "MR",
  "lastName": "Rhidoy"
}

### update user
PUT http://localhost:8080/users/1
Content-Type: application/json

{
  "firstName": "MR Update",
  "lastName": "Rhidoy Upate"
}

### create task
POST http://localhost:8080/users/1/tasks
Content-Type: application/json

{
  "description": "Test task not complete",
  "dueDate": "2023-02-28",
  "completed": false
}

### get user tasks
GET http://localhost:8080/users/1/tasks
Accept: application/json

### update task
PUT http://localhost:8080/users/1/tasks/1
Content-Type: application/json

{
  "description": "Test task not complete update",
  "dueDate": "2023-01-01",
  "completed": false
}

### make task compete
PUT http://localhost:8080/users/1/tasks/1/complete
Content-Type: application/json

### get user tasks with status
GET http://localhost:8080/users/1/tasks?completed=true
Accept: application/json

### delete task for user
DELETE http://localhost:8080/users/1/tasks/1
Accept: application/json

