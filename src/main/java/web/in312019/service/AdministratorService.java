package web.in312019.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.in312019.entity.Administrator;
import web.in312019.repository.AdministratorRepository;


@Service
public class AdministratorService {
	
	private final AdministratorRepository adminRepository;
	
	@Autowired
    public AdministratorService(AdministratorRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
	
	
	public Administrator findOne(Long id) {
		Administrator admin = this.adminRepository.getOne(id);
        return admin;
	}

	
	public List<Administrator> findAll() {
		List<Administrator> admini = this.adminRepository.findAll();
        return admini;
	}

	public Administrator save(Administrator admin) {
		return this.adminRepository.save(admin);
	}
	public Administrator create(Administrator admin) throws Exception {
		if (admin.getId() != null) {
            throw new Exception("ID must be null!");
        }
		Administrator newClan = this.adminRepository.save(admin);
        return newClan;
	}

	
	public void delete(Long id) {
		this.adminRepository.deleteById(id);

	}
	
	
}
