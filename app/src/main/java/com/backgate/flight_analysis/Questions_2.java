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


public class Questions_2 extends ActionBarActivity{

    private SeekBar seekBar;
    private TextView textView;
    private TextView textView_Quest;
    private Button prev_button;
    private Button next_button;
    public String questions_mix[]=new String[15];
    public String questions_mix_1[]=new String[15];
    public String questions_mix_2[]=new String[15];
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
        textView_Quest.setText("How physically demanding was the task?");
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
            public void onClick(View view) {
                Intent myIntent = new Intent(Questions_2.this, Questions_3.class);
                for(int i=0; i<33; i++){
                    if("physical_demand".equals(answers_all[i])){
                        values_all[i]=String.valueOf(textView.getText()).substring(0, String.valueOf(textView.getText()).length()-1);
                        //System.out.println(values_all[i]);
                    }
                }
                myIntent.putExtra("string-array", questions_mix);
                myIntent.putExtra("string-array-1", questions_mix_1);
                myIntent.putExtra("string-array-2", questions_mix_2);
                myIntent.putExtra("string-array-ans-text", answers_all);
                myIntent.putExtra("string-array-ans-vals", values_all);
                Questions_2.this.startActivity(myIntent);
                //finish();
            }
        });

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
            android.os.Process.killProcess(process_id);
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
