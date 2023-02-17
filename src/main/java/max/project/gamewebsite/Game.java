package max.project.gamewebsite;

import com.google.gson.annotations.SerializedName;

public class Game {

    private String id;
    @SerializedName("name")
    private String title;
    private String description;
    private String platform;
    private String releaseDate;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Overrides the toString method for Game.
     * @return String This is the formatted game information.
     */
    @Override
    public String toString() {
        return  "Game: " + title + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "\n" +
                "Release Date: " + releaseDate + "\n" +
                "Platform: " + platform;
    }
}
