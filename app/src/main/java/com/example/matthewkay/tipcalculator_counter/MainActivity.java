package com.example.matthewkay.tipcalculator_counter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button resetButton, calulateButton;
    private EditText billAmount, numPeople, customTip;
    private RadioGroup radioGroup;
    private int numberOfPeople;
    private double billAfter, personOwe, bill, tipPercent, tipCost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = (Button) findViewById(R.id.resetID);
        calulateButton = (Button) findViewById(R.id.calculateID);
        billAmount = (EditText) findViewById(R.id.billAmountID);
        numPeople = (EditText) findViewById(R.id.splitAmountID);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        customTip = (EditText) findViewById(R.id.customInputID);

        customTip.setVisibility(View.INVISIBLE);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast= Toast.makeText(getApplicationContext(),getString(R.string.RESET),Toast.LENGTH_SHORT);
                toast.show();

                billAmount.setText(getString(R.string.blank));
                numPeople.setText(getString(R.string.blank));
                radioGroup.clearCheck();
                customTip.setVisibility(View.INVISIBLE);
                customTip.setText(getString(R.string.blank));
            }
        });

        calulateButton.setEnabled(false);

        billAmount.setOnKeyListener(mKeyListener);
        numPeople.setOnKeyListener(mKeyListener);
        customTip.setOnKeyListener(mKeyListener);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.percent10ID) {
                    customTip.setVisibility(View.INVISIBLE);
                    customTip.setText(getString(R.string.num10));

                    Toast toast=Toast.makeText(getApplicationContext(),R.string.percent10mess,Toast.LENGTH_SHORT);
                    toast.show();

                } else if (i == R.id.percent15ID) {
                    customTip.setVisibility(View.INVISIBLE);
                    customTip.setText(getString(R.string.num15));

                    Toast toast=Toast.makeText(getApplicationContext(),R.string.percent15mess,Toast.LENGTH_SHORT);
                    toast.show();

                } else if (i == R.id.percent20ID) {
                    customTip.setVisibility(View.INVISIBLE);
                    customTip.setText(getString(R.string.num20));

                    Toast toast=Toast.makeText(getApplicationContext(),R.string.percent20mess,Toast.LENGTH_SHORT);
                    toast.show();

                } else if (i == R.id.percentCustomID) {
                    customTip.setText(getString(R.string.blank));
                    customTip.setVisibility(View.VISIBLE);

                    Toast toast=Toast.makeText(getApplicationContext(),R.string.percentCustomMess,Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        calulateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast toast=Toast.makeText(getApplicationContext(),R.string.Calcinfo,Toast.LENGTH_SHORT);
                toast.show();

                tipPercent = Double.parseDouble(customTip.getText().toString());
                numberOfPeople = Integer.parseInt(numPeople.getText().toString());
                bill = Double.parseDouble(billAmount.getText().toString());

                if (numberOfPeople < 1) {
                    showErrorAlert(getString(R.string.PeopleERROR), 1);
                } else if (tipPercent < 1) {
                    showErrorAlert(getString(R.string.TipERROR), 2);
                } else if (bill < 1) {
                    showErrorAlert(getString(R.string.BillERROR), 3);
                } else if ((numberOfPeople >= 1) && (tipPercent >= 1) && (bill >= 1)) {
                    calculateInfo(bill, numberOfPeople, tipPercent);

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    Bundle b = new Bundle();

                    b.putDouble(getString(R.string.tipPercentKEY), tipPercent);
                    b.putInt(getString(R.string.numberOfPeopleKEY), numberOfPeople);
                    b.putDouble(getString(R.string.billKEY), bill);
                    b.putDouble(getString(R.string.tipCostKEY), tipCost);
                    b.putDouble(getString(R.string.billAfterKEY), billAfter);
                    b.putDouble(getString(R.string.billAfterKEY), personOwe);
                    intent.putExtras(b);
                    startActivity(intent);

                }
            }
        });

        if(savedInstanceState != null){
            billAmount.setText(String.valueOf(savedInstanceState.getInt(getString(R.string.BILLAMOUNTKEY))));
            numPeople.setText(String.valueOf(savedInstanceState.getInt(getString(R.string.NUMPEOPLEKEY))));
            customTip.setText(String.valueOf(savedInstanceState.getInt(getString(R.string.CUSTOMTIPKEY))));
        }
    }

    public void calculateInfo(double bill, int numpeople, double tip) {
        billAfter = (bill + (bill * (tip / 100)));
        personOwe = billAfter / numpeople;
        tipCost = (bill * (tip / 100));
    }

    private void showErrorAlert(String errorMessage, final int fieldId) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setNeutralButton("Close",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                findViewById(fieldId).requestFocus();
                            }
                        }).show();
    }

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            switch (v.getId()) {
                case R.id.billAmountID:
                    if (billAmount.getText().length() > 0 && numPeople.getText().length() > 0 && customTip.getText().length() > 0 ) {
                        calulateButton.setEnabled(true);
                    }
                    else{
                        calulateButton.setEnabled(false);
                    }
                    break;
                case R.id.splitAmountID:
                    if (billAmount.getText().length() > 0 && numPeople.getText().length() > 0 && customTip.getText().length() > 0 ) {
                        calulateButton.setEnabled(true);
                    }
                    else{
                        calulateButton.setEnabled(false);
                    }
                    break;
                case R.id.customInputID:
                    if (billAmount.getText().length() > 0 && numPeople.getText().length() > 0 && customTip.getText().length() > 0 ) {
                        calulateButton.setEnabled(true);
                    }
                    else{
                        calulateButton.setEnabled(false);
                    }
                    break;
            }
            return false;
        }

    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.BILLAMOUNTKEY), billAmount.getText().toString());
        outState.putString(getString(R.string.NUMPEOPLEKEY), numPeople.getText().toString());
        outState.putString(getString(R.string.CUSTOMTIPKEY), customTip.getText().toString());



    }

}