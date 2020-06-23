package com.example.kidsmath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

public class play extends AppCompatActivity {
    //--------Initialization----------
    TextView timer,score,preview,showans,resulview;
    Button choice1,choice2,choice3,choice4,reset;
    ArrayList<Integer> answers;
    int sc=0,total=0,count=0,ca=0,wa=0;
    Boolean gameactive=true,timerenable=true;
    int anslocation;
    Random rand;
    //--------CLOCK---------
    public void clock(){
    timerenable=false;

         CountDownTimer countDownTimer=new CountDownTimer(90*1000,1000) {
            @Override
            public void onTick(long l) {
                int min, sec, time;
                time = (int) l / 1000;
                min = time / 60;
                sec = time - min * 60;
                String s = Integer.toString(sec);
                    if (sec < 10)
                        s = "0" + s;
                    timer.setText("0" + Integer.toString(min) + ":" + s);
                    showans.setVisibility(View.INVISIBLE);
                    if (time <10) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.time);
                       mediaPlayer.start();

                }
        }
        @Override
        public void onFinish() {
            MediaPlayer mediacomplete = MediaPlayer.create(getApplicationContext(), R.raw.complete);
            mediacomplete.start();


            reset.setVisibility(View.VISIBLE);
            gameactive=false;
            resulview.setVisibility(View.VISIBLE);
            showans.setVisibility(View.INVISIBLE);
            resulview.setText("\n\n\n\t\tYOUR   TOTAL  ANSWERS : "+Integer.toString(count)+
                            "\n\tTOTAL CORRECT ANSWERS : "+Integer.toString(ca)+
                            "\n\tTOTAL  WRONG  ANSWERS : "+Integer.toString(wa)+
                    "\n  \t\t---------------------------------------------"+
                    "\n TOTAL  MARKS            ( "+Integer.toString(count)+" X 2)=  "+total+
                    "\n FOR CORRECT ANSWERS(+2) ( "+Integer.toString(ca)+" X 2)=  "+Integer.toString(ca*2)+
                    "\n FOR  WRONG  ANSWERS(-1) ( "+Integer.toString(wa)+" X-1)= -"+Integer.toString(wa*1)+
                    "\n  \t\t---------------------------------------------"+
                    "\n                          YOUR SCORES   :   "+Integer.toString(sc)
            );


        }
    }.start();
}
    //----END OF CLOCK------


    public int Addition()
    {
        {
            int A,B;
            A=rand.nextInt(26);
            B=rand.nextInt(26);
            int s =A+B;
            preview.setText(Integer.toString(A)+"+"+Integer.toString(B));
            return  s;
        }

    }
    public int Subtraction()
    {
        {
            int A,B;
            A=rand.nextInt(26);
            B=rand.nextInt(26);
            while(A<B)
                B=rand.nextInt(26);
            int s =A-B;
            preview.setText(Integer.toString(A)+"-"+Integer.toString(B));
            return  s;
        }

    }
    public int Multiplication()
    {
        {
            int A,B;
            A=rand.nextInt(10);
            B=rand.nextInt(10);
            int s =A*B;
            preview.setText(Integer.toString(A)+"X"+Integer.toString(B));
            return  s;
        }

    }
    public int Division()
    {
        {
            int A,B;
            A=rand.nextInt(10);
            B=rand.nextInt(10);
            while(B==0)
                B=rand.nextInt(10);
            int s =A*B;
            preview.setText(Integer.toString(s)+"/"+Integer.toString(B));
            return A;
        }

    }
   //--------MATH VIEW-----------
   public void mathcreater(){
            rand=new Random();
        int x;
        int z;
       answers=new ArrayList<Integer>();
       int mathchoice=rand.nextInt(4);
       switch (mathchoice) {
           case 0 :
               x=Addition();
               z=51;
               break;
           case 1:
               x=Subtraction();
               z=25;
               break;
           case 2:
               x=Multiplication();
               z=90;
               break;

           default:
               x=Division();
               z=11;
               break;
       }

       anslocation=rand.nextInt(4);
       for(int i=0;i<4;i++)
       {
           if(i==anslocation)
           {answers.add(x);
               Log.i("Answer: ",Integer.toString(answers.get(anslocation)));}
           else {
               int wrong=rand.nextInt(z);
               while(x==wrong)
               {  wrong = rand.nextInt(z);}
               answers.add(wrong);
           }
       }
       choice1.setText(Integer.toString(answers.get(0)));
       choice2.setText(Integer.toString(answers.get(1)));
       choice3.setText(Integer.toString(answers.get(2)));
       choice4.setText(Integer.toString(answers.get(3)));
       answers.clear();


    }
    //----END OF MATH VIEW-------

    //--------PLAY AGAIN -------
    public void reset(View view)
    {
        reset.setVisibility(View.INVISIBLE);
        gameactive=true;
        sc=0;
        total=0;
        timerenable=true;
        showans.setVisibility(View.INVISIBLE);
        mathcreater();
        clock();
        score.setText("00/00");
        resulview.setVisibility(View.INVISIBLE);
        count=0;
        ca=0;
        wa=0;
        showans.setVisibility(View.INVISIBLE);
    }
    //-----END OF PLAY AGAIN----
    //--------MAIN STATEMENT ----------
    public void answer(View view) {
if(gameactive) {

    if (Integer.toString(anslocation).equals(view.getTag().toString())) {
        ca+=1;
        count+=1;
        sc += 2;
        total += 2;
        score.setText(Integer.toString(sc) + "/" + Integer.toString(total));
        showans.setVisibility(View.VISIBLE);
        showans.setTextColor(Color.parseColor("#32CD32"));
        showans.setText("Correct");
        MediaPlayer mediacorrect = MediaPlayer.create(getApplicationContext(), R.raw.correct);
        mediacorrect.start();


    } else {
        count+=1;
        wa+=1;
        total += 2;
        sc = sc - 1;
        score.setText(Integer.toString(sc) + "/" + Integer.toString(total));
        showans.setVisibility(View.VISIBLE);
        showans.setTextColor(Color.parseColor("#FF0000"));
        showans.setText("Wrong!");
        MediaPlayer mediawrong = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
        mediawrong.start();
    }
    mathcreater();
}

    }
    //------END OF MAIN STATEMENT -----

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        timer=findViewById(R.id.timer);
        score=findViewById(R.id.score);
        preview=findViewById(R.id.textView3);
        choice1=findViewById(R.id.button4);
        choice2=findViewById(R.id.button5);
        choice3=findViewById(R.id.button6);
        choice4=findViewById(R.id.button7);
        showans=findViewById(R.id.textView5);
        reset=findViewById(R.id.playagin);
        resulview=findViewById(R.id.textView);
        mathcreater();
        clock();
    }
}
