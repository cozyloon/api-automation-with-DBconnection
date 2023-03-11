package util;

import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import static common.Constants.*;

public class TestBase {
    public SoftAssert softAssert;
    public DBHelper dbHelper =new DBHelper(DB_URL,DB_USERNAME,DB_PASSWORD);

    @BeforeClass
    public void beforeClass() {
        LoggerUtil.logINFO("TestClass Running " + this.getClass().toString());
        this.softAssert = new SoftAssert();
    }
     
    @BeforeMethod
    public void beforeTest(Method method) {
        LoggerUtil.logINFO(method.getName());
    }
}
