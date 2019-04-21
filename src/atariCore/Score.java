package atariCore;

import java.awt.*;
import java.util.Comparator;

public class Score implements Comparable<Score>{

    private int score;
    private String name;
    private int level;

    public Score(int score, String name , int level) {
        this.score = score;
        this.level = level;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(Score o) {
        if(o.getLevel() != getLevel())
            return o.getLevel() - getLevel();
        return o.getScore() - score;
    }
}

