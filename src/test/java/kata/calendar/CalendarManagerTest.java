package kata.calendar;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalendarManagerTest {

   CalendarManager calendarManager = new CalendarManager();
   Meeting retro = new Meeting("retro", LocalDateTime.of(2019, Month.AUGUST, 27, 11, 00),
      LocalDateTime.of(2019, Month.AUGUST, 27, 12, 00));
   Meeting planning = new Meeting("planning", LocalDateTime.of(2019, Month.AUGUST, 27, 14, 00),
      LocalDateTime.of(2019, Month.AUGUST, 27, 15, 30));
   Meeting standUp = new Meeting("stand up", LocalDateTime.of(2019, Month.AUGUST, 27, 9, 20),
      LocalDateTime.of(2019, Month.AUGUST, 27, 10, 00));
   Meeting overLappingWithPlanning = new Meeting("Overlapping meeting", LocalDateTime.of(2019, Month.AUGUST, 27, 14, 00),
      LocalDateTime.of(2019, Month.AUGUST, 27, 15, 30));
   Meeting overLappingStartBefore = new Meeting("Overlapping meeting", LocalDateTime.of(2019, Month.AUGUST, 27, 13, 30),
      LocalDateTime.of(2019, Month.AUGUST, 27, 15, 00));
   Meeting overLappingStartAfter = new Meeting("Overlapping meeting", LocalDateTime.of(2019, Month.AUGUST, 27, 15, 00),
      LocalDateTime.of(2019, Month.AUGUST, 27, 16, 30));
   Meeting overLappingShort = new Meeting("Overlapping meeting", LocalDateTime.of(2019, Month.AUGUST, 27, 15, 00),
      LocalDateTime.of(2019, Month.AUGUST, 27, 15, 15));

   @Before
   public void setUp() {

   }

   @Test
   public void shouldScheduleMeetingsAndDisplayThemInOrder() {
      // Given - scheduled meetings

      calendarManager.schedule(retro);
      calendarManager.schedule(planning);
      calendarManager.schedule(standUp);

      // When - print the meetings
      List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

      // Then - Displays them in order
      assertThat(scheduledMeetings.get(0), is(standUp));
      assertThat(scheduledMeetings.get(1), is(retro));
      assertThat(scheduledMeetings.get(2), is(planning));
   }

   @Test
   public void shouldNotScheduleMeetingsIfOverlapsWithExisting() {
      // Given - meetings that overlap
      calendarManager.schedule(planning);
      calendarManager.schedule(overLappingWithPlanning);
      calendarManager.schedule(overLappingShort);
      calendarManager.schedule(overLappingStartAfter);
      calendarManager.schedule(overLappingStartBefore);

      // When - schedule them
      List<Meeting> scheduledMeetings = calendarManager.scheduledMeetings();

      // Then - only first one is scheduled

      assertThat(scheduledMeetings.get(0), is(planning));
      assertThat(scheduledMeetings.size(), is(1));

   }
}