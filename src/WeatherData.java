public class WeatherData {

    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.temperature = 0.0f;
        this.humidity = 0.0f;
        this.pressure = 0.0f;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public WeatherData setTemperature(float temperature) {
        this.temperature = temperature;
        return this;
    }

    public WeatherData setHumidity(float humidity) {
        this.humidity = humidity;
        return this;
    }

    public WeatherData setPressure(float pressure) {
        this.pressure = pressure;
        return this;
    }
}
