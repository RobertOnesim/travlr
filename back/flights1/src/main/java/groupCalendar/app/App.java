package groupCalendar.app;

import java.io.IOException;
import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

public class App {
	 public static void main(String[] args) throws IOException {
		 
	        com.google.api.services.calendar.Calendar service = CalendarQuickstart.getCalendarService();

	        // List the next 10 events from the primary calendar.
	        Events events = service.events().list("primary")
	            .setMaxResults(10)
	            .setOrderBy("startTime")
	            .setSingleEvents(true)
	            .execute();
	        List<Event> items = events.getItems();
	        if (items.size() == 0) {
	            System.out.println("No upcoming events found.");
	        } else {
	            System.out.println("Upcoming events");
	            for (Event event : items) {
	                DateTime start = event.getStart().getDateTime();
	                if (start == null) {
	                    start = event.getStart().getDate();
	                }
	                System.out.printf("%s (%s)\n", event.getSummary(), start);
	            }
	        }
	    }
}
