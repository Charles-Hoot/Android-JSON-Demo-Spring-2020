package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fetchBTN = findViewById(R.id.fetchBTN);
        fetchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread (new Runnable(){
                    @Override
                    public void run() {

                        Log.d("Button", "Fetch clicked");
                        String address = "https://qrng.anu.edu.au/API/jsonI.php?length=10&type=uint8";
                        fetchJSON(address);
                    }}).start();
            }
        });
    }

    public String fetchJSON(String url){
        StringBuilder strBuilder = new StringBuilder();
        try {
            URL location = new URL(url);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(location.openStream()));
            String line;
            while((line = br.readLine()) != null)
                strBuilder.append(line);
        } catch (IOException e) { e.printStackTrace();
        }
        Log.d("Click", "got " + strBuilder.toString());

        return strBuilder.toString();
    }
}
