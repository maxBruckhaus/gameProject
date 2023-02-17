package max.project.gamewebsite;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class GameLoader {

    private DatabaseHandler dbHandler;
    private Games games;

    @Autowired
    public GameLoader(DatabaseHandler dbHandler, Games games) {
        this.dbHandler = dbHandler;
        this.games = games;
    }

    public boolean loadGames(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader br = new FileReader(filePath)) {
            String fileData = new String(Files.readAllBytes(Paths.get(filePath)));
            JsonParser parser = new JsonParser();
            JsonElement jElement = parser.parse(fileData);
            JsonArray jsonArr = jElement.getAsJsonArray();
            Game[] gamesArr = gson.fromJson(jsonArr, Game[].class);
            for (Game g : gamesArr) {
                System.out.println("g = " + g);
            }

            games.populateGameMap(gamesArr);
            if (!dbHandler.populateGamesSQL(gamesArr)){
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error: Could not read the file: " + filePath);
            System.out.println("Exiting program...");
            return false;
        }
        return true;
    }
}
