package warnsystem;

import java.util.ArrayList;

public class Route {
	MapsWrapper maps = new MapsWrapper();
	
	String origin;
	String destination;
	String waypoint1 = "";
	String waypoint2 = "";
	String waypoint3 = "";

	public Route(String origin, String destination) {
		this.origin = origin;
		this.destination = destination;
	}

	public void setFirstWaypoint(String waypoint) {
		waypoint1 = waypoint;
	}

	public void setSecondWaypoint(String waypoint) {
		waypoint2 = waypoint;
	}

	public void setThirdWaypoint(String waypoint) {
		waypoint3 = waypoint;
	}
	
	public String getXML() {
		ArrayList<String> wp = new ArrayList<String>();
		wp.add(waypoint1);
		wp.add(waypoint2);
		wp.add(waypoint3);
		
		String xml = maps.getXML(origin, destination, wp);
		return xml;
	}
}
