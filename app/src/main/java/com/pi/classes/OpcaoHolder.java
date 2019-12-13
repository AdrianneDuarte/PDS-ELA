package com.pi.classes;

import android.widget.TextView;

public class OpcaoHolder {
    private TextView textView;

    public OpcaoHolder(TextView textView){
        this.textView = textView;

    }
    public void setText(String text){
        this.textView.setText(text);
    }
}
