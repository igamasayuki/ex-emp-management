import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/toInsert")
    public String toInsert() {
        InsertAdministratorForm form = new InsertAdministratorForm();
        return "administrator/insert";
    }

}
