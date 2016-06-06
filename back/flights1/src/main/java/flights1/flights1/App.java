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
		
		SpringApplication.run(App.class, args);
	}
}

