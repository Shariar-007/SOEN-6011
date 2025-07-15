# SOEN 6011 – Deliverable 1: Problem 3 – CLI Implementation of Function F8 (σ)

## 📌 Project Overview

This project implements **Function F8**, represented by the sigma symbol (σ), using a **Java Command Line Interface (CLI)**.  
In this implementation, σ supports two mathematical interpretations:

- **Standard Deviation (Population Formula)** – measures the dispersion of a dataset.
- **Summation (∑x)** – computes the total sum of input values.

This implementation forms **Problem 3 of Deliverable 1** for the course **SOEN 6011 – Software Engineering Processes** at Concordia University.

---

## 🧠 Functionality

✅ Accepts user input of real numbers  
✅ Supports **two operations**:
- `1` – Compute Standard Deviation (σ)
- `2` – Compute Summation (∑x)  
✅ Displays result formatted to **four decimal places**  
✅ Handles invalid input (non-numeric, out-of-range, infinite, or empty)  
✅ Validates:
- Minimum of 2 values
- Maximum of 100 values  
✅ Fully CLI-based, with clean and user-friendly prompts

---

## ⚙️ How to Run

1. Ensure you have **Java 8 or higher** installed.
2. Compile the file:
javac SigmaCalculator.java


Run the program:
java SigmaCalculator

Example Input / Output
=== Sigma Function CLI ===
Enter the number of values (min 2, max 100):
4
Enter value 1: 5
Enter value 2: 7
Enter value 3: 9
Enter value 4: 6

Choose Operation:
1. Standard Deviation (σ)
2. Summation (∑x)
1
Standard Deviation (σ): 1.4790

Sample Edge Cases Handled
| Case                        | Example Input    | Result                    |
| --------------------------- | ---------------- | ------------------------- |
| All zeros                   | `0, 0, 0`        | SD = 0.0000, Sum = 0.0000 |
| Mixed positive/negative     | `4, -2, 1`       | Correct computation       |
| Invalid operation choice    | `3`              | Prompts user again        |
| Non-numeric input           | `abc`, `5.5.5`   | Re-prompts user           |
| Too few values              | `n = 1`          | Re-prompts user           |
| Excessive input (`n > 100`) | `n = 150`        | Re-prompts user           |
| Infinite or NaN value       | `1e309` or `NaN` | Rejected with warning     |



