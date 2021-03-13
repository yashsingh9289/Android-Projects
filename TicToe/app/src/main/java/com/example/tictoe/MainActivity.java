package com.example.tictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,rest;
TextView headerText;
int PLAYER_0=0;
int PLAYER_1=1;
int activePlayer=PLAYER_0;
int[] filltic={-1,-1,-1,-1,-1,-1,-1,-1,-1};
boolean isgameactive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        rest=findViewById(R.id.restart);

headerText=findViewById(R.id.headerfile);

        btn0.setOnClickListener( this);
        btn1.setOnClickListener( this);
        btn2.setOnClickListener( this);
        btn3.setOnClickListener( this);
        btn4.setOnClickListener( this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener( this);
        btn7.setOnClickListener( this);
        btn8.setOnClickListener( this);


        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activePlayer=PLAYER_0;
                headerText.setText("0 Turn");
               filltic=new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
               btn0.setText("");
                btn1.setText("");
                btn2.setText("");
                btn3.setText("");
                btn4.setText("");
                btn5.setText("");
                btn6.setText("");
                btn7.setText("");
                btn8.setText("");
                isgameactive=true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!isgameactive) return;
Button clickedbtn=findViewById(v.getId());
int clickedtag=Integer.parseInt(v.getTag().toString());
if(filltic[clickedtag]!=-1)
{
    return;
}
filltic[clickedtag]=activePlayer;
if(activePlayer==PLAYER_0)
{
    clickedbtn.setText("0");
    activePlayer=PLAYER_1;

    headerText.setText("X Turn");
}
else {
    clickedbtn.setText("X");
    activePlayer=PLAYER_0;
    headerText.setText("0 Turn");
}checkforwin();
    }



    public void checkforwin()
    {
        int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int i=0;i<8;i++)
        {
            int val0=winningpos[i][0];
            int val1=winningpos[i][1];
            int val2=winningpos[i][2];
            if(filltic[val0]==filltic[val1]&&filltic[val1]==filltic[val2])
            {
               if(filltic[val0]!=-1)
               {  isgameactive=false;
                   if(filltic[val0]==PLAYER_0)
                   {
                       headerText.setText("0 is Winner");
                   }
                   else
                   {
                       headerText.setText("X is Winner");
                   }
               }
            }

        }
    }



}
