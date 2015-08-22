package com.backgate.flight_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Processing extends ActionBarActivity {

    private TextView textView_directory;
    private TextView textView_file;
    public String answers_all[]=new String[33];
    public String values_all[]=new String[33];
    public String[] answers_all1 = {"Subject_ID", "First_Name", "Middle_Name", "Last_Name", "Organisation", "Task_ID", "Task_Name", "Age", "Gender", "Height", "Weight", "Flying_Exp", "null",
            "MD_Rating", "PD_Rating", "TD_Rating", "Performance_Rating", "Effort_Rating", "Frustration_Rating", "null",
            "MD_Wt", "PD_Wt", "TD_Wt", "Effort_Wt", "Performance_Wt", "Frustration_Wt", "null", "MD_Weighted_Load", "PD_Weighted_Load", "TD_Weighted_Load",
            "Effort_Weighted_Load", "Performance_Weighted_Load", "Frustration_Weighted_Load", "null", "WWL"};
    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public String fileName;
    public String filePath;
    public File f;
    public float count[] = {0,0,0,0,0,0,0,0,0,0,0,0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.processing);
        answers_all = getIntent().getStringArrayExtra("string-array-ans-text");
        values_all = getIntent().getStringArrayExtra("string-array-ans-vals");

        Toast.makeText(getApplicationContext(), "Please press the back button to return to Home Page.",
                Toast.LENGTH_LONG).show();

        initializeVariables();
        performCal();
        genCSV();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_mail) {
            Intent myIntent = new Intent(Processing.this, Email_Options.class);
            Processing.this.startActivity(myIntent);
            finish();
        }

        if (id == R.id.action_exit) {
            int process_id= android.os.Process.myPid();
            android.os.Process.killProcess(process_id);
        }

        if (id == R.id.action_main) {
            Intent myIntent = new Intent(Processing.this, MainActivity.class);
            Processing.this.startActivity(myIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
            //Things to Do
            Toast.makeText(getApplicationContext(), "Returning back to the Home Page.",
                    Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(Processing.this, MainActivity.class);
            Processing.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // A private method to help us initialize our variables.
    private void initializeVariables() {
        textView_directory = (TextView) findViewById(R.id.folder_value_txt);
        textView_file = (TextView) findViewById(R.id.file_value_txt);
    }

    //Fuction to create csv file
    private void genCSV() {
        String timeStmp = sdf.format(new Date());
        fileName = values_all[1] + "_" + values_all[6];
        fileName = fileName + "_" + timeStmp + ".csv";
        filePath = baseDir + File.separator + "Task Load Analysis";
        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        filePath = filePath + File.separator + fileName;


        f = new File(filePath);

        String values_all1[]=new String[35];
        int j=0;
        for(int i=0; i<35; i++){
            if(answers_all1[i].equals("null")){
                answers_all1[i]="";
            }
            else{
                values_all1[i]=values_all[j];
                j++;
            }
        }
        CSVWriter writer;
        FileWriter mfileWriter;

        try{
            if(f.exists() && !f.isDirectory()){
                mfileWriter = new FileWriter(filePath, true);
                writer = new CSVWriter(mfileWriter);
            }
            else{
                writer = new CSVWriter(new FileWriter(filePath));
            }
            writer.writeNext(answers_all1);
            writer.writeNext(values_all1);
            writer.close();
            textView_directory.setText(filePath);
            textView_file.setText(fileName);

        }catch (IOException e){
            //e.printStackTrace();
            Toast.makeText(this,"File generation failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void performCal() {
        for (int i = 18; i < 33; i++){
            String ans = values_all[i].trim();
            if (ans.equals("Mental Demand")){
                count[0] = count[0] + 1;
            }
            if (ans.equals("Physical Demand")){
                count[1] = count[1] + 1;
            }
            if (ans.equals("Temporal Demand")){
                count[2] = count[2] + 1;
            }
            if (ans.equals("Effort")){
                count[3] = count[3] + 1;
            }
            if(ans.equals("Performance")){
                count[4] = count[4] + 1;
            }
            if (ans.equals("Frustration")){
                count[5] = count[5] + 1;
            }
        }

        for(int i = 0; i < 6; i++){
            count[i] = count[i]/15;
        }

        count[6] = count[0] * Float.parseFloat(values_all[12]);
        count[7] = count[1] * Float.parseFloat(values_all[13]);
        count[8] = count[2] * Float.parseFloat(values_all[14]);
        count[9] = count[3] * Float.parseFloat(values_all[15]);
        count[10] = count[4] * Float.parseFloat(values_all[16]);
        count[11] = count[5] * Float.parseFloat(values_all[17]);

        for (int i = 18 , j = 0; i < 33 && j < 12; i++, j++){
            values_all[i] = Float.toString(count[j]);
        }
        values_all[30] = Float.toString(count[6]+count[7]+count[8]+count[9]+count[10]+count[11]);
        values_all[31] = "";
        values_all[32] = "";
    }

}
