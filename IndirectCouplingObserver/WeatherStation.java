/**
 * Subject class
 */
public class WeatherStation {

    private Manager manager;
    private WeatherData data;

    public WeatherStation() {
        manager = new Manager();
        data = new WeatherData();
    }

    public void addListener(Object listener) {
        manager.addListener(listener);
    }

    public void update(float temperature, float humidity, float pressure) {
        data=new WeatherData(temperature,humidity,pressure);
        // Subject publishing info (data) to the "middleman" (manager)
        manager.publish(data);
    }
}
