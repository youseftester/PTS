package com.example.pts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ScanHistoryAdapter extends RecyclerView.Adapter<ScanHistoryAdapter.ScanHistoryViewHolder> {
    private ArrayList<PortScanResult> scans = new ArrayList<>();

    public void setScans(ArrayList<PortScanResult> scans) {
        this.scans = scans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScanHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_scan_history, parent, false);
        return new ScanHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanHistoryViewHolder holder, int position) {
        holder.bind(scans.get(position));
    }

    @Override
    public int getItemCount() {
        return scans.size();
    }

    static class ScanHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView targetText, resultsText, errorText, dateText;

        public ScanHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            targetText = itemView.findViewById(R.id.target_text);
            resultsText = itemView.findViewById(R.id.results_text);
            errorText = itemView.findViewById(R.id.error_text);
            dateText = itemView.findViewById(R.id.date_text);
        }

        public void bind(PortScanResult scan) {
            targetText.setText(scan.target);
            resultsText.setText(scan.output);
            errorText.setText(scan.error != null ? scan.error : "No errors");

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
            try {
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date date = parser.parse(scan.timestamp);
                SimpleDateFormat displayFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
                dateText.setText(displayFormat.format(date));
            } catch (Exception e) {
                dateText.setText("Invalid date");
            }

        }
    }
}