package com.example.pts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ScanResult";
    private final List<PortScanResult> historyList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText urlInput = findViewById(R.id.url_input);
        Button scanButton = findViewById(R.id.scan_button);
        Button historyButton = findViewById(R.id.history_button);

        scanButton.setOnClickListener(v -> {
            String target = urlInput.getText() != null ? urlInput.getText().toString().trim() : "";
            if (!target.isEmpty()) {
                performScan(target);
            } else {
                Toast.makeText(this, "Please enter a target", Toast.LENGTH_SHORT).show();
            }
        });
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            intent.putExtra("history", new ArrayList<>(historyList));
            startActivity(intent);
        });

    }

    private void performScan(String target) {
        TextView scanOutput = findViewById(R.id.scan_output);
        scanOutput.setText("Scanning...");

        NmapApiService api = ApiClient.getService();
        api.scan(target).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String output = "";
                String error = "";
                if (response.isSuccessful() && response.body() != null) {
                    output = response.body();
                    scanOutput.setText(output);
                    Toast.makeText(MainActivity.this, "Scan successful.", Toast.LENGTH_SHORT).show();
                } else {
                    error = "Scan failed. Code: " + response.code();
                    scanOutput.setText(error);
                    Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                }
                saveToHistory(target, output, error);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                String error = "Network error: " + t.getMessage();
                scanOutput.setText(error);
                saveToHistory(target, "", error);
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveToHistory(String target, String output, String error) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        PortScanResult result = new PortScanResult(target, output, error, timestamp);
        historyList.add(result);
        Log.d("ScanHistory", "Saved: " + target + " at " + timestamp);
    }


}
