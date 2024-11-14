# Wordle

Welcome to the Wordle project! The primary goal of this project is to provide a straightforward and enjoyable user experience for playing game where your task is to guess a words.
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
- **Vue3:** Building the frontend with Vue3, a modern and intuitive framework for building web applications.
- **Pinia:** Managing the state of the application with Pinia, a modern and lightweight alternative to Vuex.
- **Vite:** Leveraging Vite for fast and efficient development, providing a lightning-fast development server.
- **Tailwind CSS:** Styling the application with Tailwind CSS, a utility-first CSS framework for rapid development.
- **Shadcn:** Beautifully designed components for Vue3, enhancing the user interface of the application.

## Getting Started

To run the Wordle Guessing Game locally, follow these steps:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/potrec/JavaProject
   cd JavaProject
   ```

2. **Run Backend:**
   - Make sure you have Java installed.
   - Make sure you have Docker installed.
   - Navigate to the backend directory, run docker database and then run the application.
   ```bash
   cd WordleBackend
   docker-compose up
   ./mvnw spring-boot:run
   ```
   - If ```./mvnw spring-boot:run``` fails, you can run the application by installing IntelliJ IDEA and running the application from there.

3. **Run Frontend:**
   - Make sure you have Node.js and npm installed.
   - Navigate to the frontend directory and install dependencies.
   ```bash
   cd frontend
   npm install
   ```
   - Run the Vue3 application.
   ```bash
   npm run dev
   ```

4. **Access the Application:**
   Open your browser and visit [http://localhost:5173](http://localhost:5000) to start playing the Wordle Guessing Game!

## Contributing

If you'd like to contribute to the project, feel free to submit pull requests or open issues. Your contributions are highly appreciated!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Enjoy playing the Wordle! 🎉
