package util;

import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class TestBase {
    public SoftAssert softAssert;

    @BeforeMethod
    public void beforeTest() {
        LoggerUtil.logINFO("TestClass Running " + this.getClass().toString());
        this.softAssert = new SoftAssert();
    }
}
