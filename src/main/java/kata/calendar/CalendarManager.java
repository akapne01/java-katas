package kata.calendar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalendarManager {

   private final List<Meeting> scheduledMeetings = new ArrayList<>();

   public CalendarOperationResult schedule(Meeting newMeeting) {
//
//      boolean meetingDoesNotOverlapAnyOtherMeeting = scheduledMeetings.stream()
//         .allMatch(m -> !newMeeting.getEnd().isAfter(m.getStart()) || !newMeeting.getStart().isBefore(m.getEnd()));
//
//      if (meetingDoesNotOverlapAnyOtherMeeting) {
//         scheduledMeetings.add(newMeeting);
//         return new CalendarOperationResult(true);
//      }
//
//      String message = newMeeting.getName()  + " overlaps with " + getOverlappingMeetingName(newMeeting);
//      return new CalendarOperationResult(false, message);

//      if (meetingNameOverlaps(newMeeting)) {
//         return new CalendarOperationResult(false, "Meeting with name " + newMeeting.getName() + " already exists");
//      }

//      for (Meeting scheduledMeeting : scheduledMeetings) {
//         if (true) { //overlapping meeting
//            return new CalendarOperationResult(false,
//               newMeeting.getName() + " overlaps with " + scheduledMeeting.getName());
//         } else if (true) {//meeting with clashing name
//            return new CalendarOperationResult(false, "Meeting with name " + newMeeting.getName() + " already exists");
//         }
//      }
//
//      scheduledMeetings.add(newMeeting);
//      return new CalendarOperationResult(true);

      return scheduledMeetings.stream()
         .map(scheduledMeeting -> {
            if (newMeeting.getEnd().isAfter(scheduledMeeting.getStart()) && newMeeting.getStart().isBefore(scheduledMeeting.getEnd())) { //overlapping meeting
               return new CalendarOperationResult(false,
                  newMeeting.getName() + " overlaps with " + scheduledMeeting.getName());
            } else if (newMeeting.getName().equals(scheduledMeeting.getName())) {//meeting with clashing name
               return new CalendarOperationResult(false, "Meeting with name " + newMeeting.getName() + " already exists");
            }
            return null;
         })
         .filter(result -> result != null)
         .findFirst()
         .orElseGet(() -> {
            scheduledMeetings.add(newMeeting);
            return new CalendarOperationResult(true);
         });

//      return
//         scheduledMeetings.stream()
//            .filter(m -> newMeeting.getEnd().isAfter(m.getStart()) && newMeeting.getStart().isBefore(m.getEnd()))
//            .map(m -> m.getName()).findFirst()
//            .map(overlappingMeetingName -> {
//               if (!newMeeting.getName().equals(overlappingMeetingName)) {
//                  return new CalendarOperationResult(false, "Meeting with name " + newMeeting.getName() + " already exists");
//               }
//               new CalendarOperationResult(false,
//                  newMeeting.getName() + " overlaps with " + overlappingMeetingName);
//            })
//            .orElseGet(() -> {
//               scheduledMeetings.add(newMeeting);
//               return new CalendarOperationResult(true);
//            });

//   if (overlappingMeetingNameOptional.isPresent()) {
//         return new CalendarOperationResult(false,
//            newMeeting.getName()  + " overlaps with " + overlappingMeetingNameOptional.get());
//      } else {
//         scheduledMeetings.add(newMeeting);
//         return new CalendarOperationResult(true);
//      }
   }

   private boolean meetingNameOverlaps(Meeting newMeeting) {
      String newMeetingName = newMeeting.getName();
      for (Meeting m : scheduledMeetings) {
         if (m.getName().equals(newMeetingName)) {
            return true;
         }
      }
      return false;
   }

   private Optional<String> getOverlappingMeetingName(Meeting newMeeting) {

      return scheduledMeetings.stream()
         .filter(m -> newMeeting.getEnd().isAfter(m.getStart()) && newMeeting.getStart().isBefore(m.getEnd()))
         .map(m -> m.getName())
         .findFirst();
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
