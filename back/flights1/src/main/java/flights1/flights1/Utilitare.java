package flights1.flights1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utilitare {
	public static String parseInputStream(InputStream inputStream) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		//System.out.println("Raspuns:   " +response);
		in.close();
		return response.toString();
	}
}
