# FoodLog Selenium Test Automation Suite

Automated UI test suite for [FoodLog](https://github.com/Anshu-1506/FoodLog) — an AI-powered nutrition tracking web app — built using Selenium WebDriver, TestNG, and the Page Object Model (POM) design pattern.

**App under test:** https://github.com/Anshu-1506/FoodLog
**Live app:** https://food-log-rose.vercel.app/

---

## Overview

This suite covers 16 automated test cases across signup, login, navigation, and meal-logging flows, verifying both UI behavior and form validation logic end-to-end in a real browser.

| Module | Test Cases | Coverage |
|---|---|---|
| Signup | TC01–TC05 | Valid signup, duplicate email, mismatched passwords, short password, empty fields |
| Login | TC06–TC10 | Valid login, wrong password, unregistered email, empty fields, signup link |
| Navigation | TC11–TC14 | Protected route redirect, dashboard load, sidebar navigation, logout session end |
| Meal Logging | TC15–TC16 | AI-powered meal logging (OpenRouter), empty meal input validation |

---

## Tech Stack

- **Java 17**
- **Selenium WebDriver 4.24.0**
- **TestNG 7.10.2**
- **Maven** (build & dependency management)
- **Page Object Model** architecture with explicit waits (no `Thread.sleep`)
- Automatic failure screenshots saved to `target/screenshots`

---

## Prerequisites

Since this suite tests FoodLog's UI, the **FoodLog app must be running locally** before executing tests. You'll need:

- **Java JDK 17+**
- **Maven 3.8+**
- **Google Chrome** (Selenium Manager handles the matching ChromeDriver automatically)
- **Node.js** (to run the FoodLog frontend/backend)
- **MongoDB** (local instance or Atlas connection for FoodLog's backend)

---

## Setup & Running the Tests

### 1. Clone both repositories
```bash
git clone https://github.com/Anshu-1506/FoodLog.git
git clone https://github.com/Anshu-1506/foodlog-selenium.git
```

### 2. Start the FoodLog app
```bash
# Backend
cd FoodLog/backend
npm install
npm run dev

# Frontend (new terminal)
cd FoodLog/frontend
npm install
npm run dev
```
Ensure the backend `.env` includes `MONGODB_URI`, `JWT_SECRET`, and `OPENROUTER_API_KEY`. Confirm the frontend is served at `http://localhost:5173` (or update `Config.java` if different).

### 3. Run the test suite
```bash
cd foodlog-selenium
mvn clean test
```

To run in headless mode:
```bash
mvn clean test -Dheadless=true
```

### 4. View results
- Console output shows pass/fail summary.
- Failure screenshots are saved to `target/screenshots/`.
- Full TestNG report: `target/surefire-reports/index.html`

---

## Project Structure

src/test/java/com/foodlog/
├── base/ → WebDriver setup/teardown, shared test utilities
├── utils/ → Config (base URL, headless toggle, unique email generator)
├── pages/ → Page Object classes (Login, Signup, Dashboard, Sidebar, AddMeal, etc.)
└── tests/ → Test classes (Signup, Login, Navigation, Meal)


---

## Notable Bug Found & Fixed

During development, the logout button click intermittently failed with `ElementClickInterceptedException` due to a sidebar overlay. Fixed by scrolling the element into view before clicking, with a JavaScript-click fallback — a real regression caught and resolved through this suite, not a synthetic test case.

---

## Author

**Anshuman Tiwari**
[GitHub](https://github.com/Anshu-1506) · [LinkedIn](https://www.linkedin.com/in/anshuman-tiwari-41bb64368)