# Reservation Application

## Description
The Reservation Application is designed to facilitate the scheduling of meetings in specific conference rooms. Users can reserve a room for a meeting, specifying the meeting title, start and end dates, and choosing the desired conference room. The application ensures that multiple meetings cannot be scheduled in the same room during overlapping time intervals. Additionally, users have the ability to delete reservations, and list all the reservations for a specific conference room.

## Instructions for Running the Application

### Frontend
1. Open a terminal.
2. Navigate to the "frontend" directory:
    ```bash
    cd frontend
    ```
3. Install the necessary dependencies:
    ```bash
    npm install
    ```
4. Run the development server:
    ```bash
    npm run dev
    ```

### Backend
1. Open a terminal.
2. Navigate to the "backend" directory:
    ```bash
    cd backend
    ```
3. Pull the PostgreSQL Docker image:
    ```bash
    docker pull postgres
    ```
4. Create a Docker network:
    ```bash
    docker network create my-network
    ```
5. Run the PostgreSQL container:
    ```bash
    docker run --name postgres-container -e POSTGRES_DB=reservation -e POSTGRES_USER=vanja -e POSTGRES_PASSWORD=vanja123 -p 5432:5432 --network my-network -d postgres:latest
    ```
6. Build the Docker image for the Spring application:
    ```bash
    docker build -t my-spring-app .
    ```
7. Run the Spring application in a Docker container:
    ```bash
    docker run --name spring-app -p 8081:8081 --network my-network my-spring-app
    ```

### Troubleshooting Spring Application Startup Errors
In case of issues with starting the Spring application container due to database migration, follow these steps:

1. Manually create tables in the database:
    ```bash
    docker exec -it postgres-container psql -U vanja -d reservation
    CREATE TABLE room(id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL);
    CREATE TABLE reservation(id SERIAL PRIMARY KEY, title VARCHAR(255) NOT NULL, start_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, end_at TIMESTAMP WITHOUT TIME ZONE NOT NULL, room_id INTEGER REFERENCES room(id));
    \q
    ```
2. Restart the Spring application:
    ```bash
    docker run --name spring-app -p 8081:8081 --network my-network my-spring-app
    ```

## Demo