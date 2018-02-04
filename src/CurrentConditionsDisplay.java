@Observer
public class CurrentConditionsDisplay implements DisplayElement {

    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay() {
        temperature = 0.0f;
        humidity = 0.0f;
    }

    @Update
    public void updateReadings(WeatherData data) {
        temperature = data.getTemperature();
        humidity = data.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println(
            "Current conditions: " + temperature +
            "F degrees and " + humidity +
            "% humidity"
        );
    }
}
