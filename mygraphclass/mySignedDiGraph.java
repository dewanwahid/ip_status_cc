package signedJung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

@SuppressWarnings("serial")
public class mySignedDiGraph extends DirectedSparseGraph<Integer,String> {
	HashMap<ArrayList<Integer>,Integer> map;
	
	public mySignedDiGraph() {
		//g = new DirectedSparseGraph<Integer,String>();
		map = new HashMap<ArrayList<Integer>,Integer>();
	}

	//add edge to graph
	public void addEdge(String s, int src, int trg, int vote) {
		addEdge(s, src,trg);
		ArrayList<Integer> pair = new ArrayList<Integer>(); // pair.get(0) = src, pair.get(1) = trg
		pair.add(src);
		pair.add(trg);
		map.put(pair, vote);
	}
	
	//get vote (sign) for each edge (inputs source and target nodes)
	public int getVote(int src, int trg) {
		ArrayList<Integer> pair = new ArrayList<Integer>(); // pair.get(0) = src, pair.get(1) = trg
		pair.add(src);
		pair.add(trg);
		if (map.containsKey(pair)) {
			return (map.get(pair)/Math.abs(map.get(pair)));
		}
		else return 0;
	}
	
	//get vote (sign) for each edge (input each edge)
	public int getVote(String e) {
		String[] parts = e.split(":");
		return (Integer.parseInt(parts[2])/Math.abs(Integer.parseInt(parts[2])));
	}
	
	
	//get non-negative weight of an edge (inputs source and target nodes)
	public int getEdgeWeight(int src, int trg) {
		ArrayList<Integer> pair = new ArrayList<Integer>(); // pair.get(0) = src, pair.get(1) = trg
		pair.add(src);
		pair.add(trg);
		if (map.containsKey(pair)) {
			return Math.abs(map.get(pair));
		}
		else return 0;
	}
	
	//get non-negative weight of an edge (input each edge)
	public int getEdgeWeight(String e) {
		String[] parts = e.split(":");
		return Math.abs(Integer.parseInt(parts[2]));
	}
	
	//for an edge return source
	public int getSrc(String e) {
		String[] parts = e.split(":");
		return Integer.parseInt(parts[0]);
	}
	
	//for an edge retrun target
	public int getTrg(String e) {
		String[] parts = e.split(":");
		return Integer.parseInt(parts[1]);
	}
	
	//get the positive edges list
	public ArrayList<String> getPositiveEdges(){
		ArrayList<String> positiveEdges = new ArrayList<String>();
		
		for (String e : getEdges()) {
			if (getVote(e) > 0 ) {
				positiveEdges.add(e);
			}
			else continue;
		}
		return positiveEdges;
	}
	
	//get the negative edges list
	public ArrayList<String> getNegativeEdges(){
		ArrayList<String> negativeEdges = new ArrayList<String>();
		
		for (String e : getEdges()) {
			if (getVote(e) < 0 ) {
				negativeEdges.add(e);
			}
			else continue;
		}
		return negativeEdges ;
	}
	
	//count total number of positive triangle participate by an edge
	public int countPositiveTriangle(int src, int trg) {
		int pTriangle = 0;
		Collection<Integer> NbrsOfSrc = getNeighbors(src);
		Collection<Integer> NbrsOfTrg = getNeighbors(trg);
		
		for (Integer s : NbrsOfTrg) {
			int triVote = 0;
			if (NbrsOfSrc.contains(s)){
				//cast opinion vote flow 'src' to 'trg' to 's' to 'src'
				triVote = getVote(src,trg) + getVote(trg,s) - getVote(s,trg) + getVote(s,src) - getVote(src,s);
			}
			else continue;
			
			if (-3 < Math.abs(triVote) && Math.abs(triVote) < 3 ) {
				pTriangle = pTriangle + 1;
			}
			else continue;
		}
		return pTriangle ;	
	}
	
	//count total number of negative triangle participate by an edge
	public int countNegativeTriangle(int src, int trg) {
		int nTriangle = 0;
		
		Collection<Integer> NbrsOfSrc = getNeighbors(src);
		Collection<Integer> NbrsOfTrg = getNeighbors(trg);
		
		for (Integer s : NbrsOfTrg) {
			int triVote = 0;
			if (NbrsOfSrc.contains(s)){
				
				//cast opinion vote flow 'src' to 'trg' to 's' to 'src'
				triVote = getVote(src,trg) + getVote(trg,s) - getVote(s,trg) + getVote(s,src) - getVote(src,s);
			}
			else continue;
			
			if (-3 ==  Math.abs(triVote) || Math.abs(triVote) == 3 ) {
				nTriangle = nTriangle + 1;
			}
			else continue;
		}
		return nTriangle ;	
	}
	
