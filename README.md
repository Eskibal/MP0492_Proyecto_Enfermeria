# 🏥 Nurse Management API

## 🩺 Overview

In this activity, we will develop and complete an API capable of handling the basic **CRUD operations** (Create, Read, Update, and Delete) for a **"Nurse"** entity.  

Additionally, we will configure a **Continuous Integration (CI)** system to ensure that **unit tests** are executed automatically whenever changes are made to the code, guaranteeing software quality and enabling early detection of errors.  

Finally, we will **document the project** to provide collaborators and users with clear information about its purpose, installation process, and usage.

---

## 🧠 Learning Goals

- Implement a complete REST API with CRUD operations for nurse management.  
- Set up a CI pipeline using **GitHub Actions**.  
- Write and execute automated unit tests for backend functions.  
- Document the project clearly in a `README.md` file using **Markdown**.

---

## ⚙️ Development Process

### 👥 Work Distribution
Each team member contributed to different aspects of the project, including CRUD implementation, CI configuration, and testing documentation.

---

### 🧑‍⚕️ CRUD Operations – Nurse Entity

| Operation | Description | Response Codes |
|------------|--------------|----------------|
| **C (Create)** | Create a new nurse record | ✅ `201 Created`, ❌ `400 Bad Request` |
| **R (Read)** | Retrieve nurse by ID | ✅ `200 OK`, ❌ `404 Not Found` |
| **U (Update)** | Update nurse data by ID | ✅ `200 OK`, ❌ `404 Not Found`, ❌ `400 Bad Request` |
| **D (Delete)** | Delete a nurse by ID | ✅ `200 OK`, ❌ `404 Not Found` |

---

## 🔁 Continuous Integration (CI)

We created a series of **unit tests** to cover the most critical backend functions, focusing mainly on the **NurseController** methods.  

The CI system was implemented using **GitHub Actions**, following the official documentation:  
👉 [https://docs.github.com/en/actions](https://docs.github.com/en/actions)

### 🧩 CI Pipeline Flow
1. **Commit** new changes to the repository.  
2. **Trigger** the CI pipeline automatically.  
3. **Run** unit tests.  
4. **Notify** the outcome of tests.  
5. **Deploy** or stop the process depending on test results.  

### 🧪 Testing Scenarios
- Commit code changes and create a **Pull Request** to ensure the pipeline runs automatically.  
- Introduce a small intentional error to **simulate a test failure** and confirm that the CI detects it.  

---

## 📝 Documentation

The project includes a `README.md` file that clearly describes the purpose, installation steps, and usage of the system.  
It was written following Markdown best practices:  
👉 [https://www.markdownguide.org/](https://www.markdownguide.org/)

### Required Sections:
- **Project Title:** A clear and descriptive name.  
- **Description:** Explains the project’s purpose and the problem it solves.  
- **Installation:** Steps to clone the repository and install dependencies.  
- **Usage:** Examples of how to execute the application or test the API.

---

## ⚙️ Installation

```bash
# Clone the repository
git clone https://github.com/Eskibal/MP0492_Proyecto_Enfermeria.git

# Install dependencies (example for Symfony)
composer install

# Run the local development server
symfony serve
