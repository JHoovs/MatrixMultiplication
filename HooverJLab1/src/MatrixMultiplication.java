
/**
 * I used this code as a reference for the reset and get counter functions
 * when tracking comparisons for both algorithms.
 * 
 * Reference:
 * https://www.seas.upenn.edu/~cis1xx/resources/drjava/primer/Counter.java
 */

public class MatrixMultiplication {
	
	/**
	 * Three functions to initialize multiplication counter to 0, reset between 
	 * each iteration in Main, and count multiplications in algorithm.
	 */
	
	private static int naiveMultiplicationCounter = 0;

	public static void resetCounter() {
		naiveMultiplicationCounter = 0;
	}
	
	public static int getCounter() {
		return naiveMultiplicationCounter;
	}
	
	/**
	 * Naive Matrix Multiplication algorithm to multiply Matrix A and Matrix B.
	 * 
	 * @param Matrix A 
	 * @param Matrix B
	 * @return Matrix C
	 */
	
	public static int[][] naiveMultiply(int[][] A, int[][] B) {
		//Size of matrix
		int n = A.length;
		
		//Initialize 2D array for result
		int[][] C = new int[n][n];

		//iterate rows
		for (int i = 0; i < n; i++) {
			
			//iterate values in rows
			for (int j = 0; j < n; j++) {
				
				//Initialize result 2D array
				C[i][j] = 0;
				
				//Perform Matrix Multiplication
				for (int k = 0; k < n; k++) {
					C[i][j] += A[i][k] * B[k][j];
					naiveMultiplicationCounter++;
				}
			}
		}
		return C;
	}
	
}

