package Segment;
import java.util.ArrayList;

public class Segments {
	private int latestStartTime;
	
	private ArrayList<Segment> output;
	private ArrayList<Segment> input;
	
	public Segments() {
		input = new ArrayList<Segment>();
		output = new ArrayList<Segment>();
	}
	
	public void addSegment(Segment td) {
		// Bail out if we don't have a valid segment
		if (!validSegment(td)) {
			return;
		}
		input.add(td);
		// latest start/end times dependent on more than one item in the collection
		if (getInputSize() == 1) {
			latestStartTime = td.getStartTime();
			return;
		}
		
		Segment previous = input.get(getInputSize() - 2);
		
		if (Math.abs(previous.getEndTime() - td.getStartTime()) <= 5) {
			output.add(new Segment(latestStartTime, td.getEndTime()));
			// reset input since we meet criteria
			input = new ArrayList<Segment>();
		}
	}
	
	// Requirements state that startTime < endTime
	public boolean validSegment(Segment td) {
		if (td.getStartTime() >= td.getEndTime()) {
			return false;
		}
		return true;
	}
	
	public int getInputSize() {
		return input.size();
	}
	
	public ArrayList<Segment> getOuputSegment() {
		return output;
	}
}
