package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.opentelemetry.semconv.UrlAttributes;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testCases.BestTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;
    String repName;
    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        repName = "Test-Report-"+timeStamp+".html";
        sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
        sparkReporter.config().setDocumentTitle("Automation Testing");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester Name",System.getProperty("user.name"));
        extent.setSystemInfo("Envt","QA");

        String os = context.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("OS",os);

        String browserName =context.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("browser",browserName);

        List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()){
           extent.setSystemInfo("Groups",includedGroups.toString());
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getTestClass().getRealClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,"TestCase pass is"+result.getName());


        try {
            String imgPath = new BestTest().captureScreenshot(result.getName());
            test.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getMethod().getTestClass().getRealClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,"TestCase Failed is"+result.getName());
        test.log(Status.FAIL,"Root cause: "+result.getThrowable());


        try {
            String imgPath = new BestTest().captureScreenshot(result.getName());
            test.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getTestClass().getRealClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,"Test Case skipped is"+result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

     //automatically open reports once generate

        String pathOfExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
        File file = new File(pathOfExtentReport);


        if (!file.exists()) {
            System.err.println("Report file not found at: " + pathOfExtentReport);
            return;
        }

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(file.toURI());
                System.out.println("Report opened successfully.");
            } else {
                System.err.println("Desktop is not supported on this platform.");
            }
        } catch (IOException e) {
            System.err.println("Failed to open report: " + e.getMessage());
        }

//
//        //Send email once execution is done with report
//
//        try {
//            URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//
//            //Create email msg
//
//            ImageHtmlEmail email = new ImageHtmlEmail();
//            email.setDataSourceResolver(new DataSourceUrlResolver(url));
//            email.setHostName("smtp.googlemail.com");
//            email.setSmtpPort(465);
//            email.setAuthenticator(new DefaultAuthenticator("Your@gmail.com", "password"));
//            email.setSSLOnConnect(true);
//            email.setFrom("karishmakate31@gmail.com"); //Sender
//            email.setSubject("Test Report");
//            email.setMsg("Please find report attached..");
//            email.addTo("karishmakate1998@gmail.com"); // reciever
//            email.attach(url,"extent Report","pls check report");
//            email.send(); //send email.
//
//
//;        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
