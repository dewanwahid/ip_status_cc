package signedJung;

public class testingGraphElements {

	public static void main(String[] args) {

		mySignedDiGraph g;
		g = ReadData.fromFile("signedDigraph-Fig-6b.txt");

		System.out.println(g);

	}
}
