# Task Management System

## 💻 Overview
A bespoke management ecosystem tailored for efficient workforce orchestration. This platform allows for seamless synchronization between project managers, employees, and hierarchical task structures. Built on a high-performance **Java 17** core, it transforms fragmented data into a sophisticated administrative experience, blending intuitive **Swing GUI** management with deep analytical intelligence.

---

## 🤍 Tech Stack
* **Backend:** Java 17+, Object-Oriented Design
* **Frontend:** Java Swing (Desktop UI)
* **Architecture:** Layered Architecture (Model-View-Controller)
* **Persistence:** Java Binary Serialization (`.ser`)

---

## 🎀 Core Functionality
* **Entity Management:** Full visibility and management of employee profiles and task catalogs.
* **Hierarchical Task Engine:** * Support for **Simple Tasks** with fixed durations.
    * Support for **Complex Tasks** using the **Composite Pattern** to group multiple sub-tasks.
* **Advanced Data Analytics:**
    * **Hard Worker Filter:** Dynamic filtering of employees exceeding 40 hours of work.
    * **Productivity Stats:** Real-time computation of "Completed" vs. "Uncompleted" tasks per employee.
* **Data Persistence:** Automatic state saving using serialization to ensure data integrity between sessions.

---

## 📂 Project Structure
The project follows a standard layered architecture to maintain clean code and scalability:
* **`model`**: Defines the core entities like `Employee`, `Task`, `SimpleTask`, and `ComplexTask`.
* **`businessLogic`**: Contains the core logic for task assignment (`TaskManagement`) and statistical filtering (`Utility`).
* **`dataAcces`**: Manages the serialization and deserialization of the system state via `Serialization`.
* **`GUI`**: Handles user interactions and real-time interface updates through the `Controller` and `View`.

---

© 2026 Task Analytics Platform | Developed by [**𝐋𝐞𝐨𝐧𝐭𝐞 𝐏𝐚𝐭𝐫𝐢𝐜𝐢𝐚-𝐌𝐢𝐫𝐚𝐛𝐞𝐥𝐚**](https://github.com/patrrrrrrricia)
