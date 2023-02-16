package max.project.gamewebsite;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseHandler {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseHandler(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createDatabase() {
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS video_games");
    }


    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS games (id INT, name VARCHAR(255))");
    }
}
