/**
 * This was referenced for any small syntax issues when 
 * writing the code for the Strassen algorithm. Cormen textbook was
 * referenced for the main step-by-step approach of the algorithm.
 * 
 * Reference:
 * https://www.geeksforgeeks.org/strassens-matrix-multiplication/
 */


public class StrassenMatrixMultiplication {
	
	/**
	 * Three functions to initialize multiplication counter to 0, reset between 
	 * each iteration in Main, and count multiplications in algorithm.
	 */
	
	private static int strassenMultiplicationCounter = 0;
	
	public static void resetCounter() {
		strassenMultiplicationCounter = 0;
	}
	
	public static int getCounter() {
		return strassenMultiplicationCounter;
	}
	
	//Wrap Strassen algorithm
	
	public static int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		return strassen(A, B, n);
	}
	
	/**
	 * Strassen algorithm on Matrix A and Matrix B.
	 * 
	 * @param Matrix A
	 * @param Matrix B
	 * @param n size of matrix
	 * 
	 * @return 2D array of C
	 */
	
	public static int[][] strassen(int[][] A, int[][] B, int n) {
		
		//Base case
		if (n==1) {
			int[][] C = {{A[0][0]*B[0][0]}};
			strassenMultiplicationCounter++;
			return C;
		} 
		
		// Create 2D arrays of submatrices
		int newSize = n/2;
		int[][] A11 = new int [newSize][newSize];
		int[][] A12 = new int [newSize][newSize];
		int[][] A21 = new int [newSize][newSize];
		int[][] A22 = new int [newSize][newSize];
		
		int[][] B11 = new int [newSize][newSize];
		int[][] B12 = new int [newSize][newSize];
		int[][] B21 = new int [newSize][newSize];
		int[][] B22 = new int [newSize][newSize];
		
		// Divide matrices into submatrices
		splitMatrix(A, A11, 0, 0);
		splitMatrix(A, A12, 0, newSize);
		splitMatrix(A, A21, newSize, 0);
		splitMatrix(A, A22, newSize, newSize);
		
		splitMatrix(B, B11, 0, 0);
		splitMatrix(B, B12, 0, newSize);
		splitMatrix(B, B21, newSize, 0);
		splitMatrix(B, B22, newSize, newSize);
		
		// Create 10 S matrices using newSize
		int[][] S1 = subtractMatrix(B12, B22);
		int[][] S2 = addMatrix(A11, A12);
		int[][] S3 = addMatrix(A21, A22);
		int[][] S4 = subtractMatrix(B21, B11);
		int[][] S5 = addMatrix(A11, A22);
		int[][] S6 = addMatrix(B11, B22);
		int[][] S7 = subtractMatrix(A12, A22);
		int[][] S8 = addMatrix(B21, B22);
		int[][] S9 = subtractMatrix(A11, A21);
		int[][] S10 = addMatrix(B11, B12);
		
		// Recursively multiply newSize matrices creating matrix products P
		int[][] P1 = strassen(A11, S1, newSize);
		int[][] P2 = strassen(S2, B22, newSize);
		int[][] P3 = strassen(S3, B11, newSize);
		int[][] P4 = strassen(A22, S4, newSize);
		int[][] P5 = strassen(S5, S6, newSize);
		int[][] P6 = strassen(S7, S8, newSize);
		int[][] P7 = strassen(S9, S10, newSize);
		
		// Compute C submatrices from P matrices
		int[][] C11 = addMatrix(subtractMatrix(addMatrix(P5, P4), P2), P6);
		int[][] C12 = addMatrix(P1, P2);
		int[][] C21 = addMatrix(P3, P4);
		int[][] C22 = subtractMatrix(subtractMatrix(addMatrix(P5, P1), P3), P7);
		
		// Combine C submatrices into 2D array of C
		int[][] C = new int[n][n];
		joinMatrix(C11, C, 0, 0);
		joinMatrix(C12, C, 0, newSize);
		joinMatrix(C21, C, newSize, 0);
		joinMatrix(C22, C, newSize, newSize);
		
		return C;
	}

	/**
	* Split parent matrix into submatrices for the division step of Strassen Algorithm
	* Child matrix has dimensions size x size
	* 
	* @param parent is 2D array of source matrix
	* @param child is 2D array for submatrix
	* @param rowOffset is how much to move row index for submatrix
	* @param colOffset is how much to move column index for submatrix
	*/
	
	private static void splitMatrix(int[][] parent, int[][] child, int rowOffset, int colOffset) {
		
		//size of matrix n/2 x n/2
		int size = child.length;
		
		//Copy parent matrix for size x size
		for (int i = 0; i < size; i++) {		
			for (int j = 0; j < size; j++) {
				
				//Create submatrix from parent matrix
				child[i][j] = parent[i + rowOffset][j + colOffset];
			}
		}
	}
	
	/**
	* Join submatrices into parent matrix for the conquer step of Strassen Algorithm
	* Child matrix has dimensions size x size
	* 
	* @param parent is 2D array of source matrix
	* @param child is 2D array for submatrix
	* @param rowOffset is how much to move row index for submatrix
	* @param colOffset is how much to move column index for submatrix
	*/
	
	private static void joinMatrix(int[][] child, int[][] parent, int rowOffset, int colOffset) {
		
		int size = child.length;
		
		//Copy submatrix of size x size
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				//Create parent matrix from submatrix
				parent[i + rowOffset][j + colOffset] = child[i][j];
			}
		}
	}
	
	/**
	 * Perform addition on two Matrix 2D arrays
	 * 
	 * @param Matrix A 2D array
	 * @param Matrix B 2D array
	 * @result 2D array of sums
	 */
	
	private static int[][] addMatrix(int[][] A, int[][] B) {
		
		//Size of matrix
		int n = A.length;
		
		//Initialize 2D array for sums
		int [][] result = new int[n][n];
		
		//Add values and return as 2D array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = A[i][j] + B[i][j];
			}
		}
		return result;
	}
	
	/**
	 * Perform subtraction on two Matrix 2D arrays
	 * 
	 * @param Matrix A 2D array
	 * @param Matrix B 2D array
	 * @result 2D array of differences
	 */
	
	private static int[][] subtractMatrix(int[][] A, int[][] B) {
		
		//Size of matrix
		int n = A.length;
		
		//Initialize 2D array of differences
		int [][] result = new int[n][n];
		
		//Subtract values and return as 2D array
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = A[i][j] - B[i][j];
			}
		}
		return result;
	}
}