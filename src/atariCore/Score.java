package atariCore;

/**
 * Holds and sorts score to be shown on leaderboards.
 */
public class Score implements Comparable<Score> {

    private int score;
    private String name;
    private int level;

    /**
     * Parameterised constructor to initialise current player's score.
     *
     * @param score Current player's score.
     * @param name  Player's name.
     * @param level Player's level.
     */
    public Score(int score, String name, int level) {
        this.score = score;
        this.level = level;
        this.name = name;
    }

    /**
     * Returns player's score.
     *
     * @return Player's score.
     */
    public int getScore() {
        return score;
    }


    /**
     * Returns player's name.
     *
     * @return Player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns player's level.
     *
     * @return Player's level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets player' score.
     *
     * @param score Player's score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets player' name.
     *
     * @param name player' score.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets player's level.
     *
     * @param level player's level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Score o) {
        return o.getScore() - score;
    }
}

