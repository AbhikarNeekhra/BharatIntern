package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solution;
    MaterialButton butC, bracOpen, bracClose;
    MaterialButton butdivide, butadd, butsub, butmult, butequals;
    MaterialButton but0, but1, but2, but3, but4, but5, but6 ,but7, but8, but9;
    MaterialButton butduo, butDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solution = findViewById(R.id.solution);

        assignId(butC,R.id.button_c);
        assignId(bracOpen,R.id.button_openBrac);
        assignId(bracClose,R.id.button_closeBrac);
        assignId(butdivide,R.id.button_divide);
        assignId(butmult,R.id.button_multiply);
        assignId(butadd,R.id.button_plus);
        assignId(butsub,R.id.button_minus);
        assignId(butequals,R.id.button_equal);
        assignId(but0,R.id.button_zero);
        assignId(but1,R.id.button_one);
        assignId(but2,R.id.button_two);
        assignId(but3,R.id.button_three);
        assignId(but4,R.id.button_four);
        assignId(but5,R.id.button_five);
        assignId(but6,R.id.button_six);
        assignId(but7,R.id.button_seven);
        assignId(but8,R.id.button_eight);
        assignId(but9,R.id.button_nine);
        assignId(butduo,R.id.button_duo);
        assignId(butDot,R.id.button_dot);


    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();
        if(buttonText.equals("AC")){
            solution.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText(resultTv.getText());
            return;
        }

        else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}