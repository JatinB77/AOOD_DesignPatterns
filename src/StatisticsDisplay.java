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
