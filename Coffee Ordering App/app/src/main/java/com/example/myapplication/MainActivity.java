package com.example.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

;import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText namefield= (EditText) findViewById(R.id.name_field);
        String name= namefield.getText().toString();
        CheckBox haschecked= (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedcreamchecked= haschecked.isChecked();
        CheckBox haschecked1= (CheckBox) findViewById(R.id.choclate);
        boolean choclatechecked= haschecked1.isChecked();
        String summary = CreateOrderSummary(quantity,whippedcreamchecked,choclatechecked,name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order By "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void increment(View view)
    {

        quantity=quantity+1;
        if(quantity>100) {
            Toast.makeText(this,"You cannot have more than 100 coffee",Toast.LENGTH_SHORT).show();
            quantity = 100;
        }
        displayQuantity(quantity);
    }
    public void decrement(View view)
    {

        quantity=quantity-1;
        if(quantity<1) {
            Toast.makeText(this,"You cannot have less than 1 coffee",Toast.LENGTH_SHORT).show();
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    private String CreateOrderSummary(int number,boolean whippedcream,boolean choclate,String name)
    {    int total=number*5;
    if(whippedcream && choclate)
    {
        total= number+(3*number);
        total*=5;
    }
       else if(whippedcream && !choclate)
        {
             total = (number+number);
            total *=5;
        }
        else if(choclate && !whippedcream)
        {
            total=(number+2*number);
            total*=5;
        }

        String s= "Name : "+ name +"\n Add whipped cream? "+ whippedcream + "\n Add choclate? "+choclate+ "\n Quantity : " + number + "\n Total : $"+ total + "\n Thank You!";
        return s;
    }
}