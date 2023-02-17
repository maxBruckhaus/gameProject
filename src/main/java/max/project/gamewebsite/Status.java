package max.project.gamewebsite;

public enum Status {
    /*
     * Creates several Status enum types. The Status name and message is
     * given in the NAME(message) format below. The Status ordinal is
     * determined by its position in the list. (For example, OK is the
     * first element, and will have ordinal 0.)
     */

    OK("No errors occurred."),
    ERROR("Unknown error occurred."),
    MISSING_CONFIG("Unable to find configuration file."),
    MISSING_VALUES("Missing values in configuration file."),
    CONNECTION_FAILED("Failed to establish a database connection."),
    CREATE_FAILED("Failed to create necessary tables."),
    INVALID_GAME("Invalid game id"),
    DUPLICATE_GAME("Game with that name already exists."),
    SQL_EXCEPTION("Unable to execute SQL statement.");

    private final String message;

    private Status(String message) {
        this.message = message;
    }

    /**
     * Returns a message
     * @return String
     */
    public String message() {
        return message;
    }

    /**
     * Overrides toString method to return a string
     * @return String
     */
    @Override
    public String toString() {
        return this.message;
    }
}
