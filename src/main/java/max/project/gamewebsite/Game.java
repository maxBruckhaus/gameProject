package max.project.gamewebsite;

import java.time.LocalDate;

public class Game {

    private int id;
    private String title;
    private String description;
    private String platform;
    private LocalDate releaseDate;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
