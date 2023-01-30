package com.example.flowers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progBar;
    private Button btn;
    RecyclerView recycView;

    List<Flower> flowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progBar = (ProgressBar) findViewById(R.id.progressBar);
        btn = (Button)findViewById(R.id.button);

        flowers = new ArrayList<>();

        recycView = (RecyclerView) findViewById(R.id.recView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycView.setLayoutManager(layoutManager);

        FlowerAdapter adapter = new FlowerAdapter(flowers);
        recycView.setAdapter(adapter);

        progBar.setVisibility(View.VISIBLE);

        FlowersAPI flowersAPI = FlowersAPI.retrofit.create(FlowersAPI.class);
        final Call<List<Flower>> call = flowersAPI.getData();
        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()) {
                    progBar.setVisibility(View.INVISIBLE);
                    flowers.addAll(response.body());
                    recycView.getAdapter().notifyDataSetChanged();
                } else {

                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                        progBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Что-то пошло не так",
                        Toast.LENGTH_SHORT).show();
                progBar.setVisibility(View.INVISIBLE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}