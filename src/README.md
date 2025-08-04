# σ - Standard Deviation Calculator (SOEN 6011 – Deliverable 3)

A simple, user-friendly Java Swing GUI application that calculates the **standard deviation (σ)** of a list of numbers.
Developed as part of **SOEN 6011 Deliverable 3**.

---

## 📌 Features

- ✅ Manual calculation of **standard deviation** (no built-in math functions like `Math.sqrt`)
- ✅ Input validation (handles empty input, invalid characters, limits to 100 values)
- ✅ Responsive GUI using **Java Swing**
- ✅ Fully conforms to **Google Java Style Guide** (via Checkstyle)
- ✅ Debugged with **JDB**
- ✅ Static code analysis with **PMD**
- ✅ GUI aligned with **accessibility principles**
- ✅ Version control via **Git & GitHub** with semantic version tags

---

## 🧮 How It Works

- Users enter numbers separated by commas.
- The program:
  - Parses and validates input
  - Computes the **mean**
  - Calculates variance
  - Applies **manual square, sqrt, abs** functions
- Displays the final result as:
  `Result: σ = [value]`

---

## 🖥️ GUI Screenshot

| Input Example | Output Example |
| ------------- | -------------- |
| `1,2,4,5,99`  | `Result: σ = 38.4260` |

<img src="screenshots/input_example.png" width="400"/> <img src="screenshots/output_example.png" width="400"/>

---

## 🧪 Testing & Validation

- 📋 **Checkstyle**: Google Java Style Guide (`checkstyle-10.12.4-all.jar`)
- 🐛 **Debugger**: JDB breakpoint on `calculateStandardDeviation()`
- 📊 **PMD**: Best Practices rule set (v6.55.0)
- 👨‍🦯 **Accessibility**: Proper layout, color contrast, labels, keyboard navigation

---

## 🧷 Requirements

- Java 8+
- JDK installed and configured
- Checkstyle (Google config XML)
- PMD static analysis tool (optional)

---

## 📂 Project Structure
SigmaCalculatorGUI/
├── src/
│ └── SigmaCalculatorGuiStyleCompliant.java
├── checkstyle-10.12.4-all.jar
├── google_checks_fixed.xml
├── pmd-bin-6.55.0/
└── README.md
