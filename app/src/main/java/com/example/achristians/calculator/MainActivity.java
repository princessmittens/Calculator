package com.example.achristians.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.*;

public class MainActivity extends AppCompatActivity {

    // variables
    private TextView output, res;
    private Button no1, no2, no3, no4,
            no5, no6, no7, no8, no9, no0,
            mul, div, add, sub, sqrt, clear,
            equal, neg, point, AC;
    private double curnum = 0;
    private char operator = 'd';
    private double secondnum = 0;
    boolean secondoperand = false;
    boolean calc = true;
    double storedResult = 0;
    int operandcount = 0;
    boolean isNegative = false;
    char lastButtonPress = 'd';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button ID's
        no1 = findViewById(R.id.no1);
        no2 = findViewById(R.id.no2);
        no3 = findViewById(R.id.no3);
        no4 = findViewById(R.id.no4);
        no5 = findViewById(R.id.no5);
        no6 = findViewById(R.id.no6);
        no7 = findViewById(R.id.no7);
        no8 = findViewById(R.id.no8);
        no9 = findViewById(R.id.no9);
        no0 = findViewById(R.id.no0);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.div);
        add = findViewById(R.id.add);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        output = findViewById(R.id.output);
        neg = findViewById(R.id.neg);
        AC = findViewById(R.id.AC);
        sqrt = findViewById(R.id.sqrt);
        clear = findViewById(R.id.clear);
        res = findViewById(R.id.res);

        //Clear textview, and reset everything to 0
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("0");
                curnum = 0;
                secondnum = 0;
                res.setText("");
                operandcount = 0;
                secondoperand = false;
                lastButtonPress = 'a';
            }
        });

        //Clear last text entered
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = output.getText().toString();
                output.setText("");
                String s = res.getText().toString();
                s = s.replace(temp, "");
                res.setText(s);
                lastButtonPress = 'c';

            }
        });

        /**
         * Buttons from 0-9 that assign a value to each integer and send it to the
         * textview through the numberFunc()
         */
        no0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(0);
                lastButtonPress = '0';
            }
        });

        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(1);
                lastButtonPress = '1';
            }
        });
        no2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(2);
                lastButtonPress = '2';
            }
        });
        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(3);
                lastButtonPress = '3';
            }
        });
        no4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(4);
                lastButtonPress = '4';
            }
        });
        no5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(5);
                lastButtonPress = '5';
            }
        });
        no6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(6);
                lastButtonPress = '6';
            }
        });
        no7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(7);
                lastButtonPress = '7';
            }
        });
        no8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(8);
                lastButtonPress = '8';

            }
        });
        no9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberFunc(9);
                lastButtonPress = '9';
            }
        });

        /**
         * Division button that passes an operator to the operandFunc() where it can be
         * sent through to the text view and calculated if on more than one operand
         */
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operandFunc('/');
                lastButtonPress = '/';
            }
        });
        /**
         * Multiplication button that passes an operator to the operandFunc() where it can be
         * sent through to the text view and calculated if on more than one operand
         */
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    operandFunc('*');
                    lastButtonPress = '*';
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Multiple operands entered: " +
                                    " operand has been set to 'x'. Press 'AC' to reset or " +
                                    "continue multiplying!",
                            Toast.LENGTH_LONG).show();
                    char temp = '*';
                    operandFunc(temp);
                }
            }
        });

        /**
         * Addition button that passes an operator to the operandFunc() where it can be
         * sent through to the text view and calculated if on more than one operand
         */
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    operandFunc('+');
                    lastButtonPress = '+';
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Multiple operands entered: " +
                                    " operand has been set to '+'. Press 'AC' to reset or " +
                                    "continue adding!",
                            Toast.LENGTH_LONG).show();
                    char temp = '+';
                    operandFunc(temp);
                }
            }
        });
        /**
         * Subtraction button that passes an operator to the operandFunc() where it can be
         * sent through to the text view and calculated if on more than one operand
         */
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    operandFunc('-');
                    lastButtonPress = '-';
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Multiple operands entered: " +
                                    " operand has been set to '-'. Press 'AC' to reset or " +
                                    "continue subtracting!",
                            Toast.LENGTH_LONG).show();
                    char temp = '-';
                    operandFunc(temp);
                }
            }
        });
        /**
         * Makes the current number a negative
         */
        neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = output.getText().toString();
                String s = res.getText().toString();
                s = s.replace(temp, "");
                    double temp1 = Double.parseDouble(output.getText().toString());
                temp1 = temp1*(-1);
                output.setText(Double.toString(temp1));
                res.setText(s + "(" + temp1 + ")");
            }
        });
        /**
         * Takes the square root of the current number
         */
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = output.getText().toString();
                String s = res.getText().toString();
                s = s.replace(temp, "");
                double temp2 = Double.parseDouble(output.getText().toString());
                temp2 = Math.sqrt(temp2);
                output.setText(Double.toString(temp2));
                res.setText(s + temp2);
                lastButtonPress = 's';
            }
        });
        /**
         * Makes the number a decimal
         */
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText(output.getText().toString() + ".");
                res.setText(res.getText().toString() + ".");
                lastButtonPress = 'p';
            }
        });
        /**
         * Calculates final result when equals button is pressed
         */
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (lastButtonPress == '=') {
                    Toast.makeText(getApplicationContext(), " You pressed equals twice!" +
                                    "Press 'AC' to reset content.",
                            Toast.LENGTH_LONG).show();
                    } else {
                operandcount = 0;
                lastButtonPress = '=';
                double result = compute(curnum);
                output.setText(Double.toString(result));
                if (secondoperand == true) {
                    res.setText(Double.toString(result));
                }
                secondoperand = false;
                calc = true;
                }
            }
        });
    }

    private void numberFunc(int i) {
        if (output.getText().toString().equals("0") || calc == true) {
            calc = false;
            output.setText(Integer.toString(i));
            res.setText(res.getText().toString() + i);
        } else if (secondoperand == true) {
            output.setText(output.getText().toString() + i);
        } else {
            output.setText(output.getText().toString() + i);
            res.setText(res.getText().toString() + i);
        }
    }

    /**
     * Calculates all process for the opera d
     *
     * @param operand
     */
    private void operandFunc(char operand) {
        operandcount++;
        //If user enters two consecutive operands
        if ((operandcount >= 2) && ((lastButtonPress == '*') || (lastButtonPress == '/')
                || (lastButtonPress == '+') || (lastButtonPress == '-'))) {
            Toast.makeText(getApplicationContext(), "Multiple operands entered! " +
                            " Operand has been set to " + operator + ". Please enter a " +
                            "second number to continue! ",
                    Toast.LENGTH_LONG).show();
            String temp = output.getText().toString();
            output.setText("");
            String s = res.getText().toString();
            s = s.replace(temp, "");
            res.setText(s);
            // if more than one operand is entered
        } else if (operandcount == 2) {
            //store value and display on the textview
            storedResult = compute(curnum);
            res.setText(Double.toString(storedResult));
            curnum = storedResult;
            secondoperand = true;
            output.setText("");
            operator = operand;
            // for all calculations that have more than operands (doesn't display result)
        } else if (operandcount > 2) {
            storedResult = compute(curnum);
            curnum = storedResult;
            //clear main text view for other inputs
            output.setText("");
            operator = operand;
            // for simple calculations with two numbers
        } else {
            operator = operand;
            curnum = Double.parseDouble(output.getText().toString());
            res.setText(res.getText().toString() + operand);
            output.setText("");
        }
    }

    /**
     * Takes operands and calculates final result
     *
     * @param curnum - first number given
     * @return - calculated result
     */
    public Double compute(double curnum) {
        double result = 0;
        secondnum = Double.parseDouble(output.getText().toString());

        if (operator == '+') {
            result = (curnum) + (secondnum);
        } else if (operator == '*') {
            result = (curnum) * (secondnum);
        } else if (operator == '/') {
            result = (curnum) / (secondnum);
        } else if (operator == '-') {
            result = (curnum) - (secondnum);
        }
        return result;
    }
}


