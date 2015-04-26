package com.example.rafael.dieroller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class OwodActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owod);

//////////start of populating the spinner
        Spinner spinner = (Spinner) findViewById(R.id.diff_input);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.difficulty_string, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
/////////end of populating the spinner

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_owod, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Roll(View view) {///used xml. will need change to onclicklistener when using fragments.


        ///get the value selected in the spinner, for the diff
        Spinner mySpinner = (Spinner) findViewById(R.id.diff_input);
        Integer diff = parseInt(mySpinner.getSelectedItem().toString());
        ///end of getting the value of the spinner

        ///start of getting the number of die
        EditText NumberOfDieInput = (EditText) findViewById(R.id.number_of_die_input);
        Integer numberOfDie = parseInt(NumberOfDieInput.getText().toString());
        ///end of getting the number of die

        ///start of getting the threshold
        EditText ThresholdInput = (EditText) findViewById(R.id.threshold_input);
        Integer threshold = parseInt(ThresholdInput.getText().toString());
        ///end of getting

        CheckBox Willpower = (CheckBox) findViewById(R.id.willcheckbox);//getting willpower
        CheckBox Spec = (CheckBox) findViewById(R.id.speccheckbox);//getting spec

        Random random = new Random();//creates an object to generate random numbers
        String result = "";
        int i = 1;
        int successes = 0;
        int dice;
        if (Willpower.isChecked()) {
            successes++;
        }

        while (i <= numberOfDie) {//rolling the die
            dice = getRandomInteger(1, 10, random);//generates a number
            if (dice >= diff) {
                successes++;
            }
            if (dice == 1) {
                successes--;
            }
            if (dice == 10 && Spec.isChecked()) {
                i--;
            }
            result +=Integer.toString(dice);//put on the result string

            if (i != numberOfDie) {
                result +=", ";
            }
            i++;
        }
        result +=". ";

        if (successes >= threshold && successes != 0) {//checking the results
            result +=Integer.toString(successes);
            result +=" ";
            if (successes > 1) {
                result +=this.getString(R.string.success_plural);
            } else {
                result +=this.getString(R.string.success);
            }
        } else if (successes < 0) {
            result +=this.getString(R.string.criticalfailure);
        } else if (successes < threshold || successes == 0) {
            result +=this.getString(R.string.failure);
        }


        ///Displaying the result
        final TextView Result_Display = (TextView) findViewById(R.id.result_display);
        Result_Display.setText(result);
        ///end of displaying the result
    }

    private static int getRandomInteger(int aStart, int aEnd, Random aRandom) {///function that generates a random number between 1 and N


        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        int randomNumber = (int) (fraction + aStart);

        return randomNumber;
    }


}
