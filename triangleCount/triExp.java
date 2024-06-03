package triangleCount;

public class triExp {



	public static int countPosTriangle(int[][] arc, int i, int j){
		// this method should only get called when i->j in arc
		int count=0;

		for (int p=0; p<arc.length; p++){
			if (p==i || p==j) continue;
			int temp = 0;
			temp = score(arc, i,j) + score(arc, j,p) + score(arc, p,i);
			if (temp == 3 || temp == -3) continue;
			else count++;
		}

		return count;
	}

	public static int countNegTriangle(int[][] arc, int i, int j){
		// this method should only get called when i->j in arc
		int count=0;

		for (int p=0; p<arc.length; p++){
			if (p==i || p==j) continue;
			int temp = 0;
			temp = score(arc, i,j) + score(arc, j,p) + score(arc, p,i);
			if (temp == 3 || temp == -3) count++;
		}

		return count;
	}

}
