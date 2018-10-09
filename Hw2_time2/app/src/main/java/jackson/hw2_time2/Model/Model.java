package jackson.hw2_time2.Model;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import jackson.hw2_time2.Controller.Controller;

public class Model {
    public Controller _c;
    public TimerManager timerManager;

    public List<myClock> clocks;
    public ArrayList<TextView> titles;
    public ArrayList<TextView> clockTextViews; // the UI digital clocks
    public ArrayList<ProgressBar> progressBars;
    public Stack<String> actions;
    public Stack<String> undoneActions;

    public Model(Controller c){
        this._c = c;
    }

    public void initialize(){ //start default clock
        this.timerManager = new TimerManager(_c);
        this.timerManager.startTimer();
        this.clocks = new ArrayList<myClock>();
        this.titles = new ArrayList<TextView>();
        this.clockTextViews = new ArrayList<TextView>();
        this.progressBars = new ArrayList<ProgressBar>();
        this.actions = new Stack<String>();
        this.undoneActions = new Stack<String>();
    }

    public TimerManager getTimerManager(){
        return this.timerManager;
    }
}

// setters call c.update before returning
