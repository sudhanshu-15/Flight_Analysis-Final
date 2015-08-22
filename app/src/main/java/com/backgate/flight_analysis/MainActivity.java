package com.backgate.flight_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private Button button;
    private EditText first_name;
    private EditText middle_name;
    private EditText last_name;
    private EditText task_name;
    private EditText age_name;
    private RadioButton button1;
    private RadioButton button2;
    private EditText height_name;
    private EditText weight_name;
    private EditText flying_exp_name;
    private EditText subject_id_name;
    private EditText organisation_name;
    private EditText task_id_name;
    public String questions_mix[] = new String[15];
    public String questions_mix_1[] = new String[15];
    public String questions_mix_2[] = new String[15];
    public String answers_all[] = {"subject_id", "first_name", "middle_name", "last_name", "organisation", "task_id", "task_performed", "age", "gender", "height", "weight", "fly_exp",
            "mental_demand", "physical_demand", "temporal_demand", "performance", "effort", "frustration",
            "perf_frus", "temp_eff", "temp_ment", "ment_phy", "phy_temp", "frus_eff", "temp_frus", "phy_perf", "phy_frus",
            "eff_phy", "perf_temp", "ment_eff", "perf_ment", "eff_perf", "frus_ment"};
    public String values_all[]=new String[33];
    Random myRandom = new Random();
    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    public String filePath = baseDir + File.separator + "Task Load Analysis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int k=0; k<33; k++){
            values_all[k]="null";
        }
        for (int j = 0; j < 15; j++) {
            questions_mix[j] = "-1";
            questions_mix_1[j] = "-1";
            questions_mix_2[j] = "-1";
        }
        for (int i = 0; i < 15; i++) {
            int random_val_found = 0;
            String random_value = "null";
            while (random_val_found == 0) {
                random_value = String.valueOf(myRandom.nextInt(15));
                for (int j = 0; j < 15; j++) {
                    //random_value=String.valueOf(myRandom.nextInt(15));
                    if (random_value.equals(questions_mix[j])) {
                        random_val_found = 0;
                        break;
                    } else {
                        random_val_found = 1;
                    }
                }
            }
            questions_mix[i] = random_value;
            questions_mix_1[i] = "Questions_2_" + random_value;
        }

        /*for (int j = 0; j < 15; j++) {
            System.out.println(questions_mix[j] + ", " + questions_mix_1[j] + ", " + questions_mix_2[j]);
        }*/
        initializeVariables();
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(subject_id_name.getText()) == null || String.valueOf(subject_id_name.getText()).trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter the Subject ID.",
                            Toast.LENGTH_LONG).show();
                    subject_id_name.requestFocus();
                } else if ((String.valueOf(task_id_name.getText()) == null && String.valueOf(task_name.getText()) == null)
                        || (String.valueOf(task_id_name.getText()).trim().equals("") && String.valueOf(task_name.getText()).trim().equals(""))
                        || (String.valueOf(task_id_name.getText()).trim().equals("") && String.valueOf(task_name.getText()) == null)
                        || (String.valueOf(task_id_name.getText()) == null && String.valueOf(task_name.getText()).trim().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Please enter either Task ID or Task Name.",
                            Toast.LENGTH_LONG).show();
                    task_id_name.requestFocus();
                } else {
                    Intent myIntent = new Intent(MainActivity.this, Questions_1.class);
                    for (int i = 0; i < 33; i++) {
                        if ("first_name".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(first_name.getText());
                        }
                        if ("middle_name".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(middle_name.getText());
                        }
                        if ("last_name".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(last_name.getText());
                        }
                        if ("task_performed".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(task_name.getText());
                        }
                        if ("age".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(age_name.getText());
                        }
                        if ("gender".equals(answers_all[i])) {
                            if (button1.isChecked()) {
                                values_all[i] = String.valueOf(button1.getText());
                            } else if (button2.isChecked()) {
                                values_all[i] = String.valueOf(button2.getText());
                            }
                        }
                        if ("height".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(height_name.getText());
                        }
                        if ("weight".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(weight_name.getText());
                        }
                        if ("fly_exp".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(flying_exp_name.getText());
                        }
                        if ("subject_id".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(subject_id_name.getText());
                        }
                        if ("organisation".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(organisation_name.getText());
                        }
                        if ("task_id".equals(answers_all[i])) {
                            values_all[i] = String.valueOf(task_id_name.getText());
                        }
                    }
                    myIntent.putExtra("string-array", questions_mix);
                    myIntent.putExtra("string-array-1", questions_mix_1);
                    myIntent.putExtra("string-array-2", questions_mix_2);
                    myIntent.putExtra("string-array-ans-text", answers_all);
                    myIntent.putExtra("string-array-ans-vals", values_all);
                    MainActivity.this.startActivity(myIntent);
                    //finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_2, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        File dir = new File(filePath);
        if (!dir.exists()){
            menu.getItem(0).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_mail) {
            Intent myIntent = new Intent(MainActivity.this, Email_Options.class);
            MainActivity.this.startActivity(myIntent);
            finish();
            return true;
        }

        if (id == R.id.action_exit) {
            int process_id= android.os.Process.myPid();
            android.os.Process.killProcess(process_id);
        }

        return super.onOptionsItemSelected(item);
    }

    // A private method to help us initialize our variables.
    private void initializeVariables() {
        button = (Button) findViewById(R.id.next_button);
        first_name=(EditText)findViewById(R.id.first_name_value);
        middle_name=(EditText)findViewById(R.id.middle_name_value);
        last_name=(EditText)findViewById(R.id.last_name_value);
        task_name=(EditText)findViewById(R.id.task_value);
        age_name=(EditText)findViewById(R.id.age_value);
        button1= (RadioButton)findViewById(R.id.radio_male);
        button2= (RadioButton)findViewById(R.id.radio_female);
        height_name=(EditText)findViewById(R.id.height_value);
        weight_name=(EditText)findViewById(R.id.weight_value);
        flying_exp_name=(EditText)findViewById(R.id.flying_exp_value);
        subject_id_name=(EditText)findViewById(R.id.subject_id_value);
        organisation_name=(EditText)findViewById(R.id.org_name_value);
        task_id_name=(EditText)findViewById(R.id.task_id_value);
    }
}
