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

	ArrayList<Point2D.Double> routeCoordinates = new ArrayList<Point2D.Double>();

	public Route(String url) {
		RouteExtractor ex = new RouteExtractor();
	
		routeCoordinates = ex.getPoints(url);

		LOG.info(routeCoordinates.toString());
	}
}
