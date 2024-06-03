package signedJung;

public class Edge {
	
	public int src;
	public int target;
	public int vote;
	
	public Edge(int src1, int target1, int vote1) {
		src = src1;
		target = target1;
		vote = vote1;
	}
	
	public String toString() {
		return "src="+src+", target="+ target +" and vote=" + vote;
	}
	
	public int getVote() {
		return vote;
	}
	
	public int getSrc(){
		return this.src;
	}
	
	public int getTarget(){
		return this.target;
	}

	public boolean equals(Object o) {
		Edge e = (Edge)o;
		if (src == e.src && target == e.target) return true;
		return false;
	}
}
