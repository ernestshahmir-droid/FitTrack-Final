# 🏋️‍♂️ FitTrack - Mobile Fitness & Health Tracker

FitTrack is a comprehensive native Android application designed to help users manage their personal fitness goals, track caloric intake, monitor hydration, and calculate essential health metrics. 

Developed as the final capstone project for **SW6011 Agile Programming**, this application was built using native Java and XML in Android Studio, strictly following a 7-Sprint Agile (Scrum) lifecycle.

## ✨ Core Features (Agile Epics)

* **🔐 Secure Authentication (Sprint 1):** User registration and secure login powered by **Firebase Authentication** to ensure all personal health data is kept private.
* **⏱️ Interactive Workout Timer (Sprint 2):** Select from workout categories (Cardio, Strength, Stretching) and utilize a precision background thread timer with Pause, Resume, and Reset capabilities.
* **📊 BMI Calculator (Sprint 3):** Input height and weight to instantly calculate Body Mass Index (BMI) and receive dynamic health categorization (Underweight, Normal, Overweight, Obese).
* **🍎 Calorie Tracker (Sprint 4):** A dynamic list-based interface to log meals and automatically calculate total daily caloric intake in real-time.
* **🌙 Dark Mode & Preferences (Sprint 6):** Integrated Android `SharedPreferences` and `AppCompatDelegate` to allow users to toggle and permanently save system-wide Dark Mode.
* **💧 Water Intake & Push Notifications (Sprint 7):** Gamified water tracking using a visual `ProgressBar`. Utilizes Android's `AlarmManager` and `BroadcastReceiver` to send local push notifications to the user's lock screen every 2 hours to remind them to hydrate.

## 🛠️ Technology Stack

* **Front-End:** XML (Native Android UI)
* **Back-End:** Java (Android SDK)
* **Database & Security:** Google Firebase Authentication
* **Data Persistence:** Android `SharedPreferences` (Local Storage)
* **Background Services:** `Handler` Threads, `AlarmManager`, Notification Channels
* **Version Control:** Git & GitHub
* **Project Management:** Jira (Scrum Methodology, 7 Sprints)
* **Testing:** Zephyr Scale (Manual & Traceability Testing)

## 📱 Application Architecture 
FitTrack utilizes standard Android Activity lifecycles. Key architectural highlights include:
* Strict input validation across all forms.
* Memory-leak prevention by properly managing background handlers for the workout timer.
* Robust UI state management (saving theme preferences and daily water progress locally so data is not lost when the app closes).

## 🚀 How to Run Locally

1. Clone the repository:
   ```bash
   git clone [https://github.com/ernestshahmir-droid/FitTrack-Final.git](https://github.com/ernestshahmir-droid/FitTrack-Final.git)
   Open the project folder in Android Studio.

Allow Gradle to sync the dependencies.

Go to Tools > Device Manager and launch an Android Emulator (API 26 or higher recommended to support Notification Channels).

Click the green Run (Shift + F10) button.

(Note: Ensure your emulator has an active internet connection so Firebase Authentication can verify user logins).

📈 Agile Development Lifecycle
This application was developed using the Scrum framework. The product backlog, sprint boards, and user stories were managed in Jira. Testing was heavily integrated into the development cycle, with distinct test cases created in Zephyr Scale and mapped directly to user stories for complete traceability.

Developer: Shahmir
Course: SWE6011 - Agile Programming (HE6)
