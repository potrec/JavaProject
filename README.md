# Wordle

Welcome to the Wordle project! The primary goal of this project is to provide a straightforward and enjoyable user experience for guessing words, while also serving as a learning opportunity for writing tests.

## Technologies Used

### Backend
- **Java:** The backend is powered by Java, providing a robust and scalable foundation.
- **Spring Boot:** Leveraging the Spring Boot framework for rapid development and streamlined configuration.
- **Spring Data JPA:** Simplifying the data access layer with the power of JPA (Java Persistence API).
- **Spring Security:** Ensuring the security of your application with Spring Security.
- **Lombok:** Reducing boilerplate code and enhancing code readability with Lombok.
- **PostgreSQL (production database):** A powerful, open-source relational database for storing production data.

### Testing
- **JUnit:** Writing and running tests with JUnit to ensure the reliability of the application.
- **H2 Database (for tests):** Using H2 in-memory database for testing purposes, providing a lightweight and efficient solution.


### Frontend
- **SvelteKit:** Building the frontend with SvelteKit, a modern and intuitive framework for building web applications.

## Getting Started

To run the Wordle Guessing Game locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/wordle-guessing-game.git
   cd JavaProject
   ```

2. **Run Backend:**
   - Make sure you have Java installed.
   - Navigate to the backend directory, run docker database and then run the application.
   ```bash
   cd WordleBackend
   docker-compose up
   ./mvnw spring-boot:run
   ```

3. **Run Frontend:**
   - Make sure you have Node.js and npm installed.
   - Navigate to the frontend directory and install dependencies.
   ```bash
   cd frontend
   npm install
   ```
   - Run the SvelteKit application.
   ```bash
   npm run dev
   ```

4. **Access the Application:**
   Open your browser and visit [http://localhost:5000](http://localhost:5000) to start playing the Wordle Guessing Game!

## Contributing

If you'd like to contribute to the project, feel free to submit pull requests or open issues. Your contributions are highly appreciated!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Enjoy playing the Wordle! 🎉
