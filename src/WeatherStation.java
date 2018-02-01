public class WeatherStation {

    private Manager manager;
    private WeatherData data;

    public WeatherStation() {
        manager = new Manager();
        data = new WeatherData();
    }

    public void update(float temperature, float humidity, float pressure) {
        data.setTemperature(temperature)
            .setHumidity(humidity)
            .setPressure(pressure);

        manager.publish(data);
    }
}
