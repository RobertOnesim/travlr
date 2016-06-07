package flights1.flights1;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import flights1.*;
import flights1.flights1.user.Aeroports;
import flights1.flights1.user.ManagerUser;
import flights1.flights1.user.ManagerUserHistory;
import flights1.flights1.user.Recomandation;
import flights1.flights1.user.RecomandationGenerator;
import flights1.flights1.user.User;
import flights1.flights1.user.UserHistory;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		
		//ManagerUser mu = new ManagerUser();

		/* Add few employee records in database */
		//String userID1 = mu.addUser("id-ulLianei","Liana", "Tucar",new Date());
		//String userID2 = mu.addUser("id19404","Stan", "Rares", new Date());
		//String userID3 = mu.addUser("babalaaaauid231","Stan-Tucar", "Alfred", new Date());
		//mu.addUserToGroup("id-ulLianei", 1);
		//System.out.println("BLAAA "+userID1+" "+userID2+" "+userID3);
		//List <User> list= mu.getUsersFromGroup(1);
		//System.out.println(list.get(0));
		//System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAAAA");
		//User user= new User();
		SpringApplication.run(App.class, args);
	/*	List<Recomandation> recomandations = RecomandationGenerator.giveRecomandations("EAAGs3pTUYkABAO7Bbesi8LF3hvlPUPNzn4l3WxTMYuy3Wj9tsBUZAGKzFbu33XbgxIf36tsluFTrGTFHtEh3tWqCunJZA2vKl0wQqYZCryHunE1SUks4LajTzKRnMtR2iWInv0JdVb9J02QUtEJN5871CeyYz0a3r90KrVF8AZDZD","1206723179359743");
		System.out.println("Recomandari   ");
		for(Recomandation recomandation:recomandations){
			System.out.println("Recomandare "+recomandation.getDepartureCity()+recomandation.getArrivalCity()+recomandation.getDate());
		}*/
		/*Aeroports a = new Aeroports();
		System.out.println(a.getAirportsByCoordinates(latitude, longitude)
				a.getAiroportsByCoordinates());
*/
		ManagerUserHistory muh = new ManagerUserHistory();
		List <UserHistory> uhs = muh.getMostSearched("1");
		for (UserHistory u : uhs){
			System.out.println("id history  "+u.getIdHistory());
		}
		
		JSONParser parser = new JSONParser();
		String s = "{\"age\":100,\"name\":\"mkyong.com\",\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]}";

		/*try{
			JSONObject obj = (JSONObject)new JSONParser().parse("{\"Code\": \"AB\",\"DisplayCode\": \"AB\",\"Id\": 834,\"ImageUrl\": \"http://s1.apideeplink.com/images/airlines/AB.png\",\"Name\": \"Air Berlin\"}");
			String code = (String)obj.get("Code");

			Integer id = ((Long)obj.get("Id")).intValue();

			System.out.println(code+id);
			/*Object obj = parser.parse(s);
			//JSONArray array = (JSONArray)obj;
			JSONObject o = new JSONO
			JSONObject object = new JSONObject("{\"age\":100,\"name\":\"mkyong.com\",\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]}");

			System.out.println("de pe 1   "+object.ge);

			JSONArray msg = (JSONArray) object.get("messages");
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());

			}
		}catch(ParseException pe){

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}*/
	}
}

