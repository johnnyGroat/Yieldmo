
public class Segment {
	private int startTime;
	private int endTime;
	
	public Segment(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public void printValues() {
		System.out.println(startTime);
		System.out.println(endTime);
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getEndTime() {
		return endTime;
	}
}
