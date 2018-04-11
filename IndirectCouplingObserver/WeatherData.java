import lombok.*;
/**
 * WeatherData class has and maintains the temp, humidity, and pressure
 */
@Data
public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;
    
    /**
     * Really wanted to use lombok, but eclipse is causing problems with unspecified constructors
     */
    public WeatherData(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
    
    public WeatherData() {}
}
