package web.in312019.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.in312019.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>{
}