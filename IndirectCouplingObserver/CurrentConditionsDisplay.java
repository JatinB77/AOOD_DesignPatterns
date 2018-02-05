/**
 * This class is meant to be used as an Observer
 */
@Observer
public class CurrentConditionsDisplay implements DisplayElement {

    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay() {
        temperature = 0.0f;
        humidity = 0.0f;
    }

    /**
     * (method of this Observer) which will be called when Subjects (WeatherStation) publish info.
     * sets temp and humidity based on passed readings from WeatherData object, then calls display()
     * @param data
     */
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
