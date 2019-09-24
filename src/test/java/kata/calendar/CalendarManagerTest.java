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
   Meeting anotherRetro = new Meeting("retro", LocalDateTime.of(2019, Month.AUGUST, 28, 11, 00),
      LocalDateTime.of(2019, Month.AUGUST, 28, 12, 00));
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

   @Test
   public void shouldReturnSuccessfulIfCalendarOperationResultWasCompleted() {
      // given -  non clashing meetings
      //standUp and planning are non clashing meetings

      // When - meetings scheduled
      CalendarOperationResult result = calendarManager.schedule(standUp);
      CalendarOperationResult result2 = calendarManager.schedule(planning);

      // Then - both scheduling operations are successful
      assertTrue(result.isSuccessful());
      assertTrue(result2.isSuccessful());
   }

   @Test
   public void shouldReturnTheReasonIfCalendarOperationResultFailed() {
      // given - scheduled meeting that overlap

      // When - meeting was failed to be scheduled
      CalendarOperationResult result = calendarManager.schedule(planning);
      CalendarOperationResult failingResult = calendarManager.schedule(overLappingWithPlanning);

      // Then - schedule method returns the reason why the Operation failed
      assertTrue(result.isSuccessful());
      assertFalse(failingResult.isSuccessful());
      assertThat(failingResult.getFailingReason(), is("Overlapping meeting overlaps with planning"));
   }

   @Test
   public void shouldReturnErrorMessageIfMeetingWithTheSameNameAlreadyExists() {

      // Given - 2 meetings with the same name
      CalendarOperationResult retroResult = calendarManager.schedule(retro);
      // When - adding the second meeting
      CalendarOperationResult anotherRetroResult = calendarManager.schedule(anotherRetro);

      // Then - error message returned
      String returnMessage = "Meeting with name retro already exists";
      assertTrue(retroResult.isSuccessful());
      assertThat(anotherRetroResult.getFailingReason(), is(returnMessage));
   }
}