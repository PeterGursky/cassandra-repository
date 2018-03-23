package sk.upjs.nosql.cassandra.simplestudent;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.gursky.nosql.aislike.DaoFactory;
import sk.gursky.nosql.aislike.StudentDao;
import sk.gursky.nosql.aislike.entity.SimpleStudent;

@Service
public class SimpleStudentService {
	
	@Autowired
	private SimpleStudentRepository repository;
	private StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();
	
	public void vlozData() {
		List<SimpleStudent> simpleStudents = studentDao.getSimpleStudents();
		List<CassandraSimpleStudent> css = simpleStudents.stream()
				.map(s -> new CassandraSimpleStudent(s)).collect(Collectors.toList());
		repository.saveAll(css);
		System.out.println("vlozenych simpleStudentov: " + css.size());
	}
	
	public void najdiPodlaPriezviska() {
		String podla = "Rojlulas";
		List<CassandraSimpleStudent> list = repository.findByPriezvisko(podla);
		System.out.println(list);
	}
}
