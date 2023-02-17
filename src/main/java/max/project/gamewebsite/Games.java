package max.project.gamewebsite;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Games {
    private Map<String, Game> gameMap = new TreeMap<>();

    public void populateGameMap(Game[] games) {
        for (Game g : games) {
            gameMap.put(g.getId(), g);
        }
    }
}
