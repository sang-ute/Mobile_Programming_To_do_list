# To-Do List Application

**Made by**: Nguyễn Quang Sáng  
**Student ID**: 22110067

---

## Introduction

This project is a **To-Do List Application** built using **Jetpack Compose**, following modern Android development practices. It allows users to manage daily tasks with an intuitive UI, reactive state updates, and clean architecture. The application demonstrates how to create a robust and maintainable task management system using Android’s declarative UI toolkit and architectural components.

---

## Core Features

### 1. Task Listing Screen

Displays a dynamic list of all tasks. Each task is shown with its title and can be interacted with to perform further actions such as editing or checking details.

Sort button: to sort by name or by date, press sort again to switch between ascending and descending order

### 3. Add Task with One Tap

A **Floating Action Button (FAB)** with an Add icon allows users to quickly insert a new task with default values, demonstrating Compose's reactive and stateful behavior.

---

## Technical Implementation

### Architecture and State Management

The app follows the **Model-View-ViewModel (MVVM)** architecture:
- `ViewModel` holds the UI state and business logic
- `LiveData` (observed with `observeAsState()`) updates the UI reactively
- Navigation is handled using **Navigation Compose**

### In-Memory Repository

A simple in-memory `TaskRepository` is used to simulate a local database, demonstrating how repositories abstract data access.

### UI with Jetpack Compose

- **Jetpack Compose** is used to create all UI components in a declarative style
- **Material 3** and **Material Icons** are integrated for a modern and accessible design
- Compose Navigation is used to move between task list and detail views

---

## Technology Stack

- **Jetpack Compose** – Declarative UI framework for Android
- **Material 3** – Modern components and theming
- **ViewModel & LiveData** – Reactive state handling
- **MVVM Architecture** – Clean code separation for datepicker and main screen
- **Kotlin** – Primary development language

---

## How to Install

### Prerequisites

- Android Studio Hedgehog (or newer)
- Kotlin 1.9+
- Gradle with Jetpack Compose support

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/sang-ute/Mobile_Programming_To_do_list.git
   ```

2. **Open the project** in Android Studio.

3. **Sync Gradle** and wait for dependencies to resolve.

4. **Run the app** on an emulator or physical device with API 24 or higher.

---

## How It Works

- On launch, the app displays the list of tasks stored in memory
- When added a new task, just enter the task name, then pick a date for due task
- Pressing side button will open Menu displays 2 options: sort by name or sort by date
- All UI updates happen reactively through LiveData and ViewModel

---

## Conclusion

This To-Do List app showcases essential Android development skills using Jetpack Compose and MVVM. It provides a clean foundation for building scalable, interactive, and modern mobile applications. The project reflects good practices in architecture, UI design, and reactive programming, making it ideal for learning and future expansion (e.g., adding Room or Firebase support).

