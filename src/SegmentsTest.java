import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class SegmentsTest {
	public ArrayList<Segment> td = new ArrayList<Segment>();
	
	Segment s1 = new Segment(1, 8);
    Segment s2 = new Segment(10, 12);
    Segment s3 = new Segment(18, 20);
    Segment s4 = new Segment(5, 7);
    Segment s5 = new Segment(24,27);
    Segment s6 = new Segment(29, 33);
	
	
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
	
	@Test
	void testProblemOutput() {
		Segments seg = new Segments();
		
		seg.addSegment(s1);
		seg.addSegment(s2);
		seg.addSegment(s3);
		seg.addSegment(s4);
		seg.addSegment(s5);
		seg.addSegment(s6);
	    
		ArrayList<Segment> output = seg.getOuputSegment();
		
		Segment segOut1 = output.get(0);
		Segment segOut2 = output.get(1);
		
	    assertEquals(output.size(), 2);
	    assertEquals(segOut1.getStartTime(), 1);
	    assertEquals(segOut1.getEndTime(), 12); 
	    assertEquals(segOut2.getStartTime(), 18);
	    assertEquals(segOut2.getEndTime(), 33);
	}

}
