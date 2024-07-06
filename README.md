# Task Management Application

## Introduction

The Task Management Application allows users to create, update, delete, and manage tasks. Users can also mark tasks as
complete and filter/sort tasks based on various criteria. The application includes user authentication to associate
tasks with specific users.

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven 3.6+
- PostgreSQL (or any preferred relational database)

### Clone the Repository

```sh
git clone https://github.com/your-username/task-management-application.git
cd task_mgt_app

```

### Configuration

1. Create a database for the application:

```
createdb task_mgt_db
```

2. Update `src/main/resources/application.properties1` with your database and JWT configurations:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/task_management_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_jwt_secret
```

### Build and Run the Application

```
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Documentation

### User Endpoints

* **Register User**

```
POST /register
```

Request Body:

```
{
    "firstName": "exampleUser",
    "lastName": "exampleUser",
    "password": "examplePassword",
    "email": "user@example.com"
}
```

Response:

````
{
    "id": 1,
    "firstName": "exampleUser",
    "lastName": "exampleUser",
    "email": "user@example.com"
}
````

* **Login User**

``
POST /login
``

Request Body:

```
{
    "email": "user@example.com",
    "password": "examplePassword"
}
```

Response:

```
{
    "token": "jwt_token"
}
```

* **Get User by ID**

`GET /users/{id}`

Headers:

`Authorization: Bearer jwt_token`

Response:

```
{
    "id": 1,
    "username": "exampleUser",
    "email": "user@example.com"
}
```

* **Update User**

`PUT /users/{id}`

Headers:

`Authorization: Bearer jwt_token`

Request Body:

```
{
"username": "updatedUser",
"email": "updated@example.com"
}
```

Response:

```
{
    "id": 1,
    "username": "updatedUser",
    "email": "updated@example.com"
}
```

* **Delete User**

`DELETE /users/{id}`

Headers:

`Authorization: Bearer jwt_token`

Response:

json

    {
      "message": "User deleted successfully"
    }

Task Endpoints

    Create Task

    http

`POST /tasks`

Headers:

`Authorization: Bearer jwt_token`

Request Body:

````
{
    "title": "Task Title",
    "description": "Task Description",
    "dueDate": "2024-07-10"
}
````

Response:

````
{
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "dueDate": "2024-07-10",
    "completionStatus": false,
    "createdAt": "2024-07-01T12:00:00",
    "updatedAt": "2024-07-01T12:00:00"
}
````

* **Get Task by ID**

`GET /tasks/{id}`

Headers:

**Authorization: Bearer jwt_token**

Response:

json

````
{
    "id": 1,
    "title": "Task Title",
    "description": "Task Description",
    "dueDate": "2024-07-10",
    "completionStatus": false,
    "createdAt": "2024-07-01T12:00:00",
    "updatedAt": "2024-07-01T12:00:00"
}
````

Update Task

`PUT /tasks/{id}`

Headers:

`Authorization: Bearer jwt_token`

Request Body:

```
{
    "title": "Updated Task Title",
    "description": "Updated Task Description",
    "dueDate": "2024-07-15",
    "completionStatus": true
}
```

Response:

```
{
    "id": 1,
    "title": "Updated Task Title",
    "description": "Updated Task Description",
    "dueDate": "2024-07-15",
    "completionStatus": true,
    "createdAt": "2024-07-01T12:00:00",
    "updatedAt": "2024-07-01T12:30:00"
}
```

* **Delete Task**

`DELETE /tasks/{id}`

Headers:

`Authorization: Bearer jwt_token`

Response:

```
{
    "message": "Task deleted successfully"
}
```

* **Get Tasks with Filtering and Sorting**

`GET /tasks`

Headers:

`Authorization: Bearer jwt_token`

Query Parameters:

    completionStatus: (optional) Filter by completion status (true or false)
    dueDate: (optional) Filter by due date (e.g., 2024-07-10)
    sort: (optional) Sort by field (e.g., dueDate,desc)

Example Request:

`GET /tasks?completionStatus=false&dueDate=2024-07-10&sort=dueDate,asc`

Response:

    [
      {
        "id": 1,
        "title": "Task Title",
        "description": "Task Description",
        "dueDate": "2024-07-10",
        "completionStatus": false,
        "createdAt": "2024-07-01T12:00:00",
        "updatedAt": "2024-07-01T12:00:00"
      }
    ]

**Usage**

### Register a New User

    Send a POST request to /register with the required user details.
    Use the received token for subsequent authenticated requests.

### Create a Task

    Send a POST request to /tasks with the task details.
    Use the token received during user registration or login in the Authorization header.

### Filter and Sort Tasks

    Send a GET request to /tasks with the desired query parameters.
    Use the token in the Authorization header.

## Development

### Project Structure

* src/main/java: Contains the Java source code.
    * controller: REST controllers for handling HTTP requests.
    * service: Business logic for handling tasks and users.
    * repository: JPA repositories for database interactions.
    * model: Entity classes representing the database tables.
    * config: Configuration classes for security and application settings.

### Contributing

1. Fork the repository.
2. Create a new branch (git checkout -b feature-branch).
3. Make your changes and commit them (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature-branch).
5. Open a Pull Request.

### License

This project is licensed under the MIT License - see the LICENSE file for details.
