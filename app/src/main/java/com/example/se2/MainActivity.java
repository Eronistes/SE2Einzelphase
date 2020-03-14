package com.example.se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button sendbtn;
    Button berechnebtn;
    TextView outputtxt;
    EditText inputtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputtxt =  findViewById(R.id.inputtxt);
        outputtxt = findViewById(R.id.outputtxt);

        sendbtn =  findViewById(R.id.abschickenbtn);
        sendbtn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           try {
                                               outputtxt.setText(tcpconnect(inputtxt.getText().toString()));
                                           } catch (InterruptedException e) {
                                               e.printStackTrace();
                                           }

                                       }
                                   });
        berechnebtn = findViewById(R.id.berechnebtn);
        berechnebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    public String tcpconnect(String input) throws InterruptedException {
        TCP server = new TCP();
        server.setInput(input);
        Thread x = new Thread(server);
        x.start();

        x.join();
        return server.getOutput();


    }
}
