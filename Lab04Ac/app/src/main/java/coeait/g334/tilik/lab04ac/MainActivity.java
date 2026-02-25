package coeait.g334.tilik.lab04ac; // Тилик G334

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText textEdit;
    private CheckBox checkBoxMeow;
    private CheckBox checkBoxSq;

    private static final int REQUEST_CODE = 555;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEdit = findViewById(R.id.textedit);
        checkBoxMeow = findViewById(R.id.checkBoxmeow);
        checkBoxSq = findViewById(R.id.checkBoxsq);

        Button dialogButton = findViewById(R.id.dialwin);
        dialogButton.setOnClickListener(v -> showInputDialog());
    }

    public void btn_open_onclick(View v) {

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("abc", textEdit.getText().toString());
        intent.putExtra("meow", checkBoxMeow.isChecked());
        intent.putExtra("sq", checkBoxSq.isChecked());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void btn_close_onclick(View v) {

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setIcon(R.drawable.mario);
        builder.setTitle("Предупреждение");

        AlertDialog dialog = builder.create();
        dialog.show();

        Button stay = dialogView.findViewById(R.id.btn_stay);
        Button exit = dialogView.findViewById(R.id.btn_exit);

        stay.setOnClickListener(view -> dialog.dismiss());
        exit.setOnClickListener(view -> finish());
    }

    private void showInputDialog() {

        EditText input = new EditText(this);
        input.setHint("Введите текст");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ввод текста")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) ->
                        textEdit.setText(input.getText().toString()))
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE &&
                resultCode == RESULT_OK &&
                data != null) {

            String text = data.getStringExtra("abc");
            boolean meowState = data.getBooleanExtra("meow", false);
            boolean sqState = data.getBooleanExtra("sq", false);

            textEdit.setText(text);
            checkBoxMeow.setChecked(meowState);
            checkBoxSq.setChecked(sqState);

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }
}