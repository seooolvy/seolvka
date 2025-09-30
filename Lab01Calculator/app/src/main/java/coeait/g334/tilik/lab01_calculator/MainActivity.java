package coeait.g334.tilik.lab01_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText ta;
    EditText tb;
    TextView lr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ta = findViewById(R.id.txt_a);
        tb = findViewById(R.id.txt_b);
        lr = findViewById(R.id.txt_result);
    }

    public void my_add_click(View v) {
        String tat = ta.getText().toString();
        String tbt = tb.getText().toString();

        float a = Float.parseFloat(tat);
        float b = Float.parseFloat(tbt);

        float c = a + b;

        String tct = String.valueOf(c);
        lr.setText(tct);
    }

    public void my_sub_click(View v) {
        String tat = ta.getText().toString();
        String tbt = tb.getText().toString();

        float a = Float.parseFloat(tat);
        float b = Float.parseFloat(tbt);

        float c = a - b;

        String tct = String.valueOf(c);

        lr.setText(tct);
    }

    public void my_mul_click(View v) {
        String tat = ta.getText().toString();
        String tbt = tb.getText().toString();

        float a = Float.parseFloat(tat);
        float b = Float.parseFloat(tbt);

        float c = a * b;

        String tct = String.valueOf(c);

        lr.setText(tct);
    }

    public void my_div_click(View v) {
        String tat = ta.getText().toString();
        String tbt = tb.getText().toString();

        float a = Float.parseFloat(tat);
        float b = Float.parseFloat(tbt);


        //деление на 0
        float c = a / b;
        if (b == 0) {
            lr.setText("Ошибка! Делить на 0 нельзя!");
            return;
        }

        String tct = String.valueOf(c);

        lr.setText(tct);

    }

    public void my_sin_click(View v) {
        String tat = ta.getText().toString();

        float a = Float.parseFloat(tat);
        double result = Math.sin(Math.toRadians(a)); // перевод градусов в радианы
        result = Math.round(result * 10000.0) / 10000.0; // округление до 4 знаков
        String tct = String.valueOf(result);
        lr.setText(tct);
    }

    public void my_cos_click(View v) {
        String tat = ta.getText().toString();

        float a = Float.parseFloat(tat);
        double result = Math.cos(Math.toRadians(a));
        result = Math.round(result * 10000.0) / 10000.0; // округление до 4 знаков
        String tct = String.valueOf(result);
        lr.setText(tct);
    }

    public void my_tan_click(View v) {
        String tat = ta.getText().toString();

        float a = Float.parseFloat(tat);
        double result = Math.tan(Math.toRadians(a));
        result = Math.round(result * 10000.0) / 10000.0; // округление до 4 знаков
        String tct = String.valueOf(result);
        lr.setText(tct);
    }

    public void my_sqrt_click(View v) {
        String tat = ta.getText().toString();
        double a = Double.parseDouble(tat);

        if (a < 0) { // защита от отрицательного числа
            lr.setText("Ошибка! 0 и отрицательное число не может быть корнем!");
            return;
        }

        double result = Math.sqrt(a);
        result = Math.round(result * 10000.0) / 10000.0; // округление до 4 знаков
        lr.setText(String.valueOf(result));
    }

    // возведение в степень: a^b, где a из ta, b из tb
    public void my_pow_click(View v) {
        String sa = ta.getText().toString();
        String sb = tb.getText().toString();

        double a = Double.parseDouble(sa);
        double b = Double.parseDouble(sb);

        double result = Math.pow(a, b);
        result = Math.round(result * 10000.0) / 10000.0;
        lr.setText(String.valueOf(result));
    }

}
