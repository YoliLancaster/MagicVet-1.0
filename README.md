# MagicVet Veterinary Clinic Application

MagicVet is a console-based application designed to manage a veterinary clinic's operations, including user authentication, registration of owners and pets, and database read/write functionality.
This project showcases the implementation of various Java programming concepts and follows the Onion Architecture.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication**: Secure login and registration for users.
- **Owner and Pet Management**: Add, update, and retrieve information about owners and their pets.
- **Polymorphism**: Retrieve client information and all their pets.
- **Sorting and Grouping**:
  - [Planned] Sort pets by age.
  - [Planned] Sort dogs by size using anonymous classes and enums.
  - [Planned] Group owners by city.
- **Enums**:
  - [Planned] Health state enumeration for pets.
- **Date and Time Handling**: [Planned] Record registration date and time.
- **Database Integration**: Read/write operations using JDBC with PostgreSQL.

## Technology Stack

- **Java**: Core language used for the application.
- **JDBC**: For database operations.
- **PostgreSQL**: Database management system.
- **Maven**: Build automation tool.

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/MagicVet.git
    ```

2. **Navigate to the project directory**:
    ```bash
    cd MagicVet
    ```

3. **Build the project using Maven**:
    ```bash
    mvn clean install
    ```

4. **Set up the PostgreSQL database**:
    - Create a database named `vetclinic`.
    - Run the SQL scripts located in the `src/main/resources/sql` directory to set up the schema and initial data.

5. **Configure the database connection**:
    - Update the `src/main/resources/application.properties` file with your PostgreSQL credentials.

## Usage

1. **Run the application**:
    ```bash
    java -jar target/magicvet-1.0.jar
    ```

2. **Interact with the console**:
    - Follow the prompts to register, authenticate, and manage owners and pets.
