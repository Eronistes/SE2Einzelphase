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

                int original;
                int mtrklnr;
                int number1;
                int number2;
                int result = 1;

                original = Integer.parseInt(inputtxt.getText().toString());
                int i = inputtxt.getText().length();
                int y = inputtxt.getText().length()-1;
                while(original > 0){

                    number1 = original%10;
                    mtrklnr = original/10;
                    while(mtrklnr > 0){

                        number2 = mtrklnr%10;
                        result = findGCD(number1, number2);
                        System.out.println("Resultat ist " + result);
                        System.out.println("Nummern sind " + number1 + " und " + number2);
                        if(result > 1) break;
                        mtrklnr = mtrklnr/10;
                        y--;
                        }
                    if(result > 1) {
                        String outputString = "Zahl auf Index: [" + i + "] und [" + y + "] haben den gemeinsamen Teiler [" + result + "]";
                        outputtxt.setText(outputString);
                        break;
                    }
                    original = original/10;
                    i--;
                }
                String nothing = "Es gibt keine 2 Zahlen die einen gemeinsamen Teile > 1 haben";
                outputtxt.setText(nothing);


            }
        });


    }

    private static int findGCD(int number1, int number2) {
        if(number2 == 0) return number1;
        return findGCD(number2, number1%number2); }




    public String tcpconnect(String input) throws InterruptedException {
        TCP server = new TCP();
        server.setInput(input);
        Thread x = new Thread(server);
        x.start();

        x.join();
        return server.getOutput();


    }
}
