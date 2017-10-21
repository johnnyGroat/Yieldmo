import java.util.ArrayList;

public class Segments {
	private ArrayList<TimeDifference> timeDiff;
	
	public Segments() {
		timeDiff = new ArrayList<TimeDifference>();
	}
	
	public void addSegment(TimeDifference td) {
		timeDiff.add(td);
	}
	
	public int getTimeDiffSize() {
		return timeDiff.size();
	}
}
