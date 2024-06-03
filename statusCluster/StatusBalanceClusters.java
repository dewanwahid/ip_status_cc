//package statusCluster;
//
//import java.io.IOException;
//
//import dataReading.ReadData;
//import triangleCount.PositiveNegativeTriangleCount;
//import ilog.concert.*;
//import ilog.cplex.*;
//
//@SuppressWarnings("unused")
//public class StatusBalanceClusters {
//
//	// initialization 
//	static String data = "C:/status_balance_tri_01.xls";
//	static int N = 3;
//
//	// main method
//	public static void main (String [] args) {
//
//		int arc [][] = new int [N][N];
//		int weight [][] = new int [N][N];
//		int triPlus[][] = new int [N][N];
//		int triMinus[][] = new int [N][N];
//		int p = 0;
//		int q = 0;
//
//		// read data from triangleCount package
//		try {
//			ReadData.read(data, arc, weight);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		//PositiveNegativeTriangleCount.countTriangle(data, arc, weight, p, q, triPlus, triMinus);
//
//
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<N; j++) {
//				//System.out.println("Testing ("+i+","+j+")");
//				if (arc[i][j] != 0) {
//					triPlus[i][j] = PositiveNegativeTriangleCount.countPosTriangle(arc,i,j);
//					triMinus[i][j] = PositiveNegativeTriangleCount.countNegTriangle(arc,i,j);
//				}
//				//				System.out.println("\narc[" + i + "][" + j + "] = " + arc[i][j]);
//				//				System.out.println("weight[" + i + "][" + j + "] = " + weight[i][j]);
//				//				System.out.println("triPlus[" + i + "][" + j + "] = " + triPlus[i][j]);
//				//				System.out.println("triMinus[" + i + "][" + j + "] = " + triMinus[i][j]);
//			}
//		}
//
//		for (int i=0; i<N; i++) {
//			for (int j=0; j<N; j++) {
//								System.out.println("triPlus[" + i + "][" + j + "] = " + triPlus[i][j]);
////								System.out.println("triMinus[" + i + "][" + j + "] = " + triMinus[i][j]);
//			}
//		}
//
//		
//		//
//		//	private static void statusCluster(int[][] arc, int[][] weight,
//		//			int[][] triPlus, int[][] triMinus) {
//		//		// TODO Finding the status clusters
//		//		
//		//		try {
//		//			IloCplex cplex = new IloCplex();
//		//			
//		//			 //check the data reading
//		////			for (int p=0; p<N; p++) {
//		////				for (int q=0; q<N; q++) {
//		////					System.out.println("arc[" + p + "]" + "[" + q + "] = " + arc[p][q]);
//		////					System.out.println("weight[" + p + "]" + "[" + q + "] = " + weight[p][q]);
//		////					System.out.println("triPlus[" + p + "]" + "[" + q + "] = " + triPlus[p][q]);
//		////					System.out.println("triMinus[" + p + "]" + "[" + q + "] = " + triMinus[p][q]);
//		////				}
//		////			}
//		//			
//		//			
//		//			
//		//			// variables
//		//			IloNumVar[][] x = new IloNumVar[N][N];
//		//			
//		//			for (int q=0; q<N; q++){
//		//					x[q] = cplex.boolVarArray(N);
//		//			}
//		//			
//		//			// create objective function
//		//			IloLinearNumExpr obj = cplex.linearNumExpr();
//		//			for (int i=0; i<N; i++) {
//		//				for (int j=0; j<N; j++) {
//		//					
//		//					// objective 1
//		//					if (arc[i][j] > 0) {
//		//						obj.addTerm((weight[i][j]*triPlus[i][j]), x[i][j]);
//		//					}
//		//					
//		//					// objective 2
//		//					if (arc[i][j] < 0) {
//		//						IloNumVar expr1 = (IloNumVar) cplex.sum(1, cplex.prod(-1, x[i][j]));
//		//						obj.addTerm((weight[i][j]* triMinus[i][j]), expr1);
//		//					}
//		//					
//		//					// objective 3
//		//					if (arc[i][j] > 0) {
//		//						IloNumVar expr2 = (IloNumVar) cplex.sum(1, cplex.prod(-1, x[i][j]));
//		//						obj.addTerm((weight[i][j]* triMinus[i][j]), expr2);
//		//					}
//		//					
//		//				}
//		//			} // end objective creation
//		//			
//		//			
//		//			// Define Objective
//		//			cplex.addMinimize(obj);
//		//			
//		//			
//		////			// Constraint 1
//		////			for (int k=0; k<N; k++) {
//		////				for (int l=0; l<N; l++) {
//		////					
//		////					IloLinearNumExpr expr3 = cplex.linearNumExpr();
//		////					for (int m=0; m<N; m++) {
//		////						if ((l != m) && (m!= k) && (l!=k)) {
//		////							expr3.addTerm(x[k][m], x[m][l]);
//		////						}
//		////					}
//		////				}
//		////			} // end constraint 1
//		//			
//		//			
//		//		} catch (IloException e) {
//		//			// TODO Auto-generated catch block
//		//			e.printStackTrace();
//		//		}
//		//		
//		//	} // end: statusClsuter
//
//		
//	} // end: main method
//
//}
