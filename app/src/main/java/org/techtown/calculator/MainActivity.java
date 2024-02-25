package org.techtown.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;


    int resultNumber = 0;
    char operator = '+';

    int operatorStack = 0;

    final String CLEAR_INPUT_TEXT = "0";


    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.result_text);
    }

    public void buttonClick(View view) {
        Button getButton = findViewById(view.getId());

        Log.e("buttonClick", "Button : " + getButton.getText().toString() + "버튼이 입력 되었습니다");
        Log.e("buttonClick", "resultNumber : " + resultNumber);

        //operatorStack 확인
        if (!(view.getId() == R.id.addition_button ||
                view.getId() == R.id.subtraction_button ||
                view.getId() == R.id.multiplication_button ||
                view.getId() == R.id.division_button ||
                view.getId() == R.id.equal_button ||
                view.getId() == R.id.back_space_button||
                view.getId() == R.id.decimal_point_button)){
            operatorStack = 0;
        }


        if (view.getId() == R.id.all_clear_button) {
            isFirstInput = true;
            resultNumber = 0;
            operator = '+';
            resultText.setText(CLEAR_INPUT_TEXT);
            resultText.setTextColor(0xFF858585);
        }

        if (view.getId() == R.id.addition_button ||
                view.getId() == R.id.subtraction_button ||
                view.getId() == R.id.multiplication_button ||
                view.getId() == R.id.division_button) {
            if (operatorStack == 0) {
                int lastNum = Integer.parseInt(resultText.getText().toString());
                switch (operator) {
                    case '+':
                        resultNumber += lastNum;
                        break;
                    case '-':
                        resultNumber -= lastNum;
                        break;
                    case '*':
                        resultNumber *= lastNum;
                        break;
                    case '/':
                        resultNumber /= lastNum;
                        break;
                }
                operator = getButton.getText().toString().charAt(0);
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
                Log.e("buttonClick", "resultNumber : " + resultNumber);
                operatorStack++;
            } else {
                operator = getButton.getText().toString().charAt(0);
                isFirstInput = true;
            }
        }

        if (view.getId() == R.id.equal_button) {
            if (operatorStack == 0) {
                int lastNum = Integer.parseInt(resultText.getText().toString());
                switch (operator) {
                    case '+':
                        resultNumber += lastNum;
                        break;
                    case '-':
                        resultNumber -= lastNum;
                        break;
                    case '*':
                        resultNumber *= lastNum;
                        break;
                    case '/':
                        resultNumber /= lastNum;
                        break;
                }
                resultText.setText(String.valueOf(resultNumber));
                isFirstInput = true;
                Log.e("buttonClick", "resultNumber : " + resultNumber);
                operatorStack++;
            }

        }

        if (view.getId() == R.id.clear_entry_button) {
            resultText.setText(CLEAR_INPUT_TEXT);
            isFirstInput = true;
            Log.e("buttonClick", "resultNumber : " + resultNumber);
        }

        if (view.getId() == R.id.back_space_button) {
            if (operatorStack == 0) {
                if (resultText.getText().toString().length() > 1) {
                    String getResultText = resultText.getText().toString();
                    String subString = getResultText.substring(0, getResultText.length() - 1);
                    resultText.setText(subString);

                } else {
                    resultText.setText(CLEAR_INPUT_TEXT);
                    isFirstInput = true;
                }
            }

            Log.e("buttonClick", "resultNumber : " + resultNumber);
        }

        if (view.getId() == R.id.decimal_point_button) {
            Toast.makeText(getApplicationContext(), "작업 중", Toast.LENGTH_SHORT).show();
        }


        if (view.getId() == R.id.num0_button ||
                view.getId() == R.id.num1_button ||
                view.getId() == R.id.num2_button ||
                view.getId() == R.id.num3_button ||
                view.getId() == R.id.num4_button ||
                view.getId() == R.id.num5_button ||
                view.getId() == R.id.num6_button ||
                view.getId() == R.id.num7_button ||
                view.getId() == R.id.num8_button ||
                view.getId() == R.id.num9_button) {
            if (isFirstInput == true) {
                resultText.setTextColor(0xFFFFFFFF);
                resultText.setText(getButton.getText().toString());
                isFirstInput = false;
            } else {
                resultText.append(getButton.getText().toString());
            }
        } else {
            //Toast.makeText(getApplicationContext(),getButton.getText().toString() + "버튼이 입력 되었습니다",Toast.LENGTH_SHORT).show();
            Log.e("buttonClick", "default : " + getButton.getText().toString() + "버튼이 입력 되었습니다");
        }


    }
}