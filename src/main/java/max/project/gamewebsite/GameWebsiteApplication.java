package max.project.gamewebsite;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameWebsiteApplication {
    private DatabaseHandler dbHandler;

    public GameWebsiteApplication(DatabaseHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(GameWebsiteApplication.class, args);
    }

    @PostConstruct
    public void init() {
        dbHandler.createDatabase();
        dbHandler.createGamesTable();
    }
}
