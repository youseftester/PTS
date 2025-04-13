package com.example.pts;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scan_history")
public class ScanHistory {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String target;
    public String results;
    public String error;
    public long timestamp;

    public ScanHistory(String target, String results, String error) {
        this.target = target;
        this.results = results;
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }
}