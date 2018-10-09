package jackson.hw2_time2.View;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import jackson.hw2_time2.Controller.Controller;
import jackson.hw2_time2.R;

public class MainActivity extends AppCompatActivity {

    private static Button secondsPlus;
    private static Button secondsMinus;
    private static Button minutePlus;
    private static Button minuteMinus;
    private static Button hourPlus;
    private static Button hourMinus;
    private static Button dayPlus;
    private static Button dayMinus;
    private static Button monthPlus;
    private static Button monthMinus;
    private static Button yearPlus;
    private static Button yearMinus;

    private static TextView titleInput;

    private static Button createNewButton;
    private static Button toggleClockViewButton;
    private static Button undo;
    private static Button redo;

    private static TextView digital;

    private Controller c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////initialize UI
        secondsPlus = (Button)findViewById(R.id.secondsPlus);
        secondsMinus = (Button)findViewById(R.id.secondsMinus);
        minutePlus = (Button)findViewById(R.id.minutePlus);
        minuteMinus = (Button)findViewById(R.id.minuteMinus);
        hourPlus = (Button)findViewById(R.id.hourPlus);
        hourMinus = (Button)findViewById(R.id.hourMinus);
        dayPlus = (Button)findViewById(R.id.dayPlus);
        dayMinus = (Button)findViewById(R.id.dayMinus);
        monthPlus = (Button)findViewById(R.id.monthPlus);
        monthMinus = (Button)findViewById(R.id.monthMinus);
        yearPlus = (Button)findViewById(R.id.yearPlus);
        yearMinus = (Button)findViewById(R.id.yearMinus);

        titleInput = (TextView)findViewById(R.id.titleInput);
        toggleClockViewButton = (Button)findViewById(R.id.toggleClockView);
        undo = (Button)findViewById(R.id.undoButton);
        redo = (Button)findViewById(R.id.redoButton);

        toggleClockViewButtonListener();
        undo();
        redo();

        incSecondsListener();
        decSecondsListener();
        incMinuteListener();
        decMinuteListener();
        incHourListener();
        decHourListener();
        incDayListener();
        decDayListener();
        incMonthListener();
        decMonthListener();
        incYearListener();
        decYearListener();

        //// initialize other stuff

        this.c = new Controller(this);
        c.initialize();
        initialize();

        ///////////CREATE NEW CLOCK WITH ABOVE TIME AND DATE BUTTON////////////
        Button createNewClock = (Button) findViewById(R.id.createNewButton);
        createNewClock.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        ViewGroup layout = (ViewGroup) findViewById(R.id.clocksArea1); //get the linear layout

                        TextView title = new TextView(getApplicationContext()); //create a title textview and give it content
                        title.setText(titleInput.getText().toString()); //set title contents
                        TextView digital = (TextView) new TextView(getApplicationContext()); //make new textview where time will be
                        ProgressBar bar = (ProgressBar) new ProgressBar(getApplicationContext(), null, android.R.attr.progressBarStyleHorizontal); //make progress bar
                        bar.setVisibility(ProgressBar.GONE);

                        c.createClock(titleInput.getText().toString(), title, digital, bar); //make new clock in controller

                        layout.addView(title);
                        layout.addView(digital);
                        layout.addView(bar);
                    }
                }
        );
    }

    public void makeNewClock(String t){
        ViewGroup layout = (ViewGroup) findViewById(R.id.clocksArea1); //get the linear layout

        TextView title = new TextView(getApplicationContext()); //create a title textview and give it content
        title.setText(t); //set title contents
        TextView digital = (TextView) new TextView(getApplicationContext()); //make new textview where time will be
        ProgressBar bar = (ProgressBar) new ProgressBar(getApplicationContext(), null, android.R.attr.progressBarStyleHorizontal); //make progress bar
        bar.setVisibility(ProgressBar.GONE);
        c.createClock(titleInput.getText().toString(), title, digital, bar); //make new clock in controller
        layout.addView(title);
        layout.addView(digital);
        layout.addView(bar);
    }

    public void initialize(){ //make default clock
        ViewGroup layout = (ViewGroup) findViewById(R.id.clocksArea1); //get the linear layout
        TextView title = new TextView(getApplicationContext()); //create a title textview and give it content
        title.setText(titleInput.getText().toString()); //set title contents
        TextView digital = (TextView) new TextView(getApplicationContext()); //make new textview where time will be
        ProgressBar bar = (ProgressBar) new ProgressBar(getApplicationContext(), null, android.R.attr.progressBarStyleHorizontal); //make progress bar
        bar.setVisibility(ProgressBar.GONE);
        c.createClock(titleInput.getText().toString(), title, digital, bar); //make new clock in controller
        layout.addView(title);
        layout.addView(digital);
        layout.addView(bar);
    }

    public void updateView(final String content, final TextView t, final ProgressBar bar, final int percentOfDay){
        runOnUiThread(new Runnable() { // have to do this in mainactivity
            @Override
            public void run() {
                t.setText(content);
                bar.setProgress(percentOfDay);
            }
        });
    }

    //////////// toogles visibility of digital and progress
    public void toggleClockViewButtonListener(){
        toggleClockViewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        c.toggleClockView();
                    }
                }
        );
    }

    public void undo(){
        undo.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        c.undo();
                    }
                }
        );
    }

    public void redo(){
        redo.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        c.redo();
                    }
                }
        );
    }

    public void deleteClock(TextView title, TextView digital, ProgressBar bar){
        ViewGroup layout = (ViewGroup) findViewById(R.id.clocksArea1); //get the linear layout
        layout.removeView(title);
        layout.removeView(digital);
        layout.removeView(bar);
    }

    /////////////// + - handlers
    public void incSecondsListener(){ secondsPlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("sec+", titleInput.getText().toString()); }});}

    public void decSecondsListener(){ secondsMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("sec-", titleInput.getText().toString()); }});}

    public void incMinuteListener(){ minutePlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("min+", titleInput.getText().toString()); }});}

    public void decMinuteListener(){ minuteMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("min-", titleInput.getText().toString()); }});}

    public void incHourListener(){ hourPlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("hour+", titleInput.getText().toString()); }});}

    public void decHourListener(){ hourMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("hour-", titleInput.getText().toString()); }});}

    public void incDayListener(){ dayPlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("day+", titleInput.getText().toString()); }});}

    public void decDayListener(){ dayMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("day-", titleInput.getText().toString()); }});}

    public void incMonthListener(){ monthPlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("month+", titleInput.getText().toString()); }});}

    public void decMonthListener(){ monthMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("month-", titleInput.getText().toString()); }});}

    public void incYearListener(){ yearPlus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("year+", titleInput.getText().toString()); }});}

    public void decYearListener(){ yearMinus.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            c.changeTime("year-", titleInput.getText().toString()); }});}


}

/* Notes
actually
make add buttons work
make  create new button work


ok so just ignore updating the analog clock for now
focus on creating the digital clock with set start time.
might have to do it manually, but make it creatable before making it functional

 first figure out how to create new clocks with specified times. fragments? do that in main to start
then make it so main activity still adds the views, but it uses Clock to generate the view. pass input params to clock
now make it so main activity can control/edit the views

eventaully make all the buttons add to a cmdStack.push(button press) which can be undone

 */
