package com.example.rafael.dieroller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class Shadowrun5thActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadowrun5th);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_shadowrun5th, menu);
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

    public void Roll(View view) {

        ///start of getting the number of die
        EditText NumberOfDieInput = (EditText) findViewById(R.id.number_of_die_input);
        Integer numberOfDie = parseInt(NumberOfDieInput.getText().toString());
        ///end of getting the number of die

        ///start of getting the threshold
        EditText ThresholdInput = (EditText) findViewById(R.id.threshold_input);
        Integer threshold = parseInt(ThresholdInput.getText().toString());
        ///end of getting
        int i = 0;
        int dice, glitches = 0, successes = 0;
        String result = "";
        Random random = new Random();//creates an object to generate random numbers

        while (i < numberOfDie) {
            dice = getRandomInteger(1, 6, random);//generates a number
            if (dice >= 5) {
                successes++;
            } else if (dice == 1) {
                glitches++;
            }
            result += Integer.toString(dice);//put on the result string

            result +=" ";

            i++;

        }
        result +=". ";

        if (successes >= threshold) {
            result +=Integer.toString(successes);
            result +=" ";
            if (successes == 1) {
                result +=this.getString(R.string.success);
            } else {
                result +=this.getString(R.string.success_plural);
            }

            if (glitches >= (numberOfDie / 2)) {
                result +=this.getString(R.string.glitch);
            }
        } else {
            if (glitches >= (numberOfDie / 2)) {
                result +=this.getString(R.string.criticalfailure);
            } else {
                result +=this.getString(R.string.failure);
            }
        }

        ///Displaying the result
        final TextView Result_Display = (TextView) findViewById(R.id.result_display);
        Result_Display.setText(result);
        ///end of displaying the result
    }

    public void RollInit(View view) {
        ///start of getting the number of die
        EditText NumberOfDieInput = (EditText) findViewById(R.id.number_of_die_init_input);
        Integer Number_Of_Die = parseInt(NumberOfDieInput.getText().toString());
        ///end of getting the number of die

        ///start of getting the init
        EditText InitInput = (EditText) findViewById(R.id.initiative_input);
        Integer init = parseInt(InitInput.getText().toString());
        ///end of getting the number of die

        Random random = new Random();//creates an object to generate random numbers
        int i = 0;
        int dice;
        int initSum = 0;

        while (i <= Number_Of_Die) {
            dice = getRandomInteger(1, 6, random);//generates a number
            initSum = initSum + dice;
            i++;
        }
        initSum = initSum + init;

        ///Displaying the result
        final TextView Result_Display = (TextView) findViewById(R.id.result_display);
        Result_Display.setText(Integer.toString(initSum));
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
