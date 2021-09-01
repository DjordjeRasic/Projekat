package web.in312019.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import web.in312019.entity.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long>{
	
}