/**
 * This class is meant to be used as an Observer
 */
@Observer
public class StatisticsDisplay implements DisplayElement {

    private float maxPressure;
    private float maxTemp;
    private float minTemp;
    private float tempSum;
    private int tempReadings;

    public StatisticsDisplay() {
        maxTemp = 0.0f;
        minTemp = 0.0f;
        tempSum = 0.0f;
        maxPressure = 0.0f;
        numReadings = 0;
    }

    /**
     * (method of this Observer) which will be called when Subjects (WeatherStation) publish info.
     * updates temperature fields
     * @param data
     */
    @Update
    public void updateTemperature(WeatherData data) {
        float temp = data.getTemperature();

        tempSum += temp;
        tempReadings++;

        if (temp > maxTemp)
            maxTemp = temp;

        if (temp < minTemp)
            minTemp = temp;
    }

    /**
     * (method of this Observer) which will be called when Subjects (WeatherStation) publish info.
     * updates maxPressure field
     * @param data
     */
    @Update
    public void updatePressure(WeatherData data) {
        float pressure = data.getPressure();

        if (pressure > maxPressure)
            maxPressure = pressure;
    }

    @Override
    public void display() {
        System.out.println(
            "Avg/Max/Min temperature = " +
            (tempSum / tempReadings) +
            "/" + maxTemp + "/" + minTemp +
            "\nMaxPressure = " + maxPressure
        );
    }
}
