/**
 * Subject class
 */
public class WeatherStation {

    private Manager manager;
    private WeatherData data;

    public WeatherStation() {
        data = new WeatherData();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void update(float temperature, float humidity, float pressure) {
        data=new WeatherData(temperature,humidity,pressure);
        // Subject publishing info (data) to the "middleman" (manager)
        manager.publish(data);
    }
}
