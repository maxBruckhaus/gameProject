package max.project.gamewebsite;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseHandler {

    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_DATABASE =
            "CREATE DATABASE IF NOT EXISTS video_games;";

    private static final String CREATE_GAMES_SQL =
            "CREATE TABLE IF NOT EXISTS games(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "genre VARCHAR(255) NOT NULL, " +
                    "developer VARCHAR(255) NOT NULL, " +
                    "price DOUBLE NOT NULL, " +
                    "PRIMARY KEY (id));";

    @Autowired
    public DatabaseHandler(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createDatabase() {
        jdbcTemplate.execute(CREATE_DATABASE);
    }

    public void createGamesTable() {
        jdbcTemplate.execute(CREATE_GAMES_SQL);
    }
}