	//for any three vertices check is the triangle positive? 
	//if 1 yes, 0 no
	public int isPositiveTriangle(int i, int j, int p) {
		int triVote = 0;
		int tf = 0;
		Collection<Integer> NbrsOfi = getNeighbors(i);
		Collection<Integer> NbrsOfj = getNeighbors(j);
		
		if (NbrsOfi.contains(j) && NbrsOfi.contains(p)) {
			if (NbrsOfj.contains(p)) {
				triVote = getVote(i,j) -getVote(j,i) + getVote(j,p) - getVote(p,j) + getVote(p,i) - getVote(i,p);
				if (-3 < Math.abs(triVote) && Math.abs(triVote) < 3) {
					tf= 1;
				}
			}
			else tf= 0;
		}
		return tf;
	}
	
	//for any three vertices check is the triangle negative?
	//if 1 yes, 0 no
	public int isNegativeTriangle(int i, int j, int p) {
		int triVote = 0;
		int tf = 0;
		Collection<Integer> NbrsOfi = getNeighbors(i);
		Collection<Integer> NbrsOfj = getNeighbors(j);
		
		if (NbrsOfi.contains(j) && NbrsOfi.contains(p)) {
			if (NbrsOfj.contains(p)) {
				triVote = getVote(i,j) -getVote(j,i) + getVote(j,p) - getVote(p,j) + getVote(p,i) - getVote(i,p);
				if (-3 ==  Math.abs(triVote) || Math.abs(triVote) == 3) {
					tf = 1;
				}
			}
			else tf = 0;
		}
		
		return tf;
	}
	
	//for any three vertices check is the triangle type
	//if returns 1 then positiv, 0 no triangle and -1 then negative triangle
	public int triangleTyep(int i, int j, int p) {
		int triVote = 0;
		int triType = 0;
		Collection<Integer> NbrsOfi = getNeighbors(i);
		Collection<Integer> NbrsOfj = getNeighbors(j);
		
		if (NbrsOfi.contains(j) && NbrsOfi.contains(p)) {
			if (NbrsOfj.contains(p)) {
				triVote = getVote(i,j) -getVote(j,i) + getVote(j,p) - getVote(p,j) + getVote(p,i) - getVote(i,p);
				if (-3 ==  Math.abs(triVote) || Math.abs(triVote) == 3) {
					triType = -1;
				}
				else if (-3 < Math.abs(triVote) && Math.abs(triVote) < 3) {
					triType = 1;
				}
			}
			else triType = 0;
		}
		return triType;
	}
	
	//get total edge weight in a positive triangle
	public int getEdgeTotalPosTriWeight(int src, int trg) {
		int totalPosTriWeight = 0;
		
		Collection<Integer> NbrsOfSrc = getNeighbors(src);
		Collection<Integer> NbrsOfTrg = getNeighbors(trg);
		
		for (Integer s : NbrsOfTrg) {
			int triVote = 0;
			if (NbrsOfSrc.contains(s)){
				
				//cast opinion vote flow 'src' to 'trg' to 's' to 'src'
				triVote = getVote(src,trg) + getVote(trg,s) - getVote(s,trg) + getVote(s,src) - getVote(src,s);
			}
			else continue;
			
			if (-3 < Math.abs(triVote) && Math.abs(triVote) < 3 ) {
				totalPosTriWeight = getEdgeWeight(src,trg) + getEdgeWeight(trg,s) + getEdgeWeight(s,trg) + getEdgeWeight(s,src) + getEdgeWeight(src,s);
			}
			else continue;
		}
		
		return totalPosTriWeight;
		
	}
	
	
	//get total edge weight in a negative triangle
	public int getEdgeTotalNegTriWeight(int src, int trg) {
		int totalNegTriWeight = 0;
		
		Collection<Integer> NbrsOfSrc = getNeighbors(src);
		Collection<Integer> NbrsOfTrg = getNeighbors(trg);
		
		for (Integer s : NbrsOfTrg) {
			int triVote = 0;
			if (NbrsOfSrc.contains(s)){
				
				//cast opinion vote flow 'src' to 'trg' to 's' to 'src'
				triVote = getVote(src,trg) + getVote(trg,s) - getVote(s,trg) + getVote(s,src) - getVote(src,s);
			}
			else continue;
			
			if (-3 ==  Math.abs(triVote) || Math.abs(triVote) == 3 ) {
				totalNegTriWeight = getEdgeWeight(src,trg) + getEdgeWeight(trg,s) + getEdgeWeight(s,trg) + getEdgeWeight(s,src) + getEdgeWeight(src,s);
			}
			else continue;
		}
		return totalNegTriWeight ;
	}
	
	//get total edge status weight = (total positive triangle - total negative triangle) weight 
	//participated by and edge (src, trg)
	public int getEdgeStatusWeight(int src, int trg) {
		int edgeStatusWeight = 0;
		edgeStatusWeight = getEdgeTotalPosTriWeight(src, trg) - getEdgeTotalNegTriWeight(src, trg);
		return edgeStatusWeight;
	}
	
	//get total edge status weight = (total positive triangle - total negative triangle) weight 
	//participated by and edge e
	public int getEdgeStatusWeight(String e) {
		int edgeStatusWeight = 0;
		int src = getSrc(e);
		int trg = getTrg(e);
		edgeStatusWeight = getEdgeTotalPosTriWeight(src, trg) - getEdgeTotalNegTriWeight(src, trg);
		return edgeStatusWeight;
	}
}
