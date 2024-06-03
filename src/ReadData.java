package signedJung;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadData {

	public static mySignedDiGraph fromFile(String filename) {
		
		System.out.println("DataName: " + filename);
		mySignedDiGraph g = new mySignedDiGraph();
		FileInputStream f = null;
		
		try {
			f = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(f);
		
		while(s.hasNext()){
			String graphDataLine = s.nextLine();
			
			// avoid to read starting comments of data
			if (graphDataLine.charAt(0) == '#') {
				continue;
			}
			
			// split the line 
			String[] line = graphDataLine.split("\t");
			
			// all data line should have 3 columns 
			if (line.length != 3) {
				System.out.println("Critical read error. Found line:\n"  + graphDataLine);
				System.exit(0);
			}
			
			// 1st column is source node
			int src = Integer.parseInt(line[0]);
//			System.out.println("read vertex " + src);
			
			// 2nd column is target node
			int target = Integer.parseInt(line[1]);
			
			// 3rd column is vote 
			int vote = Integer.parseInt(line[2]);
			
//			System.out.println("Trying to add " + src+":"+target+":"+vote);
			g.addEdge(src+":"+target+":"+vote, src,target,vote);		
		}
		return g;
	}

}
