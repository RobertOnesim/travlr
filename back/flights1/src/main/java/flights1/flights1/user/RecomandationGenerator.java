package flights1.flights1.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class RecomandationGenerator {
	public static List<Recomandation> giveRecomandations(String accessToken, String userId, String userIP){
		List <Recomandation> recomandations = new ArrayList<>();
		ManagerUserHistory muh = new ManagerUserHistory();
		 Set<String> perechiAdaugate = new HashSet<>(); 
		if ((accessToken!=null) && (!userId.contains("@"))){
			
			List <EventPlaceDate> eventsFacebook = (new FacebookController()).oferireEvents(accessToken, userId);
			for (EventPlaceDate event:eventsFacebook){
				List<String> airportsIdsNames = Aeroports.getAirportsByCoordinates(event.getLatitudine(),event.getLongitudine());
				String airportChestie = airportsIdsNames.get(0);
				List <Double> coordonatesIP = IPProcessor.getCoordinates(userIP);
				List<String> IPairportsIdsNames = Aeroports.getAirportsByCoordinates(coordonatesIP.get(0), coordonatesIP.get(1));
				String IPairportChestie = IPairportsIdsNames.get(0);
				Recomandation recomandation = new Recomandation(IPairportChestie.substring(0, 3),IPairportChestie.substring(3, IPairportChestie.length()),airportChestie.substring(0, 3),airportChestie.substring(3, airportChestie.length()),event.getData());
				if (!perechiAdaugate.contains(recomandation.getDepartureCity()+recomandation.getArrivalCity())){
					perechiAdaugate.add(recomandation.getDepartureCity()+recomandation.getArrivalCity());
					recomandations.add(recomandation);
				}
				//recomandations.add(recomandation);
			}
		}
		if (userId!=null){
			List <UserHistory> mostSearched = muh.getMostSearched(userId);
			Integer count = 0;
			for (UserHistory uh: mostSearched){
				count++;
				if (count>=10)
					break;
				//String arrivalCode=uh.getArrivalId();
				//String departureCode=uh.getDepartureId();
				//String arrivalCity=Aeroports.getCityByCode(arrivalCode).getName();
				//String departureCity=Aeroports.getCityByCode(departureCode).getName();
				String arrivalCity=uh.getArrivalId();
				String departureCity=uh.getDepartureId();
				String date = null;
				Date zi = new Date();
				Date peste5Zile = new Date(zi.getTime() + (1000 * 60 * 60 * 24*5));
				DateFormat df = new SimpleDateFormat("yyy-MM-dd");
				Recomandation recomandation = new Recomandation(null, departureCity, null, arrivalCity, df.format(peste5Zile.getTime()));
				if (!perechiAdaugate.contains(recomandation.getDepartureCity()+recomandation.getArrivalCity())){
					perechiAdaugate.add(recomandation.getDepartureCity()+recomandation.getArrivalCity());
					recomandations.add(recomandation);
				}
				//Recomandation recomandation = new Recomandation(departureCode, departureCity, arrivalCode, arrivalCity, date);
				//recomandations.add(recomandation);
			}
		}
		if ((userId==null) || (userId.contains("@"))){
			List <UserHistory> mostSearched = muh.getMostSearchedUniversal();
			Integer count = 0;
			for (UserHistory uh: mostSearched){
				count++;
				if (count>=10)
					break;
				String arrivalCity=uh.getArrivalId();
				String departureCity=uh.getDepartureId();
				String date = null;
				Date zi = new Date();
				Date peste5Zile = new Date(zi.getTime() + (1000 * 60 * 60 * 24*5));
				DateFormat df = new SimpleDateFormat("yyy-MM-dd");
				Recomandation recomandation = new Recomandation(null, departureCity, null, arrivalCity, df.format(peste5Zile.getTime()));
				//Recomandation recomandation = new Recomandation(departureCode, departureCity, arrivalCode, arrivalCity, date);
				/*if (!perechiAdaugate.contains(recomandation.getDepartureCity()+recomandation.getArrivalCity())){
					perechiAdaugate.add(recomandation.getDepartureCity()+recomandation.getArrivalCity());
					recomandations.add(recomandation);
				}*/
				recomandations.add(recomandation);
			}
		}
		return recomandations;
	}
}
