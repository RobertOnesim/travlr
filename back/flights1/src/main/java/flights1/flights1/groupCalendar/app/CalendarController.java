package flights1.flights1.groupCalendar.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import flights1.flights1.calendar.ManagerEvent;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;

@RestController
@CrossOrigin(origins = "*")
public class CalendarController {
	
	private String userId;
	private int groupId;
	
	@RequestMapping("/setCalendar")
	public void setCalendar(@RequestParam(required=true) String userId, @RequestParam(required=true) int groupId){
		this.userId= userId;
		this.groupId = groupId;
	}
	
	
	@RequestMapping(value="/uploadedCalendar", method=RequestMethod.POST)
    public void handleFileUpload( 
            @RequestParam("file") MultipartFile file){
            try {
            	System.out.println("yes");
            	
            	InputStream fin = file.getInputStream();		
            	CalendarBuilder builder = new CalendarBuilder();
            	Calendar calendar = builder.build(fin);
            	for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            	    Component component = (Component) i.next();
           	    
            	    String startDate="",name="";
            	    for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
            	        Property property = (Property) j.next();
            	        if(property.getName() == "DTSTART"){
            	        	startDate = property.getValue();
                    	}
            	        if(property.getName() == "SUMMARY") name=property.getValue();
            	        System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
            	    }
            	}
            } catch (Exception e) {
            	System.out.println("Incorect file");
            }
            
            
    }
	
	
	
	/*private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static HttpTransport HTTP_TRANSPORT;
	
	@RequestMapping("/tokensignin")
	public @ResponseBody String verifyGoogleToken(@RequestParam(required=true) String token){
		System.out.println("sadasdad");
		String email =null;
		try {
			System.out.println("sadasdad");
			// Use access token to call API
			GoogleCredential credential = new GoogleCredential().setAccessToken(token);
			Calendar service = new Calendar.Builder(
	                HTTP_TRANSPORT, JSON_FACTORY, credential)
	                .build();			
			System.out.println(service);
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

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(HTTP_TRANSPORT, JSON_FACTORY);
			// (Receive idTokenString by HTTPS POST)
			GoogleIdToken idToken = GoogleIdToken.parse(JSON_FACTORY, token);

			System.out.println("token: " + idToken);
			
			if (verifier.verify(idToken)) {
				Payload payload = idToken.getPayload();
				// Print user identifier
				String userId = payload.getSubject();
				System.out.println("User ID: " + userId);
				// Get profile information from payload
				email = payload.getEmail();
				System.out.println("User mail: " + email);
				boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				String name = (String) payload.get("name");
				String pictureUrl = (String) payload.get("picture");
				String locale = (String) payload.get("locale");
				String familyName = (String) payload.get("family_name");
				String givenName = (String) payload.get("given_name");
			} else {
				// error
				System.out.println("Invalid ID token.");
			}

		} finally {
			return email;
		}
	}
	*/
	
}
