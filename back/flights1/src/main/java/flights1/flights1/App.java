package flights1.flights1;

import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAAAA");
		SpringApplication.run(App.class, args);



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

