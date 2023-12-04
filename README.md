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

3. Create a Docker network:

   ```bash
   docker network create <your-network>
   ```

4. Run the PostgreSQL container:

   ```bash
   docker run --name <db-container-name> -e POSTGRES_DB=<your-database> -e POSTGRES_USER=<your-user> -e POSTGRES_PASSWORD=<your-password> -p 5432:5432 --network <your-network> -d postgres:latest
   ```

5. Build the Docker image for the Spring application:

   ```bash
   docker build -t <image-name> .
   ```

6. Run the Spring application in a Docker container:

   ```bash
   docker run --name <container-name> -e DB_URL=<db-container-name> -e DB_PORT=5432 -e DB_NAME=<your-database> -e DB_USERNAME=<your-user> -e DB_PASSWORD=<your-password> -p 8081:8081 --network <your-network> <image-name>
   ```