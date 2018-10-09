package jackson.hw2_time2.Model;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


import jackson.hw2_time2.Controller.Controller;

public class TimerManager extends TimerTask {
    myClock clock;
    Timer timer;
    Controller c;

    TimerManager(Controller c){
        this.c = c;
    }

    public TimerManager(myClock clock, Controller c){
        this.clock = clock;
        this.c = c;
    }

    public Timer startTimer(){
        return this.timer = new Timer();
    }

    public void startClock(myClock clock){
        this.timer.schedule(new TimerManager(clock, c), 0, 1000); //starts run()
    }

    public void run() {
        clock.clock.set(Calendar.SECOND, clock.clock.get(Calendar.SECOND)+1);
        //String output = clock.format.format(clock.clock.getTime());
        //System.out.println(clock.title + "\t" + output);
        this.c.updateView(clock.format.format(clock.clock.getTime()), this.clock.getTitle(), getPercentOfDay());

    }

    public int getPercentOfDay(){
        float sec = clock.clock.get(Calendar.SECOND);
        float min = clock.clock.get(Calendar.MINUTE);
        float hour = clock.clock.get(Calendar.HOUR_OF_DAY); // /24
        sec += min*60;
        sec += hour*60*60;
        return (int)((sec/86400)*100);
    }
}
