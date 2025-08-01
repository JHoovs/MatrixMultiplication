# Programming Project 1

This program implements and compares to Matrix Multiplication Algorithms:
- Naive Matrix Multiplication
- Strassen Matrix Multiplication

The program additionally reads matrices from input files and writes to an output file the multiplication product, runtime, and number of multiplications performed for each algorithm.
 
___
## Features

Naive Matrix Multiplication Algorithm
Strassen Matrix Multiplication Algorithm
Handling of File Input/Output
Output Matrix Product and Performance Metrics

___
## Requirements

Java Version: Version 8 or higher.
	-Used 23.0.2
IDE: Any Java-compatible IDE
	-Used Eclipse IDE for Java Developers

___
## How to Run

1. The following java files are in the src directory:
-Main.java
-FileHandler.java
-MatrixMultiplication.java
-StrassenMatrixMultiplication.java


**Compile the Program**: 
bash
cd src
javac -d ../class *.java


2. The following class files are in the class directory:
-Main.class
-fileHandler.class
MatrixMultiplication.class
StrassenMatrixMultiplication.class

**Run the Program**:
java -cp class Main <inputFile.txt> <outputFile.txt>

____
## Input Files

The following input files are in the HooverJLab1 directory:

**Correct Format
-LabStrassenInput.txt
-LabSupplementalInput.txt

**Error Format
-LabErrorInput.txt
-LabErrorInput1.txt
-LabErrorInput2.txt
-LabErrorInput3.txt

____
## Input Format

The input file requires:
1. The matrix is square.
2. The matrix size is a power of 2.
3. The next line after Matrix A contains Matrix B.

Example:
2
4 8
9 2
4 3
8 6

____
## Output Files

-LabStrassenOutput
-LabSupplementalOutput
____
## Output Format

This program creates an output file with:
- The size of the input matrices.
- The runtime to perform the given algorithm.
- The number of comparisons made during the multiplication algorithm.
- The resulting product matrix.

Example:
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

