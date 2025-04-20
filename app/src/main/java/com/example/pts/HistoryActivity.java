package com.example.pts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ScanHistoryAdapter adapter = new ScanHistoryAdapter();
        recyclerView.setAdapter(adapter);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pts-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        List<ScanHistory> history = db.scanDao().getAll();
        adapter.setScans(history);
    }
}
