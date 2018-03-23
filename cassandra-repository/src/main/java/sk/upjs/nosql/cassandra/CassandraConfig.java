package sk.upjs.nosql.cassandra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Configuration
@EnableCassandraRepositories
@ComponentScan(basePackages = "sk.upjs.nosql.cassandra.simplestudent")
public class CassandraConfig extends AbstractCassandraConfiguration {

	static final String HOSTNAME = "nosql.gursky.sk";
	static final String KEY_SPACE = "gursky";
	
	public Session getSession() {
		Cluster cluster = Cluster.builder().addContactPoint(HOSTNAME).build();
		return cluster.connect(KEY_SPACE);
	}
	
	@Bean
	public CqlTemplate getCqlTemplate(Session session) {
		return new CqlTemplate(session);
	}

	@Override
	protected String getKeyspaceName() {
		return KEY_SPACE;
	}
	
	@Override
	protected String getContactPoints() {		
		return HOSTNAME;
	}
}
