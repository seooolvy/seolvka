package coeait.g334.tilik.lab03sw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCalc;
    private CheckBox[] chk = new CheckBox[9];
    private EditText[] etQuantity = new EditText[9];
    private EditText[] etPrice = new EditText[9];
    private RadioButton rbToast, rbDialog;
    private boolean isValid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btn_calc);

        chk[0] = findViewById(R.id.cb_apple);
        chk[1] = findViewById(R.id.cb_strawberry);
        chk[2] = findViewById(R.id.cb_blueberry);
        chk[3] = findViewById(R.id.cb_raspberry);
        chk[4] = findViewById(R.id.cb_cherry);
        chk[5] = findViewById(R.id.cb_grapes);
        chk[6] = findViewById(R.id.cb_lemon);
        chk[7] = findViewById(R.id.cb_orange);
        chk[8] = findViewById(R.id.cb_pear);

        etQuantity[0] = findViewById(R.id.et_apple);
        etQuantity[1] = findViewById(R.id.et_strawberry);
        etQuantity[2] = findViewById(R.id.et_blueberry);
        etQuantity[3] = findViewById(R.id.et_raspberry);
        etQuantity[4] = findViewById(R.id.et_cherry);
        etQuantity[5] = findViewById(R.id.et_grapes);
        etQuantity[6] = findViewById(R.id.et_lemon);
        etQuantity[7] = findViewById(R.id.et_orange);
        etQuantity[8] = findViewById(R.id.et_pear);

        etPrice[0] = findViewById(R.id.etcost_apple);
        etPrice[1] = findViewById(R.id.etcost_strawberry);
        etPrice[2] = findViewById(R.id.etcost_blueberry);
        etPrice[3] = findViewById(R.id.etcost_raspberry);
        etPrice[4] = findViewById(R.id.etcost_cherry);
        etPrice[5] = findViewById(R.id.etcost_grapes);
        etPrice[6] = findViewById(R.id.etcost_lemon);
        etPrice[7] = findViewById(R.id.etcost_orange);
        etPrice[8] = findViewById(R.id.etcost_pear);

        rbToast = findViewById(R.id.rb_toast);
        rbDialog = findViewById(R.id.rb_dialog);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnCalcClick(v);
            }
        });
    }

    private boolean validateInputs() {
        isValid = true;
        for (int i = 0; i < 9; i++) {
            if (chk[i].isChecked()) {
                // Проверяем количество
                String qtyStr = etQuantity[i].getText().toString().trim();
                if (qtyStr.isEmpty()) {
                    etQuantity[i].setError("Введите количество");
                    isValid = false;
                } else {
                    try {
                        double qty = Double.parseDouble(qtyStr);
                        if (qty <= 0) {
                            etQuantity[i].setError("Количество должно быть > 0");
                            isValid = false;
                        }
                    } catch (NumberFormatException e) {
                        etQuantity[i].setError("Некорректное число");
                        isValid = false;
                    }
                }

                // Проверяем цену
                String priceStr = etPrice[i].getText().toString().trim();
                if (priceStr.isEmpty()) {
                    etPrice[i].setError("Введите цену");
                    isValid = false;
                } else {
                    try {
                        double price = Double.parseDouble(priceStr);
                        if (price <= 0) {
                            etPrice[i].setError("Цена должна быть > 0");
                            isValid = false;
                        }
                    } catch (NumberFormatException e) {
                        etPrice[i].setError("Некорректное число");
                        isValid = false;
                    }
                }
            }
        }
        return isValid;
    }

    public void onBtnCalcClick(View v) {
        if (!validateInputs()) return;

        double total = 0.0;
        StringBuilder details = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            if (chk[i].isChecked()) {
                double qty = Double.parseDouble(etQuantity[i].getText().toString());
                double price = Double.parseDouble(etPrice[i].getText().toString());
                double cost = qty * price;
                total += cost;

                details.append(chk[i].getText())
                        .append(": ")
                        .append(qty)
                        .append(" × ")
                        .append(price)
                        .append(" = ")
                        .append(String.format("%.2f", cost))
                        .append("\n");
            }
        }

        String message;
        if (details.length() > 0) {
            message = details.toString() + "—————————————\nИТОГО: " + String.format("%.2f", total);
        } else {
            message = "Ничего не выбрано";
        }

        if (rbToast.isChecked()) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.create();
            dialog.setIcon(R.drawable.ic_dialog);
            dialog.setTitle("Результат");
            dialog.setMessage(message);
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (d, w) -> d.dismiss());
            dialog.show();
        }
    }
}