package warnsystem;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class GoogleMapsReader {
	private final static Logger LOG = Logger.getLogger(WeatherWrapper.class
			.getName());
	MapsWrapper wrap = new MapsWrapper();
	Builder parser = new Builder();

	public HashMap<String, Point> getCoordinates(String origin, String destination, ArrayList<String> waypoints) {
		wrap.getXML(origin, destination, waypoints);
		try {
			Document doc = parser.build(search);
		} catch (ParsingException | IOException e) {
			e.printStackTrace();
		}

	}
}
