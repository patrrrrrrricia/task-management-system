# 📝 Task Management System

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

## 🛠️ Implementation Highlights

### **Task Assignment & Work Calculation**
The system uses specialized logic to track progress and calculate cumulative work hours based on completed tasks.

```java
// Logic for assigning tasks and calculating total duration
public void assignTaskToEmployee(Employee emp, Task task) {
    if (emp == null) return;
    List<Task> tasks = map.computeIfAbsent(emp, k -> new ArrayList<>());
    if (task != null) {
        tasks.add(task);
    }
}

public int calculateEmployeeWorkDuration(Employee emp) {
    List<Task> tasks = map.get(emp);
    if (tasks == null) return 0;
    int total = 0;
    for (Task t : tasks) {
        if (t != null && "Completed".equals(t.getStatusTask())) {
            total += t.estimateDuration();
        }
    }
    return total;
}
```
