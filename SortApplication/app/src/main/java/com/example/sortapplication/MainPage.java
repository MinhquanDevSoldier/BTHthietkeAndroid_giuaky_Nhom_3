package com.example.sortapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {
    Spinner spinnergt;
    TextView txt;
    Button btnNhap,btnSapxep,btnThoatud;
    String strResult = "";
    String arrgiaithuat[] = {"Insertion","Selection","Shell Sort","Radix Sort","Quick Sort","Merge Sort","Bubble Sort","Interchange"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        anhxa();
        btnThoatud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.custom_spinner,arrgiaithuat);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown2);
        spinnergt.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),inputActivity.class);
                startActivityForResult(intent,1113);
            }
        });
        btnSapxep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(spinnergt.getSelectedItem().toString().equals("Radix Sort")||spinnergt.getSelectedItem().toString().equals("Interchange"))
//                {
//                    Toast.makeText(getApplication(),"Chưa hoàn thiện",Toast.LENGTH_SHORT).show();
//                }
//                else
                if (strResult.equals(""))
                {
                    Toast.makeText(getApplication(),"Chưa nhập chuỗi",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String selectedgt = spinnergt.getSelectedItem().toString();
                    Intent intent = new Intent(getApplication(),ResultActivity.class);
                    intent.putExtra("gtselected",selectedgt);
                    intent.putExtra("strResult",strResult);
                    startActivity(intent);
                }
            }
        });

    }
    private void anhxa()
    {
        spinnergt = (Spinner) findViewById(R.id.spngiaithuat);
        btnNhap = (Button) findViewById(R.id.btnNhap);
        btnSapxep = (Button) findViewById(R.id.btnSapxep);
        btnThoatud = (Button) findViewById(R.id.btnThoatUD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1113)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                strResult = data.getStringExtra("strResult");
                //txt.setText(strResult);
            }
        }
    }
}