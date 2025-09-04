package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;


/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event eve;
    private Date dat;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        eve = new Event("new word added");   // (1)
        dat = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("new word added", eve.getDescription());
        assertEquals(dat.toString(), eve.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(dat.toString() + "\n" + "new word added", eve.toString());
    }
}
