package com.example.flowers;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ListItemActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
    }
}
