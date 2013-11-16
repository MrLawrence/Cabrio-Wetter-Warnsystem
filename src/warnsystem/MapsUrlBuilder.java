package warnsystem;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MapsUrlBuilder {
	private final static Logger LOG = Logger.getLogger(MapsUrlBuilder.class
			.getName());

	final private String apiAddress = "http://maps.googleapis.com/maps/api/directions/xml";

	public String buildUrl(String origin, String destination,
			ArrayList<String> waypoints) {
		String urlString = "";
		ArrayList<String> escapedWaypoints = new ArrayList<String>();

		try {
			origin = URLEncoder.encode(origin, "UTF-8");
			destination = URLEncoder.encode(destination, "UTF-8");

			for (int i = 0; i < waypoints.size(); i++) {
				escapedWaypoints.add(URLEncoder.encode(waypoints.get(i),
						"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			LOG.severe("Encoding is unsupported");
		}
		String wpUrlString = getWaypointsString(escapedWaypoints);

		urlString = apiAddress + "?origin=" + origin + "&destination="
				+ destination + wpUrlString + "&sensor=false";

		return urlString;
	}

	private String getWaypointsString(ArrayList<String> waypoints) {
		String wpUrlString = "";
		if (waypoints != null) {
			wpUrlString += "&waypoints=";
			for (String s : waypoints) {
				wpUrlString += s;
				if (!s.equals("")) {
					wpUrlString += '|';
				}
			}
			wpUrlString = wpUrlString.substring(0, wpUrlString.length() - 1);
		}
		return wpUrlString;
	}
}
