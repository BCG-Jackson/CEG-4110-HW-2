package jackson.hw2_time2.Controller;

import android.widget.ProgressBar;
import android.widget.TextView;

import jackson.hw2_time2.Model.myClock;
import jackson.hw2_time2.Model.Model;
import jackson.hw2_time2.View.MainActivity;


public class Controller {
    private Model _m;
    private MainActivity ma;

    public Controller(MainActivity ma){
        this._m = new Model(this); //when a controller is made, a model is made linking controller
        this.ma = ma;
    }

///// to model
    public void initialize(){
        _m.initialize();
    }
    public void startClock(myClock c){
        _m.getTimerManager().startClock(c);
    }

    public myClock createClock(String title, TextView t, TextView digital, ProgressBar bar){
        myClock c = new myClock(title);
        boolean clockIsDuplicate = false;
        for (myClock clock:_m.clocks) {
            if (clock.getTitle().equals(title)){
                clockIsDuplicate=true;
                break;
            }
        }
        _m.clocks.add(c);
        _m.titles.add(t);
        _m.clockTextViews.add(digital);
        _m.progressBars.add(bar);
        if (!clockIsDuplicate){
            startClock(c);
        }
        _m.actions.push("createClock");
        return c;
    }

    //changes time depending on what button was pressed and what title is in title of clock input area
    public void changeTime(String s, String title){
        for (myClock c:_m.clocks) {
            String clockName = c.getTitle();
            if (clockName.equals(title)){
                switch (s) {
                    case "sec+": c.addSec();
                        break;
                    case "sec-": c.subSec();
                        break;
                    case "min+": c.addMin();
                        break;
                    case "min-": c.subMin();
                        break;
                    case "hour+": c.addHour();
                        break;
                    case "hour-": c.subHour();
                        break;
                    case "day+": c.addDay();
                        break;
                    case "day-": c.subDay();
                        break;
                    case "month+": c.addMonth();
                        break;
                    case "month-": c.subMonth();
                        break;
                    case "year+": c.addYear();
                        break;
                    case "year-": c.subYear();
                        break;
                }
                _m.actions.push(title +"`"+ s);
            }
        }

    }

///// to views
    public void updateView(String output, String title, int percentOfDay){
        int index=0;
        for (myClock c:_m.clocks) {
            if (c.getTitle().equals(title)){
                ma.updateView(output, _m.clockTextViews.get(index), _m.progressBars.get(index), percentOfDay);
            }
            index++;
        }
    }

    public void toggleClockView() { //makes digital clocks progress bars and vice
        int index = 0;
        boolean digitalVisible = true;
        if (_m.clockTextViews.get(0).getVisibility() == TextView.GONE){
            digitalVisible = false;
        }
        for (myClock c:_m.clocks) {
            if(!digitalVisible) { //if first digital is gone
                _m.clockTextViews.get(index).setVisibility(TextView.VISIBLE);
                _m.progressBars.get(index).setVisibility(ProgressBar.GONE);
            }
            else{
                _m.clockTextViews.get(index).setVisibility(TextView.GONE);
                _m.progressBars.get(index).setVisibility(ProgressBar.VISIBLE);
            }
            index++;
        }
        _m.actions.push("toggleClockView");
    }

    public void undo() {
        if (_m.actions.isEmpty()){
            return;
        }
        String action = _m.actions.pop();
        _m.undoneActions.push(action);
        if (action.equals("toggleClockView")){
            toggleClockView();
            _m.actions.pop(); //get rid of extra clockview action
        } else if(action.equals("createClock")){ //delete clock
            _m.undoneActions.pop(); //get rid of most recent undo action
            _m.undoneActions.push("createClock`" + _m.clocks.get(_m.clocks.size()-1).getTitle() );

            ma.deleteClock(_m.titles.get(_m.titles.size()-1),_m.clockTextViews.get(_m.clockTextViews.size()-1), _m.progressBars.get(_m.progressBars.size()-1));
            _m.clocks.remove(_m.clocks.size() -1);
            _m.titles.remove(_m.titles.size() -1);
            _m.clockTextViews.remove(_m.clockTextViews.size() -1);
            _m.progressBars.remove(_m.progressBars.size() -1);
        } else {
            String[] split = action.split("\\`");
            for (myClock c:_m.clocks) {
                String clockName = c.getTitle();
                if (clockName.equals(split[0])){ //if this clocks name is same as name of clock in action
                    switch (split[1]) {
                        case "sec+": c.subSec();
                            break;
                        case "sec-": c.addSec();
                            break;
                        case "min+": c.addMin();
                            break;
                        case "min-": c.subMin();
                            break;
                        case "hour+": c.subHour();
                            break;
                        case "hour-": c.addHour();
                            break;
                        case "day+": c.subDay();
                            break;
                        case "day-": c.addDay();
                            break;
                        case "month+": c.subMonth();
                            break;
                        case "month-": c.addMonth();
                            break;
                        case "year+": c.subYear();
                            break;
                        case "year-": c.addYear();
                            break;
                    }
                }
            }
        }
    }

    public void redo() {
        if (_m.undoneActions.isEmpty()){
            return;
        }
        String action = _m.undoneActions.pop();
        String[] split = action.split("\\`");
        if (action.equals("toggleClockView")) {
            toggleClockView();
        } else if(split[0].equals("createClock")){
            ma.makeNewClock(split[1]);
        } else {
            changeTime(split[1],split[0] );
        }

    }
}