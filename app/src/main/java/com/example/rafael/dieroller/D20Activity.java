package com.example.rafael.dieroller;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Integer.parseInt;


public class D20Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d20);

        //////////start of populating the spinner
        Spinner spinner = (Spinner) findViewById(R.id.damage_input);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_sides_string, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
/////////end of populating the spinner
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_d20, menu);
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

    public void RollD20(View view) {

        ///getting the difficulty class
        EditText myEditText = (EditText) findViewById(R.id.dc_input);
        Integer dC = parseInt(myEditText.getText().toString());
        ///finish it

        ///getting the modifier
        EditText myEditText2 = (EditText) findViewById(R.id.modifier_input);
        Integer mod = parseInt(myEditText2.getText().toString());
        ///finish it

        Random random = new Random();//creates an object to generate random numbers
        String result;
        int d20;

        d20 = getRandomInteger(1, 20, random);//generates the number

        result = Integer.toString(d20);//put the number on a string


        result += " + (" + Integer.toString(mod) + ") = " + Integer.toString(d20 + mod) + " ";

        if (d20 + mod >= dC) {
            result += this.getString(R.string.Success);
        } else {
            result += this.getString(R.string.failure);
        }

        ///Displaying the result
        final TextView Result_Display = (TextView) findViewById(R.id.result_display);
        Result_Display.setText(result);
        ///end of displaying the result
    }

    public void RollDamage(View view) {

        ///getting the number of die
        EditText myEditText = (EditText) findViewById(R.id.damage_input_text);
        Integer NumberOfDie = parseInt(myEditText.getText().toString());
        ///finish it

        ///getting the modifier
        EditText myEditText2 = (EditText) findViewById(R.id.modifierdmg_input);
        Integer Mod = parseInt(myEditText2.getText().toString());
        ///finish it


        ///get the value selected in the spinner, for the type of dice
        Spinner mySpinner = (Spinner) findViewById(R.id.damage_input);
        Integer NumberOfFaces = parseInt(mySpinner.getSelectedItem().toString());
        ///end of getting the value of the spinner

        int dice;
        int total = 0;

        Random random = new Random();//creates an object to generate random numbers

        int i = 1;
        while (i <= NumberOfDie) {
            dice = getRandomInteger(1, NumberOfFaces, random);
            total = total + dice + Mod;
            i++;
        }


        ///Displaying the result
        final TextView Result_Display = (TextView) findViewById(R.id.result_display);
        Result_Display.setText(Integer.toString(total) + " damage");
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
