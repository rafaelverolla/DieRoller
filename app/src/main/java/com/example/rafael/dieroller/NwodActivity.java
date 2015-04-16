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


public class NwodActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nwod);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_nwod, menu);
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

    public void RollChanceDie(View view){//a simple die, if = 1 critical failure, if =10 success, else, failure.
        Random random = new Random();//creates an object to generate random numbers
        int die;
        String Result;
        die=RandomInteger(1,10,random);

        Result=Integer.toString(die)+ ".";


        if(die==10){
            Result=Result.concat(this.getString(R.string.Success));
        }else if(die==1){
            Result=Result.concat(this.getString(R.string.criticalfailure));
        }else{
            Result=Result.concat(this.getString(R.string.failure));
        }
        ///Displaying the result
        final TextView Result_Display= (TextView) findViewById(R.id.result_display);
        Result_Display.setText(Result);
        ///end of displaying the result


    }

    public void Roll(View view){
        Random random = new Random();//creates an object to generate random numbers

        ///start of getting the number of die
        EditText NumberOfDieInput=(EditText) findViewById(R.id.number_of_dice_input);
        Integer Number_Of_Die = parseInt(NumberOfDieInput.getText().toString());
        ///end of getting the number of die

        ///start of getting the modifier
        EditText ModifierInput=(EditText) findViewById(R.id.modifier_input);
        Integer Modifier = parseInt(ModifierInput.getText().toString());
        ///end of getting it
        int i=0; int die; int successes=0;

        String Result="";

        while(i<Number_Of_Die+Modifier){
            die=RandomInteger(1,10,random);
            Result= Result.concat(Integer.toString(die)+ " ");
            if(die>=8){
                successes++;
            }
            if(die==10) {
                i--;
            }
            i++;
        }

        Result= Result.concat(". ");

        if(successes>=1 && successes!=0){//checking the results
            Result= Result.concat(Integer.toString(successes));
            Result= Result.concat(" ");
            if(successes>1){
                Result= Result.concat(this.getString(R.string.success_plural ));
            }
            else {
                Result = Result.concat(this.getString(R.string.success));
            }
        }else if(successes<=0){
            Result= Result.concat(this.getString(R.string.failure));
        }
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
