# 🧪 FoodLog – Selenium Test Automation Suite

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Selenium](https://img.shields.io/badge/Selenium-4.24.0-43B02A?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.10.2-red)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)
![Status](https://img.shields.io/badge/Tests-16%2F16%20Passing-brightgreen)

Automated UI regression suite for **[FoodLog](https://github.com/Anshu-1506/FoodLog)** — an AI-powered nutrition tracking web app. Built with Selenium WebDriver, TestNG, and the Page Object Model to verify signup, login, navigation, and meal-logging flows end-to-end in a real browser.

🔗 **App under test:** [FoodLog Repository](https://github.com/Anshu-1506/FoodLog)
🌐 **Live app:** [food-log-rose.vercel.app](https://food-log-rose.vercel.app/)

---

## 📋 Test Coverage

16 automated test cases spanning the app's core user flows:

| # | Module | Test Cases | What's Verified |
|---|--------|-----------|------------------|
| 1 | **Signup** | TC01–TC05 | Valid signup, duplicate email rejection, mismatched passwords, password length validation, empty field handling |
| 2 | **Login** | TC06–TC10 | Valid login, wrong password, unregistered email, empty fields, redirect to signup |
| 3 | **Navigation** | TC11–TC14 | Protected route redirection, dashboard load, sidebar navigation, session termination on logout |
| 4 | **Meal Logging** | TC15–TC16 | AI-powered meal logging via OpenRouter, empty input validation |

---

## 🛠️ Tech Stack

- **Language:** Java 17
- **Automation:** Selenium WebDriver 4.24.0
- **Test Framework:** TestNG 7.10.2
- **Build Tool:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Wait Strategy:** Explicit waits only — no `Thread.sleep`
- **Reporting:** TestNG HTML reports + automatic failure screenshots

---

## 📁 Project Structure

src/test/java/com/foodlog/
├── base/ # WebDriver lifecycle, shared setup/teardown
├── utils/ # Config: base URL, headless toggle, test data generation
├── pages/ # Page Object classes
│ ├── LoginPage.java
│ ├── SignupPage.java
│ ├── DashboardPage.java
│ ├── SidebarComponent.java
│ ├── AddMealPage.java
│ └── MealsHistoryPage.java
└── tests/ # Test classes
├── SignupTests.java
├── LoginTests.java
├── NavigationTests.java
└── MealTests.java


---

## ⚙️ Prerequisites

Since this suite drives FoodLog's actual UI, the app must be **running locally** before tests execute.

| Requirement | Purpose |
|---|---|
| Java JDK 17+ | Runs the test suite |
| Maven 3.8+ | Build & dependency management |
| Google Chrome | Browser under automation (ChromeDriver auto-managed by Selenium) |
| Node.js | Runs FoodLog's frontend & backend |
| MongoDB | FoodLog's database (local or Atlas) |

---

## 🚀 Getting Started

### 1. Clone both repositories
```bash
git clone https://github.com/Anshu-1506/FoodLog.git
git clone https://github.com/Anshu-1506/foodlog-selenium.git
```

### 2. Start the FoodLog app

**Backend:**
```bash
cd FoodLog/backend
npm install
npm run dev
```
Set `.env` with `MONGODB_URI`, `JWT_SECRET`, and `OPENROUTER_API_KEY`.

**Frontend** (in a new terminal):
```bash
cd FoodLog/frontend
npm install
npm run dev
```
Confirm it's served at `http://localhost:5173` — update `Config.java` if your port differs.

### 3. Run the tests
```bash
cd foodlog-selenium
mvn clean test
```

Run headless:
```bash
mvn clean test -Dheadless=true
```

### 4. View the results
- Terminal shows a pass/fail summary
- Failure screenshots → `target/screenshots/`
- Full HTML report → `target/surefire-reports/index.html`

---

## 🐞 Bug Found During Testing

While automating the logout flow, a real `ElementClickInterceptedException` surfaced — a sidebar overlay was blocking the logout button click. **Fixed** by scrolling the element into view before interacting, with a JavaScript-click fallback for edge cases. This wasn't a synthetic test scenario — it's an actual defect this suite caught and resolved.

---

## 👤 Author

**Anshuman Tiwari**
B.Tech CSE, ABES Institute of Technology
[GitHub](https://github.com/Anshu-1506) · [LinkedIn](https://www.linkedin.com/in/anshuman-tiwari-41bb64368)