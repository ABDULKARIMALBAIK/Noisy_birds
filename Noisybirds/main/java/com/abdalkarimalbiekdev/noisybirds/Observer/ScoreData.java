package com.abdalkarimalbiekdev.noisybirds.Observer;

import java.util.LinkedList;

public class ScoreData implements Subject{

    private LinkedList<Observer> observers;

    private int score;
    private int chicken;


    //Constructor
    public ScoreData()
    {
        observers = new LinkedList<>();
    }

    public void registerObserver(Observer o)
    {
        observers.addLast(o);
    }

    public void removeObserver(Observer o)
    {
        observers.remove(o);
    }

    public void notifyObservers()
    {
        for(Observer item : observers)
        {
            item.update(score, chicken);

        }
    }
    public void setData(int score, int chicken)
    {
        this.score = score;
        this.chicken = chicken;
        changed();
    }

    public void changed()
    {
        notifyObservers();
    }
}
