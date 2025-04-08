package Utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginDetails")
    public String[][] getData() throws IOException {

        String path = "./testData/OpneCartLoginData.xlsx"; //taking excel file from project
        Excelutility utils = new Excelutility(path); //Cerating object for ExcelUtility

        int tolalRow= utils.getRowCount("Sheet1");
        int totalCol= utils.getCellCount("Sheet1",1);

        String loginData[][] = new String[tolalRow][totalCol]; //created for 2 dimention array
        for(int i=1;i<tolalRow;i++){ //1  //read data from excel and storing it at i
            for (int j=0;j<totalCol;j++){ //0  i is row and j is col
              loginData[i-1][j]= utils.getCellData("Sheet1",i,j); //1,0
            }
        }

        return loginData; // return 2 dimention array
    }
}
