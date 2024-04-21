package com.example.olioharkkaprojekti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements HistoryAdapter.OnItemClickListener{

    private EditText editTextLocation;

    private RecyclerView recyclerView;

    private SearchHistory searchHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchHistory = new SearchHistory(this);

        editTextLocation = findViewById(R.id.txtEditlocation);

        recyclerView = findViewById(R.id.searchHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HistoryAdapter adapter = new HistoryAdapter(searchHistory.getHistory(), this);
        recyclerView.setAdapter(adapter);


    }

    public void openListDataActivity(View view) {
        String location = editTextLocation.getText().toString();
        searchHistory.addSearch(this, location);
        HistoryAdapter adapter = new HistoryAdapter(searchHistory.getHistory(), this);
        recyclerView.setAdapter(adapter);
        Intent intent = new Intent(this, ListDataInRecycleViewActivity.class);
        intent.putExtra("LOCATION", location);
        startActivity(intent);

    }
    @Override
    public void onItemClick(String item) {
        Intent intent = new Intent(this, ListDataInRecycleViewActivity.class);
        intent.putExtra("LOCATION", item);
        startActivity(intent);
    }

    public void clearHistory(View view) {
        searchHistory.clearHistory(this);
        HistoryAdapter adapter = new HistoryAdapter(searchHistory.getHistory(), this);
        recyclerView.setAdapter(adapter);
    }

}
