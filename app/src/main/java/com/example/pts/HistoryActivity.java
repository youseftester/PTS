package com.example.pts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ScanHistoryAdapter adapter = new ScanHistoryAdapter();
        recyclerView.setAdapter(adapter);

        ArrayList<PortScanResult> history = (ArrayList<PortScanResult>) getIntent().getSerializableExtra("history");
        adapter.setScans(history);
    }
}
