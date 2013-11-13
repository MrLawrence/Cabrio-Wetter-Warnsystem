package warnsystem;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;

public class GoogleMapsReader {
	private final static Logger LOG = Logger.getLogger(WeatherWrapper.class
			.getName());

	Builder parser;

	public GoogleMapsReader(String text) {
		try {
			parser = new Builder();
			Document doc = parser.build(text);
		} catch (ParsingException ex) {
			LOG.severe("Couldn't parse XML");
		} catch (IOException e) {
			LOG.severe("Couldn't read data");
		}
	}

	public HashMap<String, Point> getCoordinates() {

	}
}
