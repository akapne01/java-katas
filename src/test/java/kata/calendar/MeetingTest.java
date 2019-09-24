package kata.calendar;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class MeetingTest {

   @Test(expected = IllegalArgumentException.class)
   public void shouldTrowExceptionWhenEndTimeBeforeStartTime() {
      Meeting exceptionTrowingMeeting = new Meeting("Throws exception", LocalDateTime.of(2019, Month.AUGUST, 27, 15, 00),
         LocalDateTime.of(2019, Month.AUGUST, 27, 14, 45));
   }

}