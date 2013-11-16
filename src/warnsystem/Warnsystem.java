package warnsystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Warnsystem {
	private final static Logger LOG = Logger.getLogger(Warnsystem.class
			.getName());

	public static void main(String[] args) throws Exception {
		MapsUrlBuilder maps = new MapsUrlBuilder();
		WeatherWrapper weather = new WeatherWrapper();
		ArrayList<String> waypoints = new ArrayList<String>();
		//waypoints.add("Lothstr. 15, München");

		String routeXml = maps.buildUrl("Zasingerstr. 4, München",
				"Zasingerstr. 7, München", waypoints);
		LOG.fine(routeXml);
		Route route = new Route(routeXml);
		/*
		 * String currentWeather = weather.getcurrentXML(48.106, 11.580);
		 * LOG.info(currentWeather);
		 */
	}
}
