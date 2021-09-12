package pkg1;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataDrivenTest {
	
	/*@Test(dataProvider="method1")	//calling the data with the help of DataProvider
	public void test1(String user, String pass) {
		System.out.println(user);
		System.out.println(pass);		
	}*/	
	
	@Test(dataProvider="method2")	//calling the data with the help of DataProvider
	public void test1(String user, String pass) {
		System.out.println(user);
		System.out.println(pass);		
	}
	@DataProvider					//by hard coding the data 
	public Object[][] method1() {
		Object[][] ob = new Object[3][2]; // [row][col]
		ob[0][0] = "user1";
		ob[0][1] = "pass1";
		
		ob[1][0] = "user2";
		ob[1][1] = "pass2";
		
		ob[2][0] = "user3";
		ob[2][1] = "pass3";
		return ob;
	}
	
	@DataProvider					//by calling the data from excel file
	public Object[][] method2() throws BiffException, IOException {
		File f = new File("../DataDrivenTesting/DataDrivenData.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet ws = wb.getSheet(0);
		int r = ws.getRows();
		int c = ws.getColumns();
		
		Object[][] ob = new Object[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				Cell wc = ws.getCell(j, i);
				ob[i][j]=wc.getContents();
				}
		}
		return ob;		
	}
	
}
