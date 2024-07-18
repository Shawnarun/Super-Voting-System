# Super Voting Application

This project is a Superstar Voting Application built using Spring Boot 3.3 for the backend and Angular for the frontend. The application allows users to vote for candidates in various events, with features for candidate registration, voting, and vote result presentation.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Candidate Registration
- Voter Registration
- Event Management
- Voting System
- Vote Results Presentation

## Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.6+](https://maven.apache.org/install.html)
- [Node.js 16+ and npm 7+](https://nodejs.org/en/download/)
- [Angular CLI](https://angular.io/cli)

## Setup

### Backend

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/superstar-voting-app.git
    cd superstar-voting-app/backend
    ```

2. Build the project with Maven:

    ```bash
    mvn clean install
    ```

3. Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

   The backend server will start on `http://localhost:8080`.

### Frontend

1. Navigate to the frontend directory:

    ```bash
    cd ../frontend
    ```

2. Install dependencies:

    ```bash
    npm install
    ```

3. Start the Angular development server:

    ```bash
    ng serve
    ```

   The frontend application will be available on `http://localhost:4200`.

## Usage

- Access the application at `http://localhost:4200`.
- Register as a voter.
- Register candidates for events.
- Cast votes for candidates in different events.
- View vote results.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch: `git checkout -b my-feature-branch`
3. Make your changes and commit them: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin my-feature-branch`
5. Open a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
