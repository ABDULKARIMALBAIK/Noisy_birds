package com.abdalkarimalbiekdev.noisybirds.Observer;

public class Data implements Observer{

    private Subject scoreData;

    private int score;
    private int chicken;

    public Data(Subject scoreData)
    {
        this.scoreData = scoreData;
        scoreData.registerObserver(this);
    }

//    public void update(int score, int chicken)
//    {
//        this.score = score;
//        this.chicken = chicken;
//    }

    public String display()
    {
        return new StringBuilder().append(score).append("/").append(chicken).toString();
    }

    @Override
    public void update(int score, int chicken) {
        this.score = score;
        this.chicken = chicken;
    }
}
