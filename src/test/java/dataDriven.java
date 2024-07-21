import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	public ArrayList<String> getdata(String testcaseName,String SheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\vishal.saini_thought\\Documents\\DummyData.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		
		ArrayList<String> a =new ArrayList<String>();
		
		int sheets = workbook.getNumberOfSheets();
		
		for(int i=0;i<sheets;i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(SheetName))
			{
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			  Iterator<Row> rows=sheet.iterator();
			  Row FirstRow = rows.next();
			  
			  Iterator<Cell> cell = FirstRow.cellIterator();
			  int k=0;
			  int column=0;
			  while(cell.hasNext())
			  {
				 Cell value = cell.next();
				 if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
				 {
					 column=k;
				 }
				 k++;
			  }
			  System.out.println("column at "+column);
			  
			  while(rows.hasNext())
			  {
				  Row r = rows.next();
				  if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
				  {
					  Iterator<Cell> cr = r.cellIterator();
					  while(cr.hasNext())
					  {
						  Cell c = cr.next();
						  if(c.getCellType()==CellType.STRING)
						  {
							  a.add(c.getStringCellValue());  
						  }
						  else
						  {
							  a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						  }
						  
					  }
				  }
			  } 
			} 
			  
		}
		return a; 
	}

	public static void main(String[] args) throws IOException {
		
		
		
		
		
		
				
		
		
		
	}
		

}
