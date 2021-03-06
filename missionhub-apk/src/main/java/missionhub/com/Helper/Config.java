package missionhub.com.Helper;

public class Config {
    private static String KOBITON_CREDENTIAL = "<API_KEY>@api.kobiton.com/wd/hub";
    private static String APP_URL="https://s3-ap-southeast-1.amazonaws.com/kobiton-devvn/asg_apps/MissionHub.apk";
    private static String APP_USERNAME = TEST_USER;
    private static String APP_PASSWORD = TEST_PWD;

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
