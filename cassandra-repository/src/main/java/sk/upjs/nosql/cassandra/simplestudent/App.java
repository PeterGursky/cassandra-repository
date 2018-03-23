package sk.upjs.nosql.cassandra.simplestudent;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.datastax.driver.core.Session;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import sk.upjs.nosql.cassandra.CassandraConfig;

public class App 
{
    public static void main( String[] args ) {
		Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		logger.setLevel(Level.INFO);
        AnnotationConfigApplicationContext applicationContext = 
        		new AnnotationConfigApplicationContext(CassandraConfig.class);
        Session session = applicationContext.getBean(Session.class);
        SimpleStudentService service = applicationContext.getBean(SimpleStudentService.class);
        //service.vlozData();
        service.najdiPodlaPriezviska();
        session.getCluster().close(); // ukoncime spojenie s klastrom
        applicationContext.close(); // zahodime vsetky beany a ukoncime springacky aplikacny kontext
    }
}
