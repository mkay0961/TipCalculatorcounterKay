package com.example.matthewkay.tipcalculator_counter;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    private TextView billBefore, numpeople, tip, billAfter, perPerson, tipCost;
    private Button back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        billBefore = (TextView) findViewById(R.id.billBeforeID);
        numpeople = (TextView) findViewById(R.id.numSplitID);
        tip = (TextView) findViewById(R.id.tipID);
        billAfter = (TextView) findViewById(R.id.billAfterID);
        perPerson = (TextView) findViewById(R.id.afterPerPersonID);
        tipCost = (TextView) findViewById(R.id.tipCostID);

        Bundle b = getIntent().getExtras();

        billBefore.setText(getResources().getString(R.string.billBeforeTip)+ String.format( "%.2f", b.getDouble("@string/billKEY")));
        numpeople.setText(getResources().getString(R.string.numPeople)+ " "+ b.getInt("@string/numberOfPeopleKEY"));
        tip.setText(getResources().getString(R.string.tip)+ String.format( "%.2f", b.getDouble("@string/tipPercentKEY")) + getResources().getString(R.string.percentSign));
        billAfter.setText(getResources().getString(R.string.billAfterTip)+ String.format( "%.2f", b.getDouble("@string/billAfterKEY")));
        perPerson.setText(getResources().getString(R.string.billPerPerson)+ String.format( "%.2f", b.getDouble("@string/personOweKEY")));
        tipCost.setText(getResources().getString(R.string.tipCost)+ String.format( "%.2f", b.getDouble("@string/tipCostKEY")));

        back = (Button) findViewById(R.id.backID);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(getApplicationContext(),"BACK TO CALCULATOR!!",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.finish();
    }

}
