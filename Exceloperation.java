package package1;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceloperation {


	private static XSSFWorkbook workbook;

	public static void main (String args[]) throws Exception
	{
		workbook = new XSSFWorkbook();
		XSSFSheet Sheet1=workbook.createSheet("Sheet123");

		int x=0;
		int j;

		for(int i=0;i<5;i++)
		{
			XSSFRow row = Sheet1.createRow(x);
			Cell cell1=row.createCell(0);
			cell1.setCellValue(i);		
			for(j=0;j<5;j++)
			{
				if(i<((x+j)-x))
				{
					row = Sheet1.createRow(x);
					System.out.println("new row created");
					Cell cell2=row.createCell(1);
					cell2.setCellValue(j);	
				}
				Cell cell2=row.createCell(1);
				cell2.setCellValue(j);	
				x++;
			}
		}
		FileOutputStream out= new FileOutputStream("C:\\Users\\yugandhar\\Desktop\\sampleworkbook.xlsx");
		workbook.write(out);
		out.close();
		System.out.println("Succsess");
	}


}
