import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        WeatherStation station = new WeatherStation();
        List<DisplayElement> displays = getDisplays();

        station.update(75.0f, 64.5f, 20.2f);

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
