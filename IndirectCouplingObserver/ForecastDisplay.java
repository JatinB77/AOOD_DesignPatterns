import java.util.*;
/**
 * This class is meant to be used as an Observer
 */
@Observer
public class ForecastDisplay implements DisplayElement {
	private float currentPressure;  
	private float lastPressure;

	public ForecastDisplay() {
		currentPressure = 0.0f;
		lastPressure = 0.0f;
	}

	/**
	 * (method of this Observer) which will be called when Subjects (WeatherStation) publish info.
	 * updates the new pressure and the prior pressure amount, then calls display()
	 * @param data
	 */
	@Update
	public void update(WeatherData data) {
                lastPressure = currentPressure;
		currentPressure = data.getPressure();

		display();
	}

	@Override
	public void display() {
		System.out.print("Forecast: ");
		if (currentPressure > lastPressure) {
			System.out.println("Improving weather on the way!");
		} else if (currentPressure == lastPressure) {
			System.out.println("More of the same");
		} else if (currentPressure < lastPressure) {
			System.out.println("Watch out for cooler, rainy weather");
		}
	}
}
