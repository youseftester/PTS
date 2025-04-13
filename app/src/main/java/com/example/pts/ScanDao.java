package com.example.pts;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;


@Dao
public interface ScanDao {
    @Insert
    void insert(ScanHistory scan);

    @Query("SELECT * FROM scan_history ORDER BY timestamp DESC")
    LiveData<List<ScanHistory>> getAllScans();

    @Query("DELETE FROM scan_history")
    void clearAll();
}