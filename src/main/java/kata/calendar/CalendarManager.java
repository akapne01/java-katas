package kata.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarManager {

   private final List<Meeting> scheduledMeetings = new ArrayList<>();

   public void schedule(Meeting newMeeting) {
      boolean meetingDoesNotOverlapAnyOtherMeeting = scheduledMeetings.stream()
         .allMatch(m -> !newMeeting.getEnd().isAfter(m.getStart()) || !newMeeting.getStart().isBefore(m.getEnd()));

      if (meetingDoesNotOverlapAnyOtherMeeting) {
         scheduledMeetings.add(newMeeting);
      }
   }

   public List<Meeting> scheduledMeetings() {
      return scheduledMeetings.stream().sorted(compareByStartTime()).collect(Collectors.toList());
//      return scheduledMeetings.stream()
//         .sorted(new MeetingComparator())
//         .collect(Collectors.toList());
   }

   private Comparator<Meeting> compareByStartTime() {
      return (o1, o2) -> {
         if (o1.getStart().isAfter(o2.getStart())) {
            return 1;
         }

         if (o1.getStart().isBefore(o2.getStart())) {
            return -1;
         }

         return 0;
      };
   }

   private static class MeetingComparator implements Comparator<Meeting> {
      @Override
      public int compare(Meeting o1, Meeting o2) {
         if (o1.getStart().isAfter(o2.getStart())) {
            return 1;
         }

         if (o1.getStart().isBefore(o2.getStart())) {
            return -1;
         }

         return 0;
      }
   }
}
