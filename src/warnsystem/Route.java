package warnsystem;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Builder;
import nu.xom.Elements;
import nu.xom.ParsingException;

public class Route {
	private final static Logger LOG = Logger.getLogger(Route.class.getName());
	Builder parser = new Builder();

	private Point2D.Double origin;
	private Point2D.Double destination;
	private Point2D.Double waypoint1;
	private Point2D.Double waypoint2;
	private Point2D.Double waypoint3;

	public Route(String url) {
		Document doc = getXMLDocument(url);
		Elements legs = getLegs(doc);
		ArrayList<Point2D.Double> coordinates = getAllCoordinates(legs);

		origin = coordinates.get(0);
		destination = coordinates.get(coordinates.size() - 1);
		LOG.info(coordinates.toString());
	}

	private ArrayList<Point2D.Double> getAllCoordinates(Elements legs) {
		ArrayList<Point2D.Double> coordinates = new ArrayList<Point2D.Double>();

		for (int i = 0; i < legs.size(); i++) {
			Element leg = legs.get(i);
			Element start = leg.getFirstChildElement("start_location");

			coordinates.add(getCoordinates(start));
		}

		Element lastLeg = legs.get(legs.size() - 1);
		Element end = lastLeg.getFirstChildElement("end_location");
		coordinates.add(getCoordinates(end));

		return coordinates;
	}

	private Document getXMLDocument(String url) {
		Document doc = null;
		try {
			LOG.info(url);
			doc = parser.build(url);
		} catch (ParsingException | IOException e) {
			LOG.severe("Parsing of XML failed");
		}
		LOG.fine(doc.toXML());
		return doc;
	}

	private Elements getLegs(Document document) {
		Element response = document.getRootElement();
		Element route = response.getFirstChildElement("route");
		Elements legs = route.getChildElements("leg");
		LOG.info("Legs found: " + Integer.toString(legs.size()));
		return legs;
	}

	private Point2D.Double getCoordinates(Element location) {
		Double lat = Double.parseDouble(location.getFirstChildElement("lat")
				.getValue());
		Double lng = Double.parseDouble(location.getFirstChildElement("lng")
				.getValue());
		Point2D.Double coordinates = new Point2D.Double(lat, lng);
		return coordinates;
	}

}
