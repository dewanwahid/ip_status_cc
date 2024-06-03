package dataReading;

import java.io.File;
import java.io.IOException;
import jxl.Cell; 
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class ReadData {

//	public static void main(String[] args) throws IOException {
//		ReadData rd = new ReadData();
//		String inputFile = ""; //"C:/status_balance_tri_01.xls";
//		rd.read(inputFile,null,null);
//	} 

	
	public static void read(String inputFile, int [][] arc, int[][] weight) throws IOException  {

		File inputWorkbook = new File(inputFile);
		Workbook w = null;

		NumberCell nc;//for cell having Numeric data


		try {

			w = Workbook.getWorkbook(inputWorkbook);
			
			// Get the first sheet
			Sheet sheet = w.getSheet(0);

			// Define no. of nodes, arcs and arc's weight
//			int n = sheet.getColumns() ;
//			arc = new int [n][n];
//			weight = new int [n][n];
			
				
			// Loop over arcDestination (Column in Data Table)
			for (int arcDestination = 0; arcDestination < sheet.getColumns(); arcDestination++) {

				// Loop over arcOrigin (Rows in Data Table) 
				for (int arcOrigin = 0; arcOrigin < sheet.getRows(); arcOrigin++) {
					
					Cell cell = sheet.getCell(arcDestination, arcOrigin); // (Column, Row)

					// Define: c[arcOrigin][arcDestination] = 1, if there is an arc and 0 otherwise
					if (cell.getType() == CellType.NUMBER){
						
						nc = (NumberCell)cell;
						weight[arcOrigin][arcDestination] = (int) Math.abs(nc.getValue());

						if (weight[arcOrigin][arcDestination] != 0) {
							arc[arcOrigin][arcDestination] = (int) (nc.getValue()/weight[arcOrigin][arcDestination]);
						}
						
						else {
							arc[arcOrigin][arcDestination] = 0;
						}
						
						// (data check) print arc and weight 
//						System.out.println("arc "+ "[" + arcOrigin + "][" + arcDestination + "] = " + arc[arcOrigin][arcDestination]);
//						System.out.println("weight "+ "[" + arcOrigin + "][" + arcDestination + "] = " + weight[arcOrigin][arcDestination]);
					}
				}	
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		} finally{
			w.close();
		}
	}
} 