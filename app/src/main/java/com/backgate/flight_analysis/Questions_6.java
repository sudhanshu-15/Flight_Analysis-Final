package com.backgate.flight_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class Questions_6 extends ActionBarActivity{

    private SeekBar seekBar;
    private TextView textView;
    private TextView textView_Quest;
    private Button prev_button;
    private Button next_button;
    public String questions_mix[]=new String[15];
    public String questions_mix_1[]=new String[15];
    public String questions_mix_2[]=new String[15];
    public String question_val;
    public int inc;
    public String answers_all[]=new String[33];
    public String values_all[]=new String[33];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);

        initializeVariables();
        questions_mix = getIntent().getStringArrayExtra("string-array");
        questions_mix_1 = getIntent().getStringArrayExtra("string-array-1");
        questions_mix_2 = getIntent().getStringArrayExtra("string-array-2");
        answers_all = getIntent().getStringArrayExtra("string-array-ans-text");
        values_all = getIntent().getStringArrayExtra("string-array-ans-vals");

        for(int i=0; i<15; i++){
            if("-1".equals(questions_mix_2[i])){
                question_val= questions_mix[i];
                inc= i;
                //System.out.println("You are at question 6 "+questions_mix[i]+", "+i);
                break;
            }
        }

        //System.out.println("You are at question 6 "+question_val+", "+inc);

        textView_Quest.setText("How insecure, discouraged, irritated, stressed and annoyed were you?");
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(5);
        seekBar.setMax(100);
        textView.setText(seekBar.getProgress()+"%");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progresValue = progresValue / 5;
                progresValue = progresValue * 5;
                progress = progresValue;
                textView.setText(progresValue+"%");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText(progress+"%");
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText(progress+"%");
            }
        });
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Class> activityList = new ArrayList<>();
                activityList.add(Questions_2_0.class);
                activityList.add(Questions_2_1.class);
                activityList.add(Questions_2_2.class);
                activityList.add(Questions_2_3.class);
                activityList.add(Questions_2_4.class);
                activityList.add(Questions_2_5.class);
                activityList.add(Questions_2_6.class);
                activityList.add(Questions_2_7.class);
                activityList.add(Questions_2_8.class);
                activityList.add(Questions_2_9.class);
                activityList.add(Questions_2_10.class);
                activityList.add(Questions_2_11.class);
                activityList.add(Questions_2_12.class);
                activityList.add(Questions_2_13.class);
                activityList.add(Questions_2_14.class);

                Random generator = new Random();
                int number = generator.nextInt(activityList.size()) + 1;

                Class activity = null;

                switch (number){
                    case 1:
                        activity = Questions_2_0.class;
                        activityList.remove(Questions_2_0.class);
                        break;
                    case 2:
                        activity = Questions_2_1.class;
                        activityList.remove(Questions_2_1.class);
                        break;
                    case 3:
                        activity = Questions_2_2.class;
                        activityList.remove(Questions_2_2.class);
                        break;
                    case 4:
                        activity = Questions_2_3.class;
                        activityList.remove(Questions_2_3.class);
                        break;
                    case 5:
                        activity = Questions_2_4.class;
                        activityList.remove(Questions_2_4.class);
                        break;
                    case 6:
                        activity = Questions_2_5.class;
                        activityList.remove(Questions_2_5.class);
                        break;
                    case 7:
                        activity = Questions_2_6.class;
                        activityList.remove(Questions_2_6.class);
                        break;

                    case 8:
                        activity = Questions_2_7.class;
                        activityList.remove(Questions_2_7.class);
                        break;
                    case 9:
                        activity = Questions_2_8.class;
                        activityList.remove(Questions_2_8.class);
                        break;
                    case 10:
                        activity = Questions_2_9.class;
                        activityList.remove(Questions_2_9.class);
                        break;
                    case 11:
                        activity = Questions_2_10.class;
                        activityList.remove(Questions_2_10.class);
                        break;
                    case 12:
                        activity = Questions_2_11.class;
                        activityList.remove(Questions_2_11.class);
                        break;
                    case 13:
                        activity = Questions_2_12.class;
                        activityList.remove(Questions_2_12.class);
                        break;
                    case 14:
                        activity = Questions_2_13.class;
                        activityList.remove(Questions_2_13.class);
                        break;
                    default:
                        activity = Questions_2_14.class;
                        activityList.remove(Questions_2_14.class);
                        break;
                }

                Intent myIntent = new Intent(Questions_6.this, activity);
                for (int i = 0; i < 33; i++){
                    if ("frustration".equals(answers_all[i])){
                        values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                    }
                }
                myIntent.putExtra("string-array", questions_mix);
                myIntent.putExtra("string-array-1", questions_mix_1);
                myIntent.putExtra("string-array-2", questions_mix_2);
                myIntent.putExtra("string-array-ans-text", answers_all);
                myIntent.putExtra("string-array-ans-vals", values_all);
                myIntent.putExtra("ACTIVITY_LIST", activityList);
                Questions_6.this.startActivity(myIntent);
            }
        });
        /*
        if(question_val.equals("0")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_0.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                           //System.out.println(values_all[i]);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    ////finish();
                }
            });
        }

        if(question_val.equals("1")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_1.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("2")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_2.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("3")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_3.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("4")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_4.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("5")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_5.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("6")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_6.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("7")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_7.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("8")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_8.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("9")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_9.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("10")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_10.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("11")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_11.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("12")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_12.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("13")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_13.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }

        if(question_val.equals("14")) {
            next_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Questions_6.this, Questions_2_14.class);
                    for(int i=0; i<33; i++){
                        if("frustration".equals(answers_all[i])){
                            values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        }
                    }
                    questions_mix_2[inc]="1";
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    Questions_6.this.startActivity(myIntent);
                    //finish();
                }
            });
        }*/

        prev_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            int process_id= android.os.Process.myPid();
            for(int i=0; i<25; i++) {
                android.os.Process.killProcess(process_id);
            }
        }

        return super.onOptionsItemSelected(item);
    }*/

    // A private method to help us initialize our variables.
    private void initializeVariables() {
        seekBar = (SeekBar) findViewById(R.id.cardview_elevation_seekbar);
        textView = (TextView) findViewById(R.id.answer_value);
        textView_Quest = (TextView) findViewById(R.id.question_text);
        prev_button = (Button) findViewById(R.id.prev_button);
        next_button = (Button) findViewById(R.id.next_button);
    }

}
