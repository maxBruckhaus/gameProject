package max.project.gamewebsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class DatabaseHandler {

    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_DATABASE_SQL =
            "CREATE DATABASE IF NOT EXISTS video_games;";

    private static final String CREATE_GAMES_SQL =
            "CREATE TABLE IF NOT EXISTS games(" +
                    "game_id CHAR(32) PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "description TEXT NOT NULL, " +
                    "price VARCHAR(255) NOT NULL, " +
                    "release_date VARCHAR(255) NOT NULL, " +
                    "platform VARCHAR(255) NOT NULL);";

    private static final String ADD_GAME_SQL =
            "REPLACE INTO games (game_id, title, description, price, release_date, platform) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String GET_ALL_GAMES_SQL =
            "SELECT * FROM games;";

    private static final String SEARCH_GAMES_BY_NAME_SQL =
            "SELECT * FROM games " +
                    "WHERE title LIKE ?";

    private static final String SEARCH_GAMES_LESS_PRICE_SQL =
            "SELECT * FROM games " +
                    "WHERE title LIKE ? "+
                    "AND price < ?;";

    private static final String SEARCH_GAMES_GREATER_PRICE_SQL =
            "SELECT * FROM games " +
                    "WHERE title LIKE ? " +
                    "AND price >= ?;";

    private List<Game> gameList;

    @Autowired
    public DatabaseHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createDatabase() {
        jdbcTemplate.execute(CREATE_DATABASE_SQL);
        jdbcTemplate.execute("USE video_games;");
    }

    public void createGamesTable() {
        jdbcTemplate.execute(CREATE_GAMES_SQL);
    }

    public Status addGame(String id, String title, String description,
                          String price, String releaseDate, String platform) {
        Status status = Status.ERROR;

        try {
            jdbcTemplate.update(ADD_GAME_SQL, id, title, description, price, releaseDate, platform);
            status = Status.OK;
        } catch (Exception ex) {
            status = Status.SQL_EXCEPTION;
            ex.printStackTrace();
        }

        return status;
    }

    public boolean populateGamesSQL(Game[] games){
        for (Game g : games) {
            Status gameStatus = addGame(g.getId(), g.getTitle(), g.getDescription(), g.getPrice(),
                    g.getReleaseDate(), g.getPlatform());
            if (gameStatus != Status.OK){
                System.out.println("UNABLE TO ADD GAME TO SQL");
                System.out.println(gameStatus);
                return false;
            }
        }
        return true;
    }

    public Status getAllGames() {
        Status status = Status.ERROR;

        try {
            gameList = new ArrayList<>();
            gameList = jdbcTemplate.query(GET_ALL_GAMES_SQL, new BeanPropertyRowMapper<>(Game.class));
            status = Status.OK;
        } catch (Exception ex) {
            status = Status.SQL_EXCEPTION;
            ex.printStackTrace();
        }

        return status;
    }

    public Status findGamesByNameAndPrice(String name, String price) {
        Status status = Status.ERROR;
        int priceInt = 0;

        if (price.equals("50+")) {
            priceInt = 50;
        } else if (!price.equals("all")){
            priceInt = Integer.parseInt(price);
        }

        try {
            gameList = new ArrayList<>();
            int finalPriceInt = priceInt;
            switch (price) {
                case "all" -> gameList = jdbcTemplate.query(SEARCH_GAMES_BY_NAME_SQL, ps -> {
                    ps.setString(1, "%" + name + "%");
                }, new BeanPropertyRowMapper<>(Game.class));

                case "10", "20", "50" -> gameList = jdbcTemplate.query(SEARCH_GAMES_LESS_PRICE_SQL, ps -> {
                    ps.setString(1, "%" + name + "%");
                    ps.setInt(2, finalPriceInt);
                }, new BeanPropertyRowMapper<>(Game.class));

                case "50+" -> gameList = jdbcTemplate.query(SEARCH_GAMES_GREATER_PRICE_SQL, ps -> {
                    ps.setString(1, "%" + name + "%");
                    ps.setInt(2, finalPriceInt);
                }, new BeanPropertyRowMapper<>(Game.class));
            }
            status = Status.OK;
        } catch (Exception ex) {
            status = Status.SQL_EXCEPTION;
            ex.printStackTrace();
        }

        return status;
    }

    public List<Game> getGameResults() {
        return Collections.unmodifiableList(gameList);
    }
}
