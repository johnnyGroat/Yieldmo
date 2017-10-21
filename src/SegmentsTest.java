import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SegmentsTest {
	public ArrayList<Segment> td = new ArrayList<Segment>();
	
	Segment td1 = new Segment(1, 8);
	Segment td2 = new Segment(10, 12);
	Segment td3 = new Segment(4, 18);
	
	Segments seg = new Segments();
	
	@Test
	void testSegmentLength() {
		Segment td1 = new Segment(1, 8);
		Segment td2 = new Segment(10, 12);
		
		Segments seg = new Segments();
		
		seg.addSegment(td1);
		seg.addSegment(td2);
		
		assertEquals(seg.getTimeDiffSize(), 2);
	}
	
	@Test
	void testValidSegment() {
		Segment td1 = new Segment(10, 1);
		
		Segments seg = new Segments();
		
		seg.addSegment(td1);
		
		assertEquals(seg.getTimeDiffSize(), 0);
	}

}
