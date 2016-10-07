package com.rynfar.heightcalculator;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_cal;
    private RadioButton rb_male;
    private RadioButton rb_female;
    private EditText et_weight;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cal = (Button) findViewById(R.id.btn_cal);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        et_weight = (EditText) findViewById(R.id.et_weight);
        tv_result = (TextView) findViewById(R.id.tv_result);

        btn_cal.setOnClickListener(new MyOnclickListener());

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
//            System.out.println("ENTER");
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_NUMPAD_ENTER) {
            System.out.println("ENTER");
        }
        return super.dispatchKeyEvent(event);
    }

    private double calculate(double weight, String sex) {
        double res_height;
        if (sex == "男") {
            res_height = 170 - (62 - weight) / 0.6;
        } else {
            res_height = 158 - (52 - weight) / 0.5;
        }
        return res_height;
    }

    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!et_weight.getText().toString().trim().equals("")) {
                Double weight = Double.parseDouble(et_weight.getText().toString());
                StringBuffer sb = new StringBuffer();
                sb.append("---------评估结果---------\n");
                if (rb_male.isChecked() || rb_female.isChecked()) {
                    if (rb_male.isChecked()) {
                        sb.append("男性标准身高：");
                        double result = calculate(weight, "男");
                        sb.append((int) result + "(厘米)");
                    } else {
                        sb.append("女性标准身高：");
                        double result = calculate(weight, "女");
                        sb.append((int) result + "(厘米)");
                    }
                    tv_result.setText(sb.toString());
                } else {
                    showMessage("请选择性别！");
                }
            } else {
                showMessage("请输入体重！");
            }
        }
    }

    private void showMessage(String message) {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("提示");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        alert.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "退出");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
