package com.example.pts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScanHistoryAdapter extends RecyclerView.Adapter<ScanHistoryAdapter.ScanHistoryViewHolder> {

    private List<ScanHistory> scans;

    public void setScans(List<ScanHistory> scans) {
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
        return (scans != null) ? scans.size() : 0;
    }

    static class ScanHistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView targetText, resultsText, errorText, dateText;

        public ScanHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            targetText = itemView.findViewById(R.id.target_text);
            resultsText = itemView.findViewById(R.id.results_text);
            errorText = itemView.findViewById(R.id.error_text);
            dateText = itemView.findViewById(R.id.date_text);
        }

        public void bind(ScanHistory scan) {
            targetText.setText(scan.target);
            resultsText.setText(scan.results);
            errorText.setText(scan.error != null ? scan.error : "No error");

            try {
                Date date = new Date(scan.timestamp);
                String formatted = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(date);
                dateText.setText(formatted);
            } catch (Exception e) {
                dateText.setText("Invalid date");
            }
        }
    }
}
