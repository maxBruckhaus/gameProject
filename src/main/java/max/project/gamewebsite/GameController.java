package max.project.gamewebsite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

    @Autowired
    private DatabaseHandler dbHandler;

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Game> gameList = new ArrayList<>();
        Status allGamesStatus = dbHandler.getAllGames();

        try{
            if (allGamesStatus == Status.OK){
                gameList = dbHandler.getGameResults();
            }else{
                System.out.println("Error: Unable to get all games.");
                System.out.println(allGamesStatus);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        model.addAttribute("gameList", gameList);
        return "home";
    }

    @GetMapping("/search")
    public String searchGames(@RequestParam(name = "name", required = false) String name,
                              @RequestParam(name = "price", required = false) String price,
                              Model model) {
        List<Game> searchResults = new ArrayList<>();
        Status searchGamesStatus = dbHandler.findGamesByNameAndPrice(name, price);

        try{
            if (searchGamesStatus == Status.OK){
                searchResults = dbHandler.getGameResults();
            }else{
                System.out.println("Error: Unable to get all games.");
                System.out.println(searchGamesStatus);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
