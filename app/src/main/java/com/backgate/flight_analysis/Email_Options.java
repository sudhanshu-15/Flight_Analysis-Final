package com.backgate.flight_analysis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Email_Options extends ActionBarActivity {

    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    public String filePath = baseDir + File.separator + "Task Load Analysis";
    //public TextView textView_attach;
    private CheckBox[] file_names = new CheckBox[1000];
    private EditText email_add;
    private Button email_sub_but;
    public String arc_filePath;
    private List<File> files = getListFiles(new File(filePath));
    private static ArrayList<String> checked_file = new ArrayList<String>();
    private int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_opt);
        initializeVariables();

        LinearLayout email_table = (LinearLayout) findViewById(R.id.rel_lay_email);
        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //params.addRule(RelativeLayout.BELOW,R.id.attach_txt);
        for (int i = 0; i < files.size(); i++) {
            file_names[i] = new CheckBox(getApplicationContext());
            //file_names.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            //file_names.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.BELOW, R.id.attach_txt));
            file_names[i].setId(i);
            file_names[i].setTextColor(getResources().getColor(R.color.black));
            file_names[i].setText(files.get(i).getName());
            email_table.addView(file_names[i]);
            //params.addRule(RelativeLayout.BELOW,file_names.getId());
        }

        //LinearLayout button_lay = (LinearLayout)findViewById(R.id.buttonlayout);
        LinearLayout.LayoutParams button_lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        button_lay.gravity = Gravity.CENTER_HORIZONTAL;
        email_sub_but = new Button(this);
        email_sub_but.setText("Send email");
        email_table.addView(email_sub_but, button_lay);

        for (int i = 0; i < files.size(); i++ ){
            final int j = i;
            file_names[j].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try{
                        if (file_names[j].isChecked()) {
                            checked_file.add("sdcard/Task Load Analysis/" + file_names[j].getText().toString());
                        }
                        if (!file_names[j].isChecked()){
                            checked_file.remove("sdcard/Task Load Analysis/" + file_names[j].getText().toString().trim() );
                        }
                    }catch (Exception e){
                        //System.out.print(e);
                    }

                }
            });
        }


        addListenerOnButton(email_sub_but);
    }

    private void addListenerOnButton(Button email_btn) {
        email_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<Uri> uris = new ArrayList<Uri>();
                for (String files_check : checked_file){
                    File fileIn = new File(files_check.trim());
                    Uri u = Uri.fromFile(fileIn);
                    uris.add(u);
                }

                try{

                    Calendar c = Calendar.getInstance();
                    //System.out.println("Current time => " + c.getTime());

                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    String formattedDate = df.format(c.getTime());

                    Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    emailIntent.setType("multipart/mixed");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Flight Analysis Reports for date "+formattedDate);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email_add.getText().toString()});
                    emailIntent.putExtra(Intent.EXTRA_TEXT,"Please find the reports attached for date "+formattedDate);
                    emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,uris);
                    startActivity(emailIntent);
                    genCSV();
                }catch (Exception e){
                    //System.out.print(e);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_exit) {
            int process_id= android.os.Process.myPid();
            android.os.Process.killProcess(process_id);
        }

        if (id == R.id.action_main) {
            Intent myIntent = new Intent(Email_Options.this, MainActivity.class);
            Email_Options.this.startActivity(myIntent);
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
            Intent myIntent = new Intent(Email_Options.this, MainActivity.class);
            Email_Options.this.startActivity(myIntent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // A private method to help us initialize our variables.
    private void initializeVariables() {
        email_add = (EditText) findViewById(R.id.email_value);
    }

    //Fuction to create csv file
    private void genCSV() {
        arc_filePath = baseDir + File.separator + "Task Load Analysis"+File.separator+"ARCHIVE";
        File dir = new File(arc_filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    public List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".csv")) {
                inFiles.add(file);
            }
        }
        return inFiles;
    }

}
