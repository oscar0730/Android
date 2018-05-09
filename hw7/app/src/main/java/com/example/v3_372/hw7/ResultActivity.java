package com.example.v3_372.hw7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    private Button btnReturn;
    private EditText edtCountSet, edtCountP_Win, edtCountC_Win, edtCountDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(btnOnReturn);
        edtCountSet = (EditText) findViewById(R.id.edtCountSet);
        edtCountP_Win = (EditText) findViewById(R.id.edtCountPlayerWin);
        edtCountC_Win = (EditText) findViewById(R.id.edtCountComWin);
        edtCountDraw = (EditText) findViewById(R.id.edtCountDraw);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            edtCountSet.setText(String.valueOf(bundle.getInt("miCountSet")));
            edtCountP_Win.setText(String.valueOf(bundle.getInt("miCountPlayerWin")));
            edtCountC_Win.setText(String.valueOf(bundle.getInt("miCountComWin")));
            edtCountDraw.setText(String.valueOf(bundle.getInt("miCountDraw")));
        } else {
            Toast.makeText(this, "資料不能為空！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private View.OnClickListener btnOnReturn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}