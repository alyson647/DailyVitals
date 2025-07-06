# 🧭 Current Demo

We’ve implemented the initial **onboarding flow**, featuring:

- Three-step onboarding screens  
- A **Google Sign-In** dialog with Firebase integration  
- A **Home screen** displaying health data summaries (sleep, exercise, weight)

https://github.com/user-attachments/assets/e81b0b9d-e623-4a3b-9e8b-24f23c928877

---

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
