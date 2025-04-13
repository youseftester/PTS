package com.example.pts;

import java.io.Serializable;

public class PortScanResult implements Serializable {
    public String target;
    public String output;
    public String error;
    public String timestamp;

    public PortScanResult(String target, String output, String error, String timestamp) {
        this.target = target;
        this.output = output;
        this.error = error;
        this.timestamp = timestamp;
    }

}
