import java.util.ArrayList;

public class Segments {
	private int latestStartTime;
	private int latestEndTime;
	
	private ArrayList<Segment> timeDiff;
	
	public Segments() {
		timeDiff = new ArrayList<Segment>();
	}
	
	public void addSegment(Segment td) {
		// Bail out if we don't have a valid segment
		if (!validSegment(td)) {
			return;
		}
		timeDiff.add(td);
		
		if (getTimeDiffSize() == 1) {
			latestStartTime = td.getStartTime();
			latestEndTime = td.getEndTime();
		}
	}
	
	// Requirements state that startTime < endTime
	public boolean validSegment(Segment td) {
		if (td.getStartTime() >= td.getEndTime()) {
			return false;
		}
		return true;
	}
	
	public int getTimeDiffSize() {
		return timeDiff.size();
	}
}
