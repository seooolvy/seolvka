package coeait.g334.tilik.lab02_converter;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Spinner spFrom, spTo;
    private EditText etFrom;
    private TextView tvTo;
    private RadioGroup rgType;
    private ArrayAdapter<Unit> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация компонентов
        spFrom = findViewById(R.id.spinner_from);
        spTo = findViewById(R.id.spinner_to);
        etFrom = findViewById(R.id.plain_from);
        tvTo = findViewById(R.id.inf_to);
        rgType = findViewById(R.id.rg_type);
        RadioButton rbLength = findViewById(R.id.rb_length);
        RadioButton rbTime = findViewById(R.id.rb_time);
        RadioButton rbMass = findViewById(R.id.rb_mass);

        // Фильтр для ввода чисел (только цифры и точка)
        etFrom.setInputType(android.text.InputType.TYPE_CLASS_NUMBER |
                android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
        InputFilter filter = new InputFilter() {
            final Pattern pattern = Pattern.compile("^\\d*\\.?\\d*$");
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                String newString = dest.subSequence(0, dstart).toString() +
                        source.subSequence(start, end) +
                        dest.subSequence(dend, dest.length()).toString();
                return pattern.matcher(newString).matches() ? null : "";
            }
        };
        etFrom.setFilters(new InputFilter[]{filter});

        // Создаём адаптер с пользовательским типом Unit
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Устанавливаем начальные единицы (длина)
        setLengthUnits();
        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);

        // Обработчики переключения типа единиц
        rbLength.setOnClickListener(v -> {
            setLengthUnits();
            spFrom.setSelection(0);
            spTo.setSelection(0);
        });
        rbTime.setOnClickListener(v -> {
            setTimeUnits();
            spFrom.setSelection(0);
            spTo.setSelection(0);
        });
        rbMass.setOnClickListener(v -> {
            setMassUnits();
            spFrom.setSelection(0);
            spTo.setSelection(0);
        });

        // По умолчанию выбран тип "длина"
        rgType.check(R.id.rb_length);
    }

    private void setLengthUnits() {
        adapter.clear();
        adapter.add(new Unit("mm", 0.001));
        adapter.add(new Unit("cm", 0.01));
        adapter.add(new Unit("m", 1.0));
        adapter.add(new Unit("km", 1000.0));
    }

    private void setTimeUnits() {
        adapter.clear();
        adapter.add(new Unit("s", 0.000277777));  // относительно часа
        adapter.add(new Unit("m", 0.0166667));
        adapter.add(new Unit("h", 1.0));
        adapter.add(new Unit("d", 24.0));
    }

    private void setMassUnits() {
        adapter.clear();
        adapter.add(new Unit("mg", 0.000001));    // относительно кг
        adapter.add(new Unit("g", 0.001));
        adapter.add(new Unit("kg", 1.0));
        adapter.add(new Unit("t", 1000.0));
    }

    // Обработчик кнопки Convert
    public void onConvert(View view) {
        String input = etFrom.getText().toString().trim();

        if (input.isEmpty() || input.equals(".")) {
            Toast.makeText(this, "Ошибка: введите число", Toast.LENGTH_SHORT).show();
            tvTo.setText(R.string.resultview);
            return;
        }

        double fromValue;
        try {
            fromValue = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ошибка: некорректное число", Toast.LENGTH_SHORT).show();
            tvTo.setText(R.string.resultview);
            return;
        }

        Unit fromUnit = (Unit) spFrom.getSelectedItem();
        Unit toUnit = (Unit) spTo.getSelectedItem();

        if (fromUnit == null || toUnit == null) return;

        // Конвертация через базовую единицу
        double baseValue = fromValue * fromUnit.coef;
        double result = baseValue / toUnit.coef;

        // Форматирование результата
        String resultText;
        if (result == (long) result) {
            resultText = String.valueOf((long) result);
        } else {
            resultText = String.format("%.6f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
        tvTo.setText(resultText + " " + toUnit.name);
    }

    // Вспомогательный класс для единиц измерения
    public static class Unit {
        public String name;
        public double coef;

        public Unit(String name, double coef) {
            this.name = name;
            this.coef = coef;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}