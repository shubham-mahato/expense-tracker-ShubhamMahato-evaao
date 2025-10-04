# Expense Tracker API

This is a RESTful API I built for tracking personal expenses. It was developed as the technical assignment for an internship role at Evaao. My goal for this project was to create a clean, functional, and well-documented backend service that meets all the specified requirements.

---


## Project Features

I implemented all the core requirements for the application, along with the optional enhancements to make the service more robust.

#### Core Functionality
- **Create, Read, Update, and Delete (CRUD) Expenses:** Provides the fundamental operations for managing expense records. Each expense includes an amount, date, a descriptive note, and a category.
- **Data Persistence:** Expense data is saved and retrieved from an H2 in-memory database.
- **Input Validation:** The system validates incoming data to ensure its integrity. For example, expense amounts cannot be negative, and required fields cannot be empty.
- **Structured Error Handling:** The API returns standard HTTP status codes and clear error messages when a request fails, such as when an expense ID is not found.

#### Enhanced Features
- **Dynamic Categorization:** Expenses are linked to categories. When an expense is added with a new category, that category is automatically created and stored.
- **Advanced Filtering:** The API provides endpoints to filter the list of expenses by a specific category or within a given date range.

---

## Technology Stack

The project is built on the Spring Boot ecosystem, utilizing the following technologies:

- **Java 17**
- **Spring Boot 3** (including Spring Web, Spring Data JPA)
- **Hibernate**
  - **H2 In-Memory Database**
- **Maven**
- **Lombok**

---

## How to Run This Project

To get the application running on your local machine, please follow these steps.

#### Prerequisites
- Java JDK 17 or later
- Apache Maven

#### Installation and Execution
1.  **Clone the repository to your local machine:**
    ```bash
    git clone https://github.com/shubham-mahato/expense-tracker-ShubhamMahato-evaao.git
    ```
2.  **Navigate into the project directory:**
    ```bash
    cd expense-tracker
    ```

3.  **Start the application using the Maven wrapper:**
    ```bash
    ./mvnw spring-boot:run
    ```

Once these steps are complete, the server will start, and the API will be accessible at `http://localhost:8080`.

---

## API Usage and Examples

The base URL for all API endpoints is `http://localhost:8080/api/expenses`.

### 1. Add a New Expense

Creates a new expense record in the database.

- **Endpoint:** `POST /`
- **Request Body:**

  ```json
  {
      "amount": 799,
      "date": "2025-10-05",
      "note": "Jio WiFi Bill",
      "category": {
          "name": "Bills"
      }
  }
  ```

- **Successful Response (Status 201 Created):**

  The API will return the newly created expense object.
  ![Screenshot showing the POST request to add a new expense.](./assests/post%20expense.png)
### 2. Get All Expenses

Retrieves a list of all expense records.

- **Endpoint:** `GET /`
- **Successful Response (Status 200 OK):**

  The API will return an array of expense objects.
 ![Screenshot showing the GET request of all the expenses.](./assests/get%20expenses%20all.png)
### 3. Get a Single Expense by ID

Retrieves a single expense record by its ID.

- **Endpoint:** `GET /{id}` (e.g., `/api/expenses/1`)
- **Successful Response (Status 200 OK):**

  The API will return the expense object with the specified ID.

  ![Screenshot of GET request of a ID](./assests/get%20expenses%20by%20id.png)
### 4. Update an Existing Expense

Modifies the details of a single expense, identified by its ID.

- **Endpoint:** `PUT /{id}` (e.g., `/api/expenses/1`)
- **Request Body:**

  ```json
  {
      "amount": 999,
      "date": "2025-10-05",
      "note": "Jio WiFi bill (Price Increased)",
      "category": {
          "name": "Bills"
      }
  }
  ```

- **Successful Response (Status 200 OK):**

  The API will return the updated expense object.

  ![Screenshot of Updated expense using PUT request](./assests/update%20expense%20id.png)
### 5. Delete an Expense

Removes an expense record from the database using its ID.

- **Endpoint:** `DELETE /{id}` (e.g., `/api/expenses/1`)
- **Successful Response (Status 204 No Content):**

  The API will return an empty response.

  ![Screenshot of Deleted Record](./assests/delete%20expense%20by%20id.png)
### 6. Filter Expenses

Retrieves a subset of expenses based on a filter query.

#### Filter by Category

- **Endpoint:** `GET /filter?category=Food`
- **Successful Response (Status 200 OK):**

  The API will return an array of expense objects belonging to the specified category.

 ![Screenshot of filter query](./assests/Filter%20by%20category.png)
#### Filter by Date Range

- **Endpoint:** `GET /filter?startDate=2025-10-01&endDate=2025-10-31`
- **Successful Response (Status 200 OK):**

  The API will return an array of expense objects within the specified date range.

  ![Screenshot of filter by date range](./assests/filter%20by%20date%20range.png)
---

## My Design Philosophy and Assumptions

Here is a brief overview of my approach to building this application.

- **Architecture:** I implemented a standard **3-tier architecture (Controller-Service-Repository)**. This pattern effectively separates the API's concerns: the Controller handles HTTP traffic, the Service contains the business logic, and the Repository manages data access. This makes the code cleaner, more modular, and easier to test.

- **Key Technical Decisions:**
    - My choice of the **H2 Database** was to ensure the project is self-contained and easy for anyone to run without needing to set up an external database.
    - I used the **`BigDecimal`** class for monetary values because it prevents the floating-point inaccuracies that can occur with `double` or `float`, which is critical in financial applications.
    - Building a **RESTful API** provides a flexible foundation. It decouples the backend logic from any specific front-end, allowing various clients (web, mobile, etc.) to connect to it in the future.

- **Assumptions Made:**
    - I built this as a single-user system without authentication or authorization.
    - The currency type is not specified; the application simply stores the numeric value.
    - Dates are stored as `LocalDate` and do not include timezone information.

---

## Testing and Database Access

- **API Client:** I have tested all endpoints using **Postman**.
- **Database Console:** To view the contents of the H2 database directly, you can navigate to the following URL while the application is running:
  `http://localhost:8080/h2-console`
    - Use the JDBC URL: `jdbc:h2:mem:expensedb`
    - User Name: `sa`
    - Password: (leave blank)

Thank you for reviewing my work.