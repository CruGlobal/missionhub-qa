package missionhub.com.Helper;

public class Config {
    private static String KOBITON_CREDENTIAL = "https://bufordr1:84fd0f29-23e8-47f4-8ba2-223f737ba768@api.kobiton.com/wd/hub";  //Bob Buford login
//    private static String KOBITON_CREDENTIAL = "https://Justin_Custer:a0fe65f9-1e46-44f1-85fa-ee24bbccb004@api.kobiton.com/wd/hub";   //Justin Custer login
    private static String APP_URL="https://s3-ap-southeast-1.amazonaws.com/kobiton-devvn/asg_apps/MissionHub.apk";
    private static String APP_USERNAME = "test@test.com";
    private static String APP_PASSWORD = "Test1234";

    public static String getCredential() {
        return KOBITON_CREDENTIAL;
    }
    public static String getAppURL() {
        return APP_URL;
    }
    public static String getAppUserName() {
        return APP_USERNAME;
    }
    public static String getAppPassword() {
        return APP_PASSWORD;
    }
}
