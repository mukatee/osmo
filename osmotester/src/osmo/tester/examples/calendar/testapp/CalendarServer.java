package osmo.tester.examples.calendar.testapp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Teemu Kanstren
 */
public class CalendarServer {
  private static final CalendarServer singleton = new CalendarServer();
  private Map<String, CalendarApplication> calendars = new HashMap<String, CalendarApplication>();

  private CalendarServer() {
  }

  public void addUser(CalendarUser user) {
    calendars.put(user.getUserId(), user.getCalendar());
  }

  public void deleteEvent(String uid, String eventId) {
    for (CalendarApplication calendar : calendars.values()) {
      if (calendar.getUid().equals(uid)) {
        //avoid infinite recursion
        continue;
      }
      calendar.removeEvent(eventId, true);
    }
  }

  public CalendarApplication calendarFor(String uid) {
    return calendars.get(uid);
  }

  public static CalendarServer getServer() {
    return singleton;
  }
}
