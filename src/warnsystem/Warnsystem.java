package warnsystem;

import java.util.ArrayList;
import java.util.logging.Logger;



public class Warnsystem {
	private final static Logger LOG = Logger.getLogger(Warnsystem.class
			.getName());

	public static void main(String[] args) throws Exception{
		MapsWrapper maps = new MapsWrapper();
		WeatherWrapper weather = new WeatherWrapper();
		ArrayList<String> waypoints = new ArrayList<String>();
		waypoints.add("Mindelheim");
		waypoints.add("Memmingen");
		

		String router = maps.getXML("München", "Landshut");
		LOG.info(router);
		String currentWeather = weather.getcurrentXML(48.106, 11.580);
		LOG.info(currentWeather);
	}
}
