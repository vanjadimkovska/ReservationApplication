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
2. Navigate to the "backend" directory.

   ```bash
   cd backend
   ```

3. Build the project using Maven.

   ```bash
   mvn clean install
   ```

4. Navigate to the "root" directory.

   ```bash
   cd ..
   ```
   
5. Modify the docker-compose.yml file with your own data.
6. Run the docker-compose.yml file:

   ```bash
   docker-compose up
   ```

## Demo

[Reservation Application Demo](frontend/demo/04.12.2023_23.21.27_REC.mp4)

Click the above link to download and watch a quick demo video.