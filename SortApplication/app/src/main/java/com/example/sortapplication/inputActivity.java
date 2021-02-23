package com.example.sortapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Button btnxacnhan = (Button) findViewById(R.id.btnXacnhan);
        EditText edtinput = (EditText) findViewById(R.id.edtInput);
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtinput.getText().toString().equals(""))
                {
                    Toast.makeText(inputActivity.this, "Chưa nhập", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                   String strinput = edtinput.getText().toString();
                    Intent data = new Intent();
                    data.putExtra("strResult",strinput);
                    //Toast.makeText(inputActivity.this, strinput, Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK,data);
                   finish();
                }
            }
        });
    }
}