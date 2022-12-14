package com.Ecommerce.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.CheckOutPage;
import com.Ecommerce.java.ConfirmationPage;
import com.Ecommerce.java.ProductCatalog;

public class EndToEndTest extends BaseTest{

	DataFormatter formatter=new DataFormatter(); 
	@Test(dataProvider="ReadExcelData") 
	public void  endtoEndFunction(String name ,String pwd,String Prod) throws InterruptedException {
		ProductCatalog productcatalog = landingpage.loginApplication(name,pwd);
		productcatalog.getProductsList();
		productcatalog.addProductToCart(Prod);
		System.out.println(Prod);
		CartPage cartpage = productcatalog.gotoCartPage();
		cartpage.getCartProductList();
		Boolean match = cartpage.fetchSpecificProduct(Prod);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage conformationpage = checkoutpage.Placeorder();
		String text = conformationpage.getConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", text);
	}
	@DataProvider(name="ReadExcelData")
	public Object[][] excelDataRead() throws IOException {
		FileInputStream fis=new FileInputStream("E:\\Eclipse_workspace\\Project\\src\\test\\java\\com\\Ecommerce\\Resources\\LoginDataExcel.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("logindata");
		int rowcount=sheet.getPhysicalNumberOfRows();
		XSSFRow row=sheet.getRow(0);
		int columncount=row.getLastCellNum();
		Object[][] data=new Object[rowcount-1][columncount];
		for(int i=0;i<rowcount-1;i++) {
			row=sheet.getRow(i+1);
			for(int j=0;j<columncount;j++) {
				XSSFCell cell=row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
				System.out.println(data[i][j]);
			}
		}
		return data;
	}
}
