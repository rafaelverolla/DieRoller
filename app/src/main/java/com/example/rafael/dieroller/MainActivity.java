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


public class MainActivity extends ActionBarActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//////////start of populating the spinner
        Spinner spinner = (Spinner) findViewById(R.id.number_of_sides_input);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Roll(View view) {///used xml. will need change to onclicklistener when using fragments.


        ///get the value selected in the spinner
        Spinner mySpinner=(Spinner) findViewById(R.id.number_of_sides_input);
        Integer Number_of_Faces = parseInt(mySpinner.getSelectedItem().toString());
        ///end of getting the value of the spinner

        ///start of getting the number of die
        EditText myEditText=(EditText) findViewById(R.id.number_of_die_input);
        Integer Number_Of_Die = parseInt(myEditText.getText().toString());
        ///end of getting the number of die

        Random random = new Random();//creates an object to generate random numbers
        String Result="";
        int i=1;
        while(i<=Number_Of_Die){

          Result = Result.concat(Integer.toString(RandomInteger(1,Number_of_Faces,random)));//generates a number
          if(i!=Number_Of_Die) {
              Result = Result.concat(", ");
          }
          i++;
        }
        Result = Result.concat(".");


        ///Displaying the result
        final TextView Result_Display= (TextView) findViewById(R.id.result_display);
         Result_Display.setText(Result);
        ///end of displaying the result
    }

    private static int RandomInteger(int aStart, int aEnd, Random aRandom){///function that generates a random number between 1 and N



        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);

        return randomNumber;
    }



}
