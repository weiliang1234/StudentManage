package com.booy.ssm.exam.pojo;

public class Score extends ScoreKey {
    private Integer score;

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}