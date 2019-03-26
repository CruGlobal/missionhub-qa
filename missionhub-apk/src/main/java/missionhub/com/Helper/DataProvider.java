package missionhub.com.Helper;

public class DataProvider {
    @org.testng.annotations.DataProvider(name = "devicesList")
    public static Object[][] provideDevicesList() {
        return new Object[][] {{"Nexus 5X"}, {"LG G5"},{"Moto Z (2) Play"}, {"Galaxy S7"}, {"U11"}, {"Galaxy S8"}};
    }
}
