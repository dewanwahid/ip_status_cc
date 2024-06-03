package statusCoefficient;

import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;

import dataReading.ReadData;

public class countStatusCoeff {
	
	static String data = "C:/status_balance_tri_10.xls";
	static int N = 3;
	static int nodes = 0;
	static double statusCoefficient = 0;
	static int  weightedStatus = 0;
	static int totalStatus = 0;
	static int[] wStatus = null;
	static int[] tStatus = null;
	
	
	public static void main(String[] args){
		
		int [][] arc = new int [N][N];
		int [][] weight = new int [N][N];
		
		try {
			ReadData.read(data, arc, weight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in data reading!!");
			e.printStackTrace();
		} //end data reading
		
		nodes = arc.length;
		// print nodes numbers 
		System.out.println("Number of Nodes: " + nodes);
		
		System.out.println("\nStatus Coefficients: ");
		
		// count status for every node
		for (int i=0; i < N; i++) {
			countStatus(arc, weight, i);

			// compute SC for each node
			statusCoefficient = (double) weightedStatus / (double)totalStatus;
			
			// print status coefficient SC(v)
			System.out.println("SC(" + i + ") = " + statusCoefficient);
		}
		
	} // end main method


	private static void countStatus(int[][] arc, int[][] weight, int i) {
		// TODO Counting status coefficient for each node
		
		int  wStatus = 0;
		int  tStatus = 0;
		
		for(int j=0; j<N; j++){
		
				if (arc[i][j] == 1) {
					wStatus  = wStatus + (-1 * weight[i][j]); 
					tStatus =  tStatus + weight[i][j];
				}
				else if (arc[i][j] == -1) {
					wStatus = wStatus + (1 * weight[i][j]); 
					tStatus =  tStatus +  weight[i][j];
				}
				
				if (arc[j][i] == 1) {
					wStatus = wStatus + (1 * weight[j][i]); 
					tStatus =  tStatus + weight[j][i];
				}
				else if (arc[j][i] == -1) {
					wStatus = wStatus + (-1 * weight[j][i]); 
					tStatus =  tStatus + weight[j][i];
				}
			
		} // end for-loop
		
		weightedStatus = wStatus;
		totalStatus = tStatus;
		
	} // end countStatus

} // end class
