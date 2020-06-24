package com.example.krestiki_noliki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList <Button> listButton = new ArrayList<>();

    int count=0;

    Button exit;
    Button reset;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point(); display.getSize(size);


        int width = size.x;
        int height = size.y-300;
        Button button;

        gridLayout = new GridLayout(this);
        gridLayout.setMinimumHeight(height);
        gridLayout.setMinimumWidth(width);
        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(3);
       // gridLayout.set
        //gridLayout.setBackgroundColor(Color.argb(255,0,0,0));
        for (int i=0; i<9; i++){
            listButton.add(new Button(this));
            listButton.get(i).setWidth(width/3);
            listButton.get(i).setHeight(height/3-10);
            listButton.get(i).setTextSize(47);
            //listButton.get(i).setBackgroundColor(Color.argb(180,255,0,0));
           // listButton.get(i).setShadowLayer(4, 2, 3, 2);
            gridLayout.addView(listButton.get(i));

            final int j =i;
            listButton.get(i).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(!listButton.get(j).getText().equals("X") && !listButton.get(j).getText().equals("O")) {
                        listButton.get(j).setText("X");
                        count++;
                        if(checkerWinn("X")==1){
                            toastView("X");
                        }
                        else {
                            if(count==9)
                            toastView("XO");
                        }

                        //listButton.get(j).setEnabled(false);
                        androidPlay();
                        count++;
                        if(checkerWinn("O")==1){
                            toastView("O");
                        }
                        else {
                            if(count==9)
                                toastView("XO");
                        }

                    }
                }
            });
        }
        exit = new Button(this);
        reset = new Button(this);
        reset.setText("заново");
        exit.setText("выход");
        reset.setX(10);
        exit.setX(350);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReset();
            }
        });

        gridLayout.addView(reset);
        gridLayout.addView(exit);




        setContentView(gridLayout);
    }
    public void androidPlay(){
        Integer[] arrnumX = new Integer[9];
        Integer[] arrnumO= new Integer[9];
        //Integer[] arrnumTemp= new Integer[9];

        int i=0;
        for (Button item: listButton) {
            switch (item.getText().toString()){
                case "X":{ arrnumX[i]=1; arrnumO[i]=2; } break;
                case "O":{ arrnumX[i]=2; arrnumO[i]=1; } break;
                case "":{ arrnumX[i]=0; arrnumO[i]=0; } break;
            }
            i++;
        }
        int tr=0;
        for (int j=0; j<9; j++){
            Integer[] arrnumTemp= arrnumO;
            if(arrnumTemp[j]!=1 && arrnumTemp[j]!=2){
                arrnumTemp[j]=1;
                if(checkerWinn(arrnumO,1)==1){
                    listButton.get(j).setText("O"); //listButton.get(j).setEnabled(false);
                     tr=1;  break;
                }
                else{arrnumTemp[j]=0;}

            }
            Integer[] arrnumTemp2 = arrnumX;
            if(arrnumTemp2[j]!=1 && arrnumTemp2[j]!=2){
                arrnumTemp2[j]=1;
                if(checkerWinn(arrnumX,1)==1){
                    listButton.get(j).setText("O");  //listButton.get(j).setEnabled(false);
                    tr=1; break;
                }
                else{arrnumTemp2[j]=0;}

            }


        }
        if(tr==0){
        for (Button btn: listButton) {
            if(!btn.getText().equals("X") && !btn.getText().equals("O"))
            {btn.setText("O");
            //btn.setEnabled(false);
            tr=0; break;}
        }
        }


    }
    public int checkerWinn(Integer[] arrsost, int xo){
        if(arrsost[0] == xo && arrsost[1] == xo && arrsost[2] == xo) return 1;
        if(arrsost[3] == xo && arrsost[4] == xo && arrsost[5] == xo) return 1;
        if(arrsost[6] == xo && arrsost[7] == xo && arrsost[8] == xo) return 1;
        if(arrsost[0] == xo && arrsost[3] == xo && arrsost[6] == xo) return 1;
        if(arrsost[1] == xo && arrsost[4] == xo && arrsost[7] == xo) return 1;
        if(arrsost[2] == xo && arrsost[5] == xo && arrsost[8] == xo) return 1;
        if(arrsost[0] == xo && arrsost[4] == xo && arrsost[8] == xo) return 1;
        if(arrsost[2] == xo && arrsost[4] == xo && arrsost[6] == xo) return 1;
        return 0;
    }
    public int checkerWinn(String xo){
        if(listButton.get(0).getText() == xo && listButton.get(1).getText() == xo && listButton.get(2).getText() == xo) return 1;
        if(listButton.get(3).getText() == xo && listButton.get(4).getText() == xo && listButton.get(5).getText() == xo) return 1;
        if(listButton.get(6).getText() == xo && listButton.get(7).getText() == xo && listButton.get(8).getText() == xo) return 1;
        if(listButton.get(0).getText() == xo && listButton.get(3).getText() == xo && listButton.get(6).getText() == xo) return 1;
        if(listButton.get(1).getText() == xo && listButton.get(4).getText() == xo && listButton.get(7).getText() == xo) return 1;
        if(listButton.get(2).getText() == xo && listButton.get(5).getText() == xo && listButton.get(8).getText() == xo) return 1;
        if(listButton.get(0).getText() == xo && listButton.get(4).getText() == xo && listButton.get(8).getText() == xo) return 1;
        if(listButton.get(2).getText() == xo && listButton.get(4).getText() == xo && listButton.get(6).getText() == xo) return 1;

        return 0;
    }
    public void toastView (String xo){
        if(xo=="X")
            Toast.makeText(this,"Вы выиграли!", Toast.LENGTH_LONG).show();
        if(xo=="O")
            Toast.makeText(this,"Вы проиграли!", Toast.LENGTH_LONG).show();
        if(xo=="XO")
            Toast.makeText(this,"Ничья!", Toast.LENGTH_LONG).show();
        for (Button item: listButton){
            item.setEnabled(false);
        }
    }
    public void setReset(){
        Intent i = new Intent(this, this.getClass());
        finish();
        this.startActivity(i);
    }
}