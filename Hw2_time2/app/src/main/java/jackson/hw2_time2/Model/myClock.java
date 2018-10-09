package jackson.hw2_time2.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class myClock {
    Calendar clock;
    SimpleDateFormat format;
    String title;

    public myClock(String title){
        this.clock = Calendar.getInstance(); //default to current time
        this.format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.title = title;
    }

    public void setTitle( String s){
        this.title = s; //calendar.set
    }

    public String getTitle(){
        return this.title;
    }

    public void addSec(){
        this.clock.set(Calendar.SECOND, this.clock.get(Calendar.SECOND)+1);
    }
    public void subSec(){
        this.clock.set(Calendar.SECOND, this.clock.get(Calendar.SECOND)-1);
    }
    public void addMin(){
        this.clock.set(Calendar.MINUTE, this.clock.get(Calendar.MINUTE)+1);
    }
    public void subMin(){
        this.clock.set(Calendar.MINUTE, this.clock.get(Calendar.MINUTE)-1);
    }
    public void addHour(){
        this.clock.set(Calendar.HOUR, this.clock.get(Calendar.HOUR)+1);
    }
    public void subHour(){
        this.clock.set(Calendar.HOUR, this.clock.get(Calendar.HOUR)-1);
    }
    public void addDay(){
        this.clock.set(Calendar.DAY_OF_MONTH, this.clock.get(Calendar.DAY_OF_MONTH)+1);
    }
    public void subDay(){
        this.clock.set(Calendar.DAY_OF_MONTH, this.clock.get(Calendar.DAY_OF_MONTH)-1);
    }
    public void addMonth(){
        this.clock.set(Calendar.MONTH, this.clock.get(Calendar.MONTH)+1);
    }
    public void subMonth(){
        this.clock.set(Calendar.MONTH, this.clock.get(Calendar.MONTH)-1);
    }
    public void addYear(){
        this.clock.set(Calendar.YEAR, this.clock.get(Calendar.YEAR)+1);
    }
    public void subYear(){
        this.clock.set(Calendar.YEAR, this.clock.get(Calendar.YEAR)-1);
    }

}

//google how to open android date picker dialogue
//make controlller first

