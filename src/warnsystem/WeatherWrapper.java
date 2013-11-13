package warnsystem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Logger;

public class WeatherWrapper {
	private final static Logger LOG = Logger.getLogger(WeatherWrapper.class
			.getName());

	String currentAddress = "http://api.openweathermap.org/data/2.5/weather";
	String forecastAddress = "http://api.openweathermap.org/data/2.5/forecast";

	public String getcurrentXML(double lat, double lon) {
		URL url = buildCurrentUrl(lat, lon);
		LOG.info("Weather URL:" + url.toString());

		URLConnection connection;
		try {
			connection = url.openConnection();
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream());
			String response = "";
			while (in.hasNextLine()) {
				response += in.nextLine() + '\n';
			}
			in.close();
			return response;
		} catch (IOException e) {
			LOG.severe("Couldn't access website");
			return null;
		}

	}

	public URL buildCurrentUrl(double latitude, double longitude) {
		URL weatherUrl = null;
		String urlString = currentAddress + "?lat=" + latitude + "&lon="
				+ longitude + "&mode=xml";
		try {
			weatherUrl = new URL(urlString);

		} catch (MalformedURLException e) {
			LOG.severe("Built an invalid URL");
		}
		return weatherUrl;
	}
}
