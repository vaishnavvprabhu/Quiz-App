package com.vaisworks.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class QuestionsActivity extends AppCompatActivity {

    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    ImageView imageView;

    String questions[] = {
            "Name the show this actor appears in?",
            "Who is this?",
            "Name a song of this artist?",
            "Which Animal is this?"
    };
    String answers[] = {"KBC","Kanye West","Ocean Eyes","Cat"};
    String opt[] = {
            "KBC","Khatron ke Khiladi","Money Heist","Manifest",
            "Billie Eilish","Kanye West","Akshay Kumar","Dwayne Johnson (the rock)",
            "Wolves","Trees","Catch","Ocean Eyes",
            "Tiger","Cat","Dog","Cheetah"
    };
    String imgsrc[] = {
            "R.drawable.q1",
            "R.drawable.q2",
            "R.drawable.q3",
            "R.drawable.q4"
    };
    int flag=0;
    int counter=4;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        getSupportActionBar().hide();

        Intent intent = getIntent();

        final TextView score = (TextView)findViewById(R.id.score);
//        TextView textView=(TextView)findViewById(R.id.DispName);
//        Intent intent = getIntent();
//        String name= intent.getStringExtra("myname");
//
//        if (name.trim().equals(""))
//            textView.setText("Hello User");
//        else
//            textView.setText("Hello " + name);


        submitbutton=(Button)findViewById(R.id.submit);
        quitbutton=(Button)findViewById(R.id.quit);
        tv=(TextView) findViewById(R.id.question);
        imageView=(ImageView) findViewById(R.id.imgView);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;
                counter--;

                if (score != null)
                    score.setText(""+counter+"Qs");

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
//                    Toast.makeText(getApplicationContext(), imgsrc[flag] ,Toast.LENGTH_LONG).show();
                    if(flag==0){
                        Picasso.get().load(R.drawable.q1).into(imageView);
                    }
                    else if(flag==1){
                        Picasso.get().load(R.drawable.q2).into(imageView);
                    }
                    else if(flag==2){
                        Picasso.get().load(R.drawable.q3).into(imageView);
                    }
                    else if(flag==3){
                        Picasso.get().load(R.drawable.q4).into(imageView);
                    }

                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });

    }
}