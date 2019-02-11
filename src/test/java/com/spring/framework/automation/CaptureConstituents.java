package com.spring.framework.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Duvvuri on 17/01/2019.
 */
@Test
public class CaptureConstituents {


    @Test()
    public  void getIndices() throws ClassNotFoundException, SQLException, FileNotFoundException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SeleniumConfig.class);
        SeleniumCompoenents selComp = context.getBean(com.spring.framework.automation.SeleniumCompoenents.class);
        //selComp.browserMgmt.openBrowser("https://uk.investing.com/indices/investing.com-uk-100-components","hunit");
        selComp.browserMgmt.openBrowser(selComp.browserMgmt.getConstituents_url(), System.getProperty("browser"));
        String[] arrDates =null;
        Connection conn;
        try {


            arrDates = getMostRecentDates(selComp);
            selComp.browserMgmt.driver.navigate().to(selComp.browserMgmt.getConstituents_url());
            WebDriverWait wait = new WebDriverWait(selComp.browserMgmt.driver, 5000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cr1")));
            //List<WebElement> eles=  selComp.browserMgmt.driver.findElements(By.xpath("//table[@id='cr1']//tr"));
            List<WebElement> eles = selComp.browserMgmt.driver.findElements(By.xpath(selComp.browserMgmt.getWait_element()));


            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:"
                    + "db/" + selComp.browserMgmt.getDb() + ";shutdown=true"  // filenames
            );

            Date dt = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");


            String SQL = "UPDATE " + selComp.browserMgmt.getDb() + " SET ISVALID='N' where date='%1$s'";
            //select    t1.symbol , t1.close , t2.close , t1.high , t2.high , t1.low, t2.low  from nifty100 t1 , nifty100 t2 where t1.symbol=t2.symbol and t1.date='1603' and t2.date='1703' and t1.isvalid='Y' and t2.isvalid='Y'
            // SQL= String.format(SQL, sf.format(dt));
            SQL = String.format(SQL, arrDates[0]);
            Statement resetSt = conn.createStatement();
            resetSt.execute(SQL);

            for (int i = 1; i < eles.size(); i++) {
/*
           logger.info("Name:" + selComp.browserMgmt.driver.findElement(By.xpath("//table[@id='cr1']//tr["+i+"]/td[2]/a")).getText());
           logger.info("Close:" + selComp.browserMgmt.driver.findElement(By.xpath("//table[@id='cr1']//tr["+i+"]/td[3]")).getText());
           logger.info("High:" + selComp.browserMgmt.driver.findElement(By.xpath("//table[@id='cr1']//tr["+i+"]/td[4]")).getText());
           logger.info("Low:" + selComp.browserMgmt.driver.findElement(By.xpath("//table[@id='cr1']//tr["+i+"]/td[5]")).getText());
*/
                String Name = selComp.browserMgmt.driver.findElement(By.xpath(selComp.browserMgmt.getPrefix_element() + "[" + i + "]/td[2]/a")).getText();
                String Close = selComp.browserMgmt.driver.findElement(By.xpath(selComp.browserMgmt.getPrefix_element() + "[" + i + "]/td[3]")).getText();
                String High = selComp.browserMgmt.driver.findElement(By.xpath(selComp.browserMgmt.getPrefix_element() + "[" + i + "]/td[4]")).getText();
                String Low = selComp.browserMgmt.driver.findElement(By.xpath(selComp.browserMgmt.getPrefix_element() + "[" + i + "]/td[5]")).getText();
                String d = arrDates[0];
                String f = "%1$s\t%2$s\t%3$s\t%4$s\t%5$s";
                f = String.format(f, d, Name, Close, High, Low);
                String statement = "insert into " + selComp.browserMgmt.getDb() + " values('%1$s','%2$s','%3$s','%4$s','%5$s','Y')";
                statement = String.format(statement, d, Name.replace("'", ""), Close, High, Low);
                Statement st = conn.createStatement();
                int result =st.executeUpdate(statement);
                //Thread.sleep(100);
                SeleniumCompoenents.logger.info(i + "/" + eles.size()  + " : " + statement + " : " + result);

            }
            conn.close();
            SeleniumCompoenents.logger.info("Number Of Elements:" + eles.size());
            SeleniumCompoenents.logger.info("${constituents.url}" + selComp.browserMgmt.getConstituents_url());
            SeleniumCompoenents.logger.info("${prefix.element}" + selComp.browserMgmt.getPrefix_element());
            SeleniumCompoenents.logger.info("${historic.url}" + selComp.browserMgmt.getHistoric_url());
            SeleniumCompoenents.logger.info("${element.wait.date}" + selComp.browserMgmt.getElement_wait_date());
            SeleniumCompoenents.logger.info("${xpath.today}" + selComp.browserMgmt.getXpath_today());
        } catch (Exception e) {
            SeleniumCompoenents.logger.error(e.getMessage());
        }
        selComp.browserMgmt.closeBrowser();


        String sql = " select t2.symbol , t1.close , t2.close , t3.close , t1.high, t2.high,t3.high,  t1.low, t2.low  , t3.low "
                + " from  " + selComp.browserMgmt.getDb() + " t1 , " + selComp.browserMgmt.getDb() + " t2 , " + selComp.browserMgmt.getDb() + " t3 "
                + " where t1.symbol=t2.symbol and t1.date='%1$s' and t2.date ='%2$s'  and t3.date='%3$s' and t1.isvalid= 'Y' and t2.isvalid='Y' and t3.isvalid='Y' and t3.symbol=t1.symbol and t1.symbol=t2.symbol ";
        sql = String.format(sql, arrDates[0], arrDates[1], arrDates[2]);
     SeleniumCompoenents.logger.info(sql);
        String filename = "%1$s_%2$s_%3$s_%4$s.csv";
        String filename1 = "%1$s_CAMS_%2$s.csv";
        filename = String.format(filename, arrDates[0], arrDates[1], arrDates[2], System.getProperty("country"));
        filename1 = String.format(filename1, arrDates[0], System.getProperty("country"));


        String driver = "org.hsqldb.jdbcDriver";
        String relativeurl = "jdbc:hsqldb:file:./db/" + selComp.browserMgmt.getDb() + ";shutdown=true";
        Connection conn1;
        PreparedStatement st = null;
        ResultSet rs;

        PrintWriter pw = new PrintWriter("files\\" + filename);
        PrintWriter pw1 = new PrintWriter("files\\" + filename1);


        try {

            // Starting up the driver
            Class.forName(driver);

            // Connecting

            conn = DriverManager.getConnection(relativeurl, "sa", "");
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            String s = "%1$s;%2$s;%3$s;%4$s;%5$s;%6$s;%7$s;%8$s;%9$s;%10$s;%11$s;%12$s;%13$s;%14$s;%15$s;%16$s;%17$s;%18$s;%19$s;%20$s;%21$s,;%22$s;%23$s;%24$s;%25$s;%26$s;%27$s;%28$s;%29$s;%30$s;%31$s";
            s = String.format(s, "symbol", "closeD2", "HighD2", "LowD2", "Pivot_D2", "BC2", "TC2", "TCD2", "BCD2", "Pivot_Range_D2", "Price_Range_D2", "closeD1", "HighD1", "LowD1", "Pivot_D1", "BC1", "TC1", "TCD1", "BCD1", "Pivot_Range_D1", "Price_Range_D1", "closeD0", "HighD0", "LowD0", "Pivot_D0", "BC0", "TC0", "TCD0", "BCD0", "Pivot_Range_D0", "Price_Range_D0");
            pw.println(s);

            String s1 = "%1$s;%2$s;%3$s;%4$s;%5$s;%6$s;%7$s;%8$s;%9$s;%10$s;%11$s;%12$s;%13$s;%14$s;%15$s;%16$s;%17$s;%18$s;%19$s;%20$s;%21$s;%22$s;%23$s;%24$s;%25$s;%26$s;%27$s;%28$s;%29$s";
            s1 = String.format(s1, "symbol", "close", "high", "low", "cpv", "H1", "H2", "H3", "H4", "H5", "H6", "L1", "L2", "L3", "L4", "L5", "L6", "H1cpv", "H2cpv", "H3cpv", "H4cpv", "H5cpv", "H6cpv", "L1cpv", "L2cpv", "L3cpv", "L4cpv", "L5cpv", "L6cpv");

            pw1.println(s1);
            while (rs.next())
            //t2.symbol , t1.close , t2.close , t3.close , t1.high, t2.high,t3.high,  t1.low, t2.low  , t3.low
            {
                String symbol = rs.getString(1);
                double LowD2 = Double.parseDouble(rs.getString(10).replace(",", ""));
                double LowD1 = Double.parseDouble(rs.getString(9).replace(",", ""));
                double LowD0 = Double.parseDouble(rs.getString(8).replace(",", ""));

                double HighD2 = Double.parseDouble(rs.getString(7).replace(",", ""));
                double HighD1 = Double.parseDouble(rs.getString(6).replace(",", ""));
                double HighD0 = Double.parseDouble(rs.getString(5).replace(",", ""));

                double closeD2 = Double.parseDouble(rs.getString(4).replace(",", ""));
                double closeD1 = Double.parseDouble(rs.getString(3).replace(",", ""));
                double closeD0 = Double.parseDouble(rs.getString(2).replace(",", ""));

                double Pivot_D2 = (LowD2 + HighD2 + closeD2) / 3;
                double BC2 = (LowD2 + HighD2) / 3;
                double TC2 = 2 * (Pivot_D2 - BC2);
                double TCD2 = BC2 > TC2 ? BC2 : TC2;
                double BCD2 = BC2 > TC2 ? TC2 : BC2;
                double Pivot_Range_D2 = TCD2 - BCD2;
                double Price_Range_D2 = HighD2 - LowD2;

                double Pivot_D1 = (LowD1 + HighD1 + closeD1) / 3;
                double BC1 = (LowD1 + HighD1) / 3;
                double TC1 = 2 * (Pivot_D1 - BC1);
                double TCD1 = BC1 > TC1 ? BC1 : TC1;
                double BCD1 = BC1 > TC1 ? TC1 : BC1;
                double Pivot_Range_D1 = TCD1 - BCD1;
                double Price_Range_D1 = HighD1 - LowD1;

                double Pivot_D0 = (LowD0 + HighD0 + closeD0) / 3;
                double BC0 = (LowD0 + HighD0) / 3;
                double TC0 = 2 * (Pivot_D0 - BC0);
                double TCD0 = BC0 > TC0 ? BC0 : TC0;
                double BCD0 = BC0 > TC0 ? TC0 : BC0;
                double Pivot_Range_D0 = TCD0 - BCD0;
                double Price_Range_D0 = HighD0 - LowD0;

                String s2 = "%1$s;%2$,.2f;%3$,.2f;%4$,.2f;%5$,.2f;%6$,.2f;%7$,.2f;%8$,.2f;%9$,.2f;%10$,.2f;%11$,.2f;%12$,.2f;%13$,.2f;%14$,.2f;%15$,.2f;%16$,.2f;%17$,.2f;%18$,.2f;%19$,.2f;%20$,.2f;%21$,.2f;%22$,.2f;%23$,.2f;%24$,.2f;%25$,.2f;%26$,.2f;%27$,.2f;%28$,.2f;%29$,.2f;%30$,.2f;%31$,.2f";
                s2 = String.format(s2, symbol, closeD2, HighD2, LowD2, Pivot_D2, BC2, TC2, TCD2, BCD2, Pivot_Range_D2, Price_Range_D2, closeD1, HighD1, LowD1, Pivot_D1, BC1, TC1, TCD1, BCD1, Pivot_Range_D1, Price_Range_D1, closeD0, HighD0, LowD0, Pivot_D0, BC0, TC0, TCD0, BCD0, Pivot_Range_D0, Price_Range_D0);

                //String s2 = "%1$s;%2$,.2f;%3$,.2f;%4$,.2f;%5$,.2f;%6$,.2f;%7$,.2f;%10$,.2f;%11$,.2f;%12$,.2f;%13$,.2f;%14$,.2f;%15$,.2f;%16$,.2f;%17$,.2f;%20$,.2f;%21$,.2f;%22$,.2f;%23$,.2f;%24$,.2f;%25$,.2f;%26$,.2f;%27$,.2f;%30$,.2f;%31$,.2f";
                //s2 = String.format(s2, symbol,closeD2,HighD2,LowD2,Pivot_D2, BC2,TC2,Pivot_Range_D2,Price_Range_D2,closeD1,HighD1,LowD1,Pivot_D1, BC1,TC1,Pivot_Range_D1,Price_Range_D1, closeD0,HighD0,LowD0,Pivot_D0, BC0,TC0,Pivot_Range_D0,Price_Range_D0);
//logger.info(s2);
                pw.println(s2);

                String s3 = getCMSValues(symbol, closeD0, HighD0, LowD0);
                //logger.info(s3);
                pw1.println(s3);


            }
            pw.close();
            pw1.close();

            System.out.println("HSQL Server started: using connection = ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getCMSValues(String symbol, double close, double high, double low)
    //public static String  getCMSValues( String symbol, double low, double high , double close)
    {
        //double close = 0;
        //double high = 0;
        //double low = 0;
        double cpv = (high + close + low) / 3;
        double H1 = ((0.0916 * (high - low)) + close);
        double H2 = ((0.183 * (high - low)) + close);//c+(.183(h-l))
        double H3 = ((0.275 * (high - low)) + close);//c+.275()
        double H4 = ((0.55 * (high - low)) + close);    //K8
        double H5 = H4 + (H4 - H3) * 1.168;    //L8
        double H6 = (high * close / low);    //L8
        double H1cpv = Math.abs(((H1 - cpv) / cpv) * 100);
        double H2cpv = Math.abs(((H2 - cpv) / cpv) * 100);
        double H3cpv = Math.abs(((H3 - cpv) / cpv) * 100);
        double H4cpv = Math.abs(((H4 - cpv) / cpv) * 100);
        double H5cpv = Math.abs(((H5 - cpv) / cpv) * 100);
        double H6cpv = Math.abs(((H6 - cpv) / cpv) * 100);


        double L1 = ((close - (0.0916 * (high - low))));
        double L2 = (((close - (0.183 * (high - low)))));
        double L3 = (((close - (0.275 * (high - low)))));
        double L4 = (((close - (0.55 * (high - low)))));
        double L5 = L4 - (L3 - L4) * 1.168;
        double L6 = 2 * close - H6;
        //double L5	=((2*close-H5));

        double L1cpv = Math.abs(((L1 - cpv) / cpv) * 100);
        double L2cpv = Math.abs(((L2 - cpv) / cpv) * 100);
        double L3cpv = Math.abs(((L3 - cpv) / cpv) * 100);
        double L4cpv = Math.abs(((L4 - cpv) / cpv) * 100);
        double L5cpv = Math.abs(((L5 - cpv) / cpv) * 100);
        double L6cpv = Math.abs(((L6 - cpv) / cpv) * 100);


        String s1 = "%1$s;%2$,.2f;%3$,.2f;%4$,.2f;%5$,.2f;%6$,.2f;%7$,.2f;%8$,.2f;%9$,.2f;%10$,.2f;%11$,.2f;%12$,.2f;%13$,.2f;%14$,.2f;%15$,.2f;%16$,.2f;%17$,.2f;%18$,.2f;%19$,.2f;%20$,.2f;%21$,.2f;%22$,.2f;%23$,.2f;%24$,.2f;%25$,.2f;%26$,.2f;%27$,.2f;%28$,.2f;%29$,.2f";
        s1 = String.format(s1, symbol, close, high, low, cpv, H1, H2, H3, H4, H5, H6, L1, L2, L3, L4, L5, L6, H1cpv, H2cpv, H3cpv, H4cpv, H5cpv, H6cpv, L1cpv, L2cpv, L3cpv, L4cpv, L5cpv, L6cpv);
        return s1;
    }

    public static String[] getMostRecentDates(SeleniumCompoenents selcomp) {

        ArrayList<String> arrDates = new ArrayList<String>();
        selcomp.browserMgmt.driver.navigate().to(selcomp.browserMgmt.getHistoric_url());


        new WebDriverWait(selcomp.browserMgmt.driver, 5000).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='curr_table']/tbody/tr[1]/td[1]")));


        /*String todaysDate = getDate(seleniumElement.getText("//table[@id='curr_table']/tbody/tr[1]/td[1]"));
        String todaysDateMinusOne = getDate(seleniumElement.getText("//table[@id='curr_table']/tbody/tr[2]/td[1]"));
        String todaysDateMinusTwo = getDate(seleniumElement.getText("//table[@id='curr_table']/tbody/tr[3]/td[1]"));
*/
        String todaysDate = getDate(selcomp.browserMgmt.driver.findElement(By.xpath("//table[@id='curr_table']/tbody/tr[1]/td[1]")).getText());
        String todaysDateMinusOne = getDate(selcomp.browserMgmt.driver.findElement(By.xpath("//table[@id='curr_table']/tbody/tr[2]/td[1]")).getText());
        String todaysDateMinusTwo = getDate(selcomp.browserMgmt.driver.findElement(By.xpath("//table[@id='curr_table']/tbody/tr[3]/td[1]")).getText());

        SeleniumCompoenents.logger.info("todaysDate :" + todaysDate);
        SeleniumCompoenents.logger.info("todaysDateMinusOne :" + todaysDateMinusOne);
        SeleniumCompoenents.logger.info("todaysDateMinusTwo :" + todaysDateMinusTwo);


        arrDates.add(todaysDate);
        arrDates.add(todaysDateMinusOne);
        arrDates.add(todaysDateMinusTwo);

        return arrDates.toArray(new String[arrDates.size()]);


    }

    public static String getDate(String date) {

        String startDateString = date;
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        DateFormat df1 = new SimpleDateFormat("yyyyMMdd");
        Date startDate;
        try {
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
            return df1.format(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @AfterTest
    void teardown() {

    }

}
