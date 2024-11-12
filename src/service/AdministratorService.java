import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class AdministratorService {
    @Autowired
    private AdministratorRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee load(Integer id) {
        return repository.load(id);
    }

    public Employee update() {
        return repository.update();
    }
}
