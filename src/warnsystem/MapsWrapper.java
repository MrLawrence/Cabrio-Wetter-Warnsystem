package warnsystem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class MapsWrapper {
	private final static Logger LOG = Logger.getLogger(MapsWrapper.class
			.getName());

	private String format = "xml";
	private String apiAddress = "http://maps.googleapis.com/maps/api/directions/"
			+ format;
	private String sensorString = "sensor=false";

	public String getXML(String origin, String destination) {
		return this.getXML(origin, destination, null);
	}

	public String getXML(String origin, String destination,
			ArrayList<String> waypoints) {

		URL url = buildUrl(origin, destination, waypoints);
		LOG.info("Maps URL:" + url.toString());
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

	private URL buildUrl(String origin, String destination,
			ArrayList<String> waypoints) {
		URL mapsUrl = null;
		String urlString = "";
		String wayp = "";
		if (waypoints != null) {
			wayp += "&waypoints=";
			for (String s : waypoints) {
				wayp += s;
				wayp += '|';
			}
			wayp = wayp.substring(0, wayp.length() - 1);
		}
		urlString = apiAddress + "?origin=" + origin + "&destination="
				+ destination + wayp + '&' + sensorString;
		urlString = urlString.replace(" ", "+");

		try {
			mapsUrl = new URL(urlString);

		} catch (MalformedURLException e) {
			LOG.severe("Built an invalid URL");
		}
		return mapsUrl;
	}
}
