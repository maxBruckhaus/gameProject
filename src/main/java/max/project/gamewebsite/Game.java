package max.project.gamewebsite;

public class Game {

    private int id;
    private String title;
    private String developer;
    private String genre;
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

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
                "Developer: " + developer + "\n" +
                "Genre: " + genre + "\n" +
                "Price: " + price;
    }
}
