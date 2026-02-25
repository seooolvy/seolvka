package coeait.g334.tilik.lab04ac; // Тилик G334

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText textEdit;
    private Switch switchMeow;
    private Switch switchSq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textEdit = findViewById(R.id.textedit);
        switchMeow = findViewById(R.id.switchmeow);
        switchSq = findViewById(R.id.switchsq);

        Intent intent = getIntent();

        if (intent != null) {
            textEdit.setText(intent.getStringExtra("abc"));
            switchMeow.setChecked(intent.getBooleanExtra("meow", false));
            switchSq.setChecked(intent.getBooleanExtra("sq", false));
        }
    }

    public void sec_ok_click(View v) {

        Intent resultIntent = new Intent();
        resultIntent.putExtra("abc", textEdit.getText().toString());
        resultIntent.putExtra("meow", switchMeow.isChecked());
        resultIntent.putExtra("sq", switchSq.isChecked());

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void btn_close_onclick(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}