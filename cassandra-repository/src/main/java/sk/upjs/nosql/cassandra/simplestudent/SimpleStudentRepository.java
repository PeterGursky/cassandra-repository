package sk.upjs.nosql.cassandra.simplestudent;

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

public interface SimpleStudentRepository extends CrudRepository<CassandraSimpleStudent, Long>{
	
	@AllowFiltering
	List<CassandraSimpleStudent> findByPriezvisko(String priezvisko);
}
