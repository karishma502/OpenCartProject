package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportManager implements ITestListener {

    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
        sparkReporter.config().setDocumentTitle("Automation Testing");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS","MAC");
        extent.setSystemInfo("Browser","Chrome");
        extent.setSystemInfo("Tester Name","Kate");
        extent.setSystemInfo("Envt","QA");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.PASS,"TestCase pass is"+result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.FAIL,"TestCase Failed is"+result.getName());
        test.log(Status.FAIL,"Root cause"+result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test=extent.createTest(result.getName());
        test.log(Status.SKIP,"Test Case skipped is"+result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
