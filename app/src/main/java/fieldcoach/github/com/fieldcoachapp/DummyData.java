package fieldcoach.github.com.fieldcoachapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron Crutchfield on 5/18/2018.
 */
public class DummyData {
    private String title;
    private String content;

    DummyData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List<DummyData> getDummyDataList() {
        List<DummyData> dummyDataList = new ArrayList<>();
        dummyDataList.add(new DummyData("FC Barcelona",
                "Manager: Ernesto Valverde\n" +
                        "Coach: Ernesto Valverd\n" +
                        "Founder: Joan Gamper"));
        dummyDataList.add(new DummyData("Upcoming Games",
                "5/20 - 2:45 PM\n" +
                        "7/31 - 9:30 PM\n" +
                        "8/12 - TBD"));
        dummyDataList.add(new DummyData("Latest Results",
        "Levante 5 - Barcelona 4"));

        return dummyDataList;
    }
}
