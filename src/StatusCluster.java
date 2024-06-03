package statusBalance;


import java.util.ArrayList;
import java.util.Collection;

import signedJung.mySignedDiGraph;
import signedJung.ReadData;
import ilog.concert.*;
import ilog.cplex.*;

public class StatusCluster {

	//main method
	public static void main(String [] args) {
		mySignedDiGraph g = ReadData.fromFile("signedDigraph-Fig-6a.txt");

		//run method to identify partition in status-clusters
		getStatusCluster(g);

	}//end-main-method


	private static void getStatusCluster( mySignedDiGraph g) {

		//print graph data
		//System.out.println(g);
		int n = (Integer) g.getVertexCount() + 1;
		System.out.println("Total Nodes: " + (n-1));
		System.out.println("Total Edges: " + g.getEdgeCount());
		System.out.println("Total Positive Edges: " + g.getPositiveEdges().size());
		System.out.println("Total Negative Edges: " + g.getNegativeEdges().size());

		//ILP formulation using ilog.cplex
		try {
			IloCplex cplex = new IloCplex();

			//binary variables x for each i,j,p in V
			IloNumVar[][][] x = new IloNumVar[n][n][];
			for (int i=1; i<n; i++) {
				for (int j=1; j<n; j++) {
					x[i][j] = cplex.boolVarArray(n);
				}
			}

			//binary variables y for each i,j in V
			IloNumVar[][] y = new IloNumVar[n][];
			for (int src = 1; src < n; src++) {
				y[src] = cplex.boolVarArray(n);
			}

			//dummy variable
			y[0] = cplex.boolVarArray(2); // x[0][0] will be forced F, and x[0][1] forced T, to use as constants

			//create objective
			IloLinearNumExpr objective = cplex.linearNumExpr();

			//part 01 & 02 (modified)
			for (String e: g.getEdges()) {
				int edgeWeight = g.getEdgeWeight(e);
				int i = g.getSrc(e);
				int j = g.getTrg(e);
				Collection<Integer> NbrsOfSrc = g.getNeighbors(i);
				Collection<Integer> NbrsOfTrg = g.getNeighbors(j);

				for (int p : NbrsOfTrg){
					if (NbrsOfSrc.contains(p)) {
						
						//if the triangle formed by i,j,p is a negative triangle
						//then error occurs, if i,j,p (all of them) are in the same cluster
						if (g.triangleTyep(i, j, p) == -1) {
							objective.addTerm(edgeWeight, y[0][1]);     //here x[0][1] = 1 is a dummy variable
							objective.addTerm((-1 * edgeWeight), x[i][j][p]);
						}

						//if the triangle formed by i,j,p is a positive triangle
						//then error occurs, if i,j,p (any one of them) are in different clusters
						if (g.triangleTyep(i, j, p) == 1){
							objective.addTerm(edgeWeight, x[i][j][p]);
						}
						else continue;
					} else continue;
				}
			}

			//part 03
			//if a negative edge does not participate in any triangle and inside a cluster
			//then an error occurs
			for (String e: g.getNegativeEdges()) {
				int edgeWeight = g.getEdgeWeight(e);
				int posTriCount = g.countPositiveTriangle(g.getSrc(e), g.getTrg(e));
				int negTriCount = g.countNegativeTriangle(g.getSrc(e), g.getTrg(e));

				if (negTriCount == 0 && posTriCount == 0) {
					objective.addTerm(edgeWeight, y[0][1]);
					objective.addTerm((-1* edgeWeight), y[g.getSrc(e)][g.getTrg(e)]);
				}
			}

			//part 04
			//if a positive does not participate in any triangle and between two clusters
			//then an error occurs
			for (String e: g.getPositiveEdges()) {
				int edgeWeight = g.getEdgeWeight(e);
				int posTriCount = g.countPositiveTriangle(g.getSrc(e), g.getTrg(e));
				int negTriCount = g.countNegativeTriangle(g.getSrc(e), g.getTrg(e));
				
				if (negTriCount == 0 && posTriCount == 0) {
					objective.addTerm(edgeWeight, y[g.getSrc(e)][g.getTrg(e)]);
				}
			}

			//define objective
			cplex.addMinimize(objective);

			//add dummy constraints
			cplex.addEq(y[0][0], 0);
			cplex.addEq(y[0][1], 1);

			//constraint 01, 02, 03 and 04
			for (int i=1; i<n; i++) {
				for (int j=1; j<n; j++) {
					for (int p=1; p<n; p++) {

						IloLinearNumExpr expr = cplex.linearNumExpr();
						IloLinearNumExpr expr1 = cplex.linearNumExpr();
						IloLinearNumExpr expr2 = cplex.linearNumExpr();
						IloLinearNumExpr expr3 = cplex.linearNumExpr();

						if (i!=j && j!=p && i!=p) {
							//constraint 01
							// "0<= Yij + Yip + Yjp - Xijp <= 2"
							expr.addTerm(1.0, y[i][j]);
							expr.addTerm(1.0, y[i][p]);
							expr.addTerm(1.0, y[p][j]);
							expr.addTerm((-1.0), x[i][j][p]);
							cplex.addLe(expr, 2);
							cplex.addGe(expr,0);

							//constraint 02
							//"XijP - Yij >= 0"
							expr1.addTerm(1.0, x[i][j][p]);
							expr1.addTerm(-1.0, y[i][j]);
							cplex.addGe(expr1, 0);

							//constraint 03
							//"XijP - Yip >= 0"
							expr2.addTerm(1.0, x[i][j][p]);
							expr2.addTerm(-1.0, y[i][p]);
							cplex.addGe(expr2, 0);

							//constraint 04
							//"XijP - Yjp >= 0"
							expr3.addTerm(1.0, x[i][j][p]);
							expr3.addTerm(-1.0, y[p][j]);
							cplex.addGe(expr3, 0);
						}
					}
				}
			}

			//constraint 05
			//"Yip + Ypj >= Yij"
			for (int i=1; i<n ; i++) {
				for (int p=1; p<n; p++) {
					for (int j=1; j<n; j++) {
						cplex.addGe(cplex.sum(y[i][p], y[p][j]), y[i][j]);
					}
				}
			}

			// constraint 06
			//"Yij = Yji" 
			for (int i=1; i<n; i++) {
				for (int j=1; j<n; j++) {
					cplex.addEq(y[i][j], y[j][i]);
				}
			}

			//solve model
			//System.out.println(cplex);
			if(cplex.solve()) {

				//print objective value
				System.out.println("Objective = " + cplex.getObjValue());

				//print clusters
				ArrayList<ArrayList<Integer>> cluster = new ArrayList<ArrayList<Integer>>();
				for (int i=1; i<n; i++) {
					ArrayList<Integer> thisCluster = new ArrayList<Integer>();
					for (int j=1; j<n; j++) {
						if( cplex.getValue(y[i][j]) == 0) {

							if (thisCluster.contains(j)){
								continue;
							}
							else {
								thisCluster.add(j);
							}
						}
					}
					if (cluster.contains(thisCluster)) {
						continue;
					}
					else {
						cluster.add(thisCluster);
					}
				}
				System.out.println("Clusters: "  + cluster);
			}
			else {
				System.out.println("Model not solved!!");
			}
		} catch (IloException e) {
			e.printStackTrace();
		}
	} //end-getStatusCluster()
}
