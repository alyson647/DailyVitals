# 📱 Daily Vitals – Compose Multiplatform App

**Daily Vitals** is a Compose Multiplatform mobile application built for **Android** and **iOS**, designed to help users monitor and manage their diabetes-related data on a daily basis.

The app provides a simple and intuitive interface featuring an **Onboarding Flow**, a **Home Screen Dashboard**, and secure user authentication. It leverages modern technologies and architecture patterns to ensure a smooth, scalable, and maintainable development experience.

## ✨ Features

- 📲 **Cross-platform** support: Built with JetBrains **Compose Multiplatform** for Android & iOS  
- 🔐 **Google Sign-In** via **Firebase Authentication**  
- ☁️ **Cloud Data Storage** with **Firebase Firestore**  
- 🗄️ **Local Persistence** using **SQLDelight**  
- ⚙️ **Dependency Injection** with **Koin**  
- 💾 **Jetpack DataStore** for local preference storage  
- 🧠 **MVVM Architecture** for clean separation of concerns 


# 🛠️ Project Structure

This is a Kotlin Multiplatform project targeting Android and iOS.

- `/composeApp`  
  Shared code for Compose Multiplatform apps. It includes:
  - `commonMain`: Code shared across all platforms.
  - Platform-specific folders (e.g., `iosMain`) for iOS-only code like Apple’s CoreCrypto.

- `/iosApp`  
  Contains the iOS application entry point. Even if UI is shared with Compose, this is where you add SwiftUI and iOS-specific logic.

---

📚 Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
