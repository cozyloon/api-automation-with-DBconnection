package common;

public class Constants {
    public static final String TEST_ENV = SystemProperties.getProperty("test.env");
    public static final String DB_URL = SystemProperties.getProperty("db.url");
    public static final String DB_USERNAME = SystemProperties.getProperty("db.username");
    public static final String DB_PASSWORD = SystemProperties.getProperty("db.password");
    public static final String BASE_URL = SystemProperties.getProperty("base.url");;


}
