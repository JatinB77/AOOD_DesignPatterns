import lombok.*;
/**
 * WeatherData class has and maintains the temp, humidity, and pressure
 */
@Data
public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;
}
