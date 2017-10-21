import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TimeDifferenceTest {
	public ArrayList<TimeDifference> td = new ArrayList<TimeDifference>();
	@Test
	void testSegmentLength() {
		TimeDifference td1 = new TimeDifference(1, 8);
		TimeDifference td2 = new TimeDifference(10, 12);
		
		Segments seg = new Segments();
		
		seg.addSegment(td1);
		seg.addSegment(td2);
		
		assertEquals(seg.getTimeDiffSize(), 2);
	}

}
