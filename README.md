# Matrix Multiplication Algorithms

This project implements and compares two matrix multiplication algorithms in Java:

- **Naive Matrix Multiplication**
- **Strassen Matrix Multiplication**

The program reads matrices from input files and writes to an output file:  
- The multiplication product  
- Runtime performance  
- Number of multiplications performed by each algorithm  

---

## ✨ Features

- Naive matrix multiplication algorithm  
- Strassen matrix multiplication algorithm  
- File input/output handling  
- Output of matrix product and performance metrics  

---

## ⚙️ Requirements

- **Java**: Version 8 or higher (tested on 23.0.2)  
- **IDE**: Any Java-compatible IDE (tested on Eclipse IDE for Java Developers)  

---

## 🚀 How to Run

### 1. Compile the program

Navigate to the `src` directory and compile all `.java` files into the `class` directory:

```bash
cd src
javac -d ../class *.java
```

### 2. Run the program

Execute the program from the class directory with the following syntax:

```bash
java -cp class Main <inputFile.txt> <outputFile.txt>
```

## 📂 Repository Structure
```bash
.
├── src/
│   ├── Main.java
│   ├── FileHandler.java
│   ├── MatrixMultiplication.java
│   └── StrassenMatrixMultiplication.java
│
├── class/
│   ├── Main.class
│   ├── FileHandler.class
│   ├── MatrixMultiplication.class
│   └── StrassenMatrixMultiplication.class
│
├── input/
│   ├── LabStrassenInput.txt
│   ├── LabSupplementalInput.txt
│   ├── LabErrorInput.txt
│   ├── LabErrorInput1.txt
│   ├── LabErrorInput2.txt
│   └── LabErrorInput3.txt
│
├── output/
│   ├── LabStrassenOutput.txt
│   └── LabSupplementalOutput.txt
│
└── README.md
```

## 📝 Input Files

**Correct Format:**  
-LabStrassenInput.txt  
-LabSupplementalInput.txt  

**Error Format:**  
-LabErrorInput.txt  
-LabErrorInput1.txt  
-LabErrorInput2.txt  
-LabErrorInput3.txt  

## 📑 Input Format

-The matrix must be square.  
-The matrix size must be a power of 2.  
-Matrix B must immediately follow Matrix A.  

**Example input file:**
```yaml
2
4 8
9 2
4 3
8 6
```

## 📤 Output Files

-LabStrassenOutput.txt  
-LabSupplementalOutput.txt  

## 📊 Output Format

Each output file contains:  
-Size of the input matrices  
-Runtime to perform each algorithm  
-Number of multiplications performed  
-Resulting product matrix  

**Example output:**
```yaml
--Matrix Size: 2 x 2--

Naive Matrix Multiplication:
Time: 3600ns
Comparisons: 8
Result:
16 17 
26 22 

Strassen Matrix Multiplication:
Time: 18600ns
Comparisons: 7
Result:
16 17 
26 22
```
