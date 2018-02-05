import java.util.ArrayList;
import java.util.List;

/**
 * Driver class for Observer Pattern: indirect coupling between Observers and Subjects
 */
public class Main {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        List<DisplayElement> displays = getDisplays();

        displays.forEach(d -> station.addListener(d));

        station.update(75.0f, 64.5f, 20.2f);
        displays.forEach(d -> d.display());

        System.out.println();

        station.update(56.3f, 22.0f, 65.9f);
        displays.forEach(d -> d.display());
    }

    private static List<DisplayElement> getDisplays() {
        List<DisplayElement> displays = new ArrayList<>();
        displays.add(new CurrentConditionsDisplay());
        displays.add(new StatisticsDisplay());
        displays.add(new ForecastDisplay());
        displays.add(new HeatIndexDisplay());
        return displays;
    }
}
