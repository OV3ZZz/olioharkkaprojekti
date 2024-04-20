package com.example.olioharkkaprojekti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLocation;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLocation = findViewById(R.id.txtEditlocation);

        context = MainActivity.this;

    }

    public void openListDataActivity(View view) {
        String location = editTextLocation.getText().toString();
        Intent intent = new Intent(this, ListDataInRecycleViewActivity.class);
        intent.putExtra("LOCATION", location);
        startActivity(intent);

    }

}
