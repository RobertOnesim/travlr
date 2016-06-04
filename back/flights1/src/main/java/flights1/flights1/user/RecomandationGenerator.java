package flights1.flights1.user;

import java.util.ArrayList;
import java.util.List;

public class RecomandationGenerator {
	public static List<Recomandation> giveRecomandations(String userId){
		List <Recomandation> recomandations = new ArrayList<>();
		ManagerUserHistory muh = new ManagerUserHistory();
		List <EventPlaceDate> eventsFacebook = (new FacebookController()).oferireEvents();
		for (EventPlaceDate event:eventsFacebook){
			List<String> airportsIdsNames = Aeroports.getAirportsByCoordinates(event.getLatitudine(),event.getLongitudine());
			String airportChestie = airportsIdsNames.get(0);
			Recomandation recomandation = new Recomandation(null,null,airportChestie.substring(0, 2),airportChestie.substring(3, airportChestie.length()),event.getData());
			//De modificat mai sus la primii 2 parametri
			recomandations.add(recomandation);
		}
		List <UserHistory> mostSearched = muh.getMostSearched(userId);
		for (UserHistory uh: mostSearched){
			String arrivalCode=uh.getArrivalId();
			String departureCode=uh.getDepartureId();
			String arrivalCity=Aeroports.getCityByCode(arrivalCode);
			String departureCity=Aeroports.getCityByCode(departureCode);
			String date = null;
			Recomandation recomandation = new Recomandation(departureCode, departureCity, arrivalCode, arrivalCity, date);
			recomandations.add(recomandation);
		}
		return recomandations;
	}
}
