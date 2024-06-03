package triangleCount;

import java.io.IOException;
import dataReading.ReadData;


public class PositiveNegativeTriangleCount {

	// initialization 
	static String data = "C:/status_balance_tri_05.xls";
	static int N = 3;

	// main method
	public static void main(String[] args) {

		int arc [][] = new int [N][N];
		int weight [][] = new int [N][N];
		int triPlus[][] = new int [N][N];
		int triMinus[][] = new int [N][N];

		// read data using ReadData class
		try {
			ReadData.read(data, arc, weight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in data reading!!");
			e.printStackTrace();
		}

		// print the input data size
		System.out.println("Data Name: " + data);
		System.out.println("Array of Size: " + arc.length + " x " + arc[0].length);

		// count (+) and (-) triangle associated with each arc
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (arc[i][j] != 0) {

					// run countTrangle only for an arc
					System.out.println("\narc[" + i + "]" + "[" + j + "] = " + arc[i][j]);
					countTriangle(data, arc, weight,i, j, triPlus,triMinus);

					// print results 					
					System.out.println("triPlus[" + i +"]" + "["+j+"]"+" = " + triPlus[i][j]);
					System.out.println("triMinus[" + i +"]" + "["+j+"]"+" = " + triMinus[i][j]);
				}
			}
		}
		
	}// end-main-method


	public static void countTriangle(String data, int[][] arc, int [][] weight, 
			int i, int j, int [][] triPlus, int [][] triMinus ){
		// TODO counting positive and negative triangle associated with each arc

		// create local objects
		int [][] dArc = new int [N][N];
		int [][] pT = new int [N][N];
		int [][] nT = new int [N][N];

		// for positive arc
		if (arc[i][j] > 0) {

			for (int p=0; p<N; p++){

				if ((arc[i][p]!=0 || arc[p][i]!=0) && (arc[j][p]!=0 || arc[p][j]!=0)) {

					// if there is an arc then opposite direction is in opposite sign 
					if (arc[i][p] == 0 && arc[p][i]!=0){
						dArc[i][p] = - arc[p][i];
						dArc[p][i] = arc[p][i];
					}

					else if (arc[p][i]== 0 && arc[i][p] != 0) {
						dArc[p][i] = - arc[i][p];
						dArc[i][p] = arc[i][p];
					}

					if (arc[p][j] == 0 && arc[j][p]!=0){
						dArc[p][j] = - arc[j][p];
						dArc[j][p] = arc[j][p];
					}

					else if (arc[j][p]== 0 && arc[p][j] != 0) {
						dArc[j][p] = - arc[p][j];
						dArc[p][j] = arc[p][j];
					}

					// count the positive and negative triangle associated with a positive arc
					if ((dArc[i][p] + dArc[p][j]) >= 0) {
						pT[i][j] = pT[i][j] + 1;
					}
					else {
						nT[i][j] = nT[i][j] + 1;
					}
				}	
			}

		} // end-if: the arc is positive 

		// for negative arc
		else if (arc[i][j] < 0) {

			for (int p=0; p<N;p++) {

				if ((arc[i][p]!=0 || arc[p][i]!=0) && (arc[j][p]!=0 || arc[p][j]!=0)) {

					// if there is an arc then opposite direction is in opposite sign 
					if (arc[i][p] == 0 && arc[p][i]!=0){
						dArc[i][p] = - arc[p][i];
						dArc[p][i] = arc[p][i];
					}

					else if (arc[p][i]== 0 && arc[i][p] != 0) {
						dArc[p][i] = - arc[i][p];
						dArc[i][p] = arc[i][p];
					}

					if (arc[p][j] == 0 && arc[j][p]!=0){
						dArc[p][j] = - arc[j][p];
						dArc[j][p] = arc[j][p];
					}

					else if (arc[j][p]== 0 && arc[p][j] != 0) {
						dArc[j][p] = - arc[p][j];
						dArc[p][j] = arc[p][j];
					}

					// count the positive and negative triangle associated with a positive arc
					if ((dArc[i][p] + dArc[p][j]) <= 0) {
						pT[i][j] = pT[i][j] + 1;
					}
					else {
						nT[i][j] = nT[i][j] + 1;
					}
				}
			}

		} // end-else if : the arc is negative

		triPlus[i][j] = pT[i][j];
		triMinus[i][j] = nT[i][j];

	} // end-countTriangle

} // end-class
