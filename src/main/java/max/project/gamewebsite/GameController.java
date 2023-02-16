package max.project.gamewebsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    private DatabaseHandler dbHandler;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "price", required = false) String price,
                              Model model) {
        Game exampleGame = new Game();
        List<Game> games = new ArrayList<Game>();
        games.add(exampleGame);
//        List<Game> games = dbHandler.findByNameAndPrice(name, price);
        model.addAttribute("games", games);
        return "searchResults";
    }

//    @GetMapping("/")
//    public String index(Model model) {
//        // Retrieve data from the database using JDBC
//        List<Game> games = new ArrayList<>();
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM games");
//            while (rs.next()) {
//                Game game = new Game();
//                game.setId(rs.getInt("id"));
//                game.setTitle(rs.getString("title"));
//                game.setDescription(rs.getString("description"));
//                game.setPrice(rs.getDouble("99.99"));
//                LocalDate currDate = LocalDate.now();
//                game.setReleaseDate(currDate);
//                game.setPlatform("platform");
//                games.add(game);
//            }
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Add the data to the model for the view to use
//        model.addAttribute("games", games);
//
//        // Return the name of the view to render
//        return "index";
//    }
}
