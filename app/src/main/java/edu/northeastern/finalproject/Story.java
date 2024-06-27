package edu.northeastern.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Story extends AppCompatActivity {
    private String [] storyLine;
    private String passInfo;
    private String fireName;
    private int index;
    private final String TAG = "Story";
    private FloatingActionButton forward;
    private FloatingActionButton backward;
    private TextView text;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "reach line 26");
        setContentView(R.layout.activity_story);
        Log.d(TAG, "reach line 28");
        getUserNames();
        declareString();
        Log.d(TAG, "reach line 30");
        text = (TextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.imageView);
        forward = (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        backward = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        if ((passInfo != null) && passInfo.equals("Start")) {
            Log.d(TAG, "no null");
            index = 0;
            text.setText(storyLine[index]);
            index ++;
        }
        else if((passInfo != null) && passInfo.equals("Frompuzzle")) {
            index = 10;
            text.setText(storyLine[index]);
            index ++;
        }
        else{
            Log.d(TAG, "in else");
            index = 0;
        }
        Log.d(TAG, "reach line 35");


        Log.d(TAG, "reach line 41");
        Intent game = new Intent(Story.this, Puzzle.class);
        Intent chat = new Intent(Story.this, Chat.class);
        Intent mainA = new Intent(Story.this, MainActivity.class);

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(storyLine[index]);
                Log.e(TAG, String.format("index is %d",index));
                index ++;
                if (index == 5) {
                    int baby = R.drawable.baby;
                    Log.e(TAG, "got yoda" + String.valueOf(baby));
                    image.setImageResource(baby);
                }
                if (index == 11) {
                    game.putExtra("Sender", fireName);
                    startActivity(game);
                }
                if(index >15) {
                    startActivity(mainA);
                }
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(storyLine[index]);
                index --;
            }
        });
    }
    private void getUserNames() {
        Log.d(TAG, "reach line a");
        Intent storyReceiver = getIntent();
        Log.d(TAG, "reach line b");
        passInfo = storyReceiver.getStringExtra("Sender");
        Log.d(TAG, "reach line c");
        Log.e(TAG, passInfo);
        fireName = storyReceiver.getStringExtra("Firebase");
    }



    public void declareString() {
        storyLine = new String[18];
        storyLine[0] ="Caption, We have to land on this mystery planet";
        storyLine[1]="Landing! Successfully";
        storyLine[2]="You find a weird creature";
        storyLine[3]="......";
        storyLine[4]="...........";
        storyLine[5]= ".................";
        storyLine[6]="Don't bother me.....";
        storyLine[7]="OK, I have been working on this puzzle for a long time";
        storyLine[8]="You think you could help me? ";
        storyLine[9]="If you made it, I will reward you.";
        storyLine[10]="..........";
        storyLine[11]="How did you make it? ";
        storyLine[12]="Well promise is promise";
        storyLine[13]="I will give you this Phone";
        storyLine[14]="You already have one? Yours won't work on this planet";
        storyLine[15]= "";
        storyLine[16]= "";
        storyLine[17]= "";

    }
}