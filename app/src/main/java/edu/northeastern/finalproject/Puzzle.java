package edu.northeastern.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Puzzle extends AppCompatActivity {
    private Map<Integer, Integer> dict;
    private int [][] correct = new int [][] {{0,1,2},{3,4,5},{6,7,8}};
    private int [][] shuffle = new int [][] {{0,1,2},{3,4,5},{6,7,8}};
    private PuzzleObject app;
    private int [] buttonID;
    //this is an int array with buttonID: int R.id
    private int [] Img;
    //this is an int array with ImgID: int drawable.id
    private int [] keys;
    //this is an int array with number int:012345678
    private ImageButton [] imgButton;
    //this is a button array with button object Button: object
    static final String STATE_USER = "user";
    private String mUser;
    private String message;
    private static final String TAG = "InPuzzle";
    private String fireName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            mUser = savedInstanceState.getString(STATE_USER);
        }
        else{
            mUser = "NewUser";
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString("secrectuser");
        }
        setContentView(R.layout.activity_puzzle);
        getUserName();
        // ===================================================================================

        Button run = (Button) findViewById(R.id.run);
        Button success = (Button) findViewById(R.id.cheat);
        Button shuffle = (Button) findViewById(R.id.shuffle);
        Intent story = new Intent(Puzzle.this, Story.class);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                story.putExtra("Sender", "Frompuzzle");
                story.putExtra("Firebase", fireName);
                startActivity(story);
            }
        });
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runApp();
                Log.d(TAG, "Run button clicked");
            }
        });

        ImageButton test = (ImageButton) findViewById(R.id.image01);
        test.setTag("1");
        /*
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object kk = test.getTag();
                Log.e(TAG, kk.toString());
                int temp = R.drawable.bb1;
                Log.e(TAG, String.format("%d", temp));
                test.setImageResource(temp);
                Log.e(TAG, "button clicked");
            }
        });*/

        setButtons();

    }

    public void runApp() {
        app.shuffle();
        Log.d(TAG, app.toString());
        int [][] temp = app.getArray();
        for (int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                imgButton[i * 3 + j].setTag(String.valueOf(temp[i][j]));
            }
        }

        for (int i = 0; i < imgButton.length; i++) {
            String tag_key = (String)imgButton[i].getTag();
            int tag_num = Integer.parseInt(tag_key);
            int value = dict.get(tag_num);
            Log.d(TAG, tag_key + " : " + String.valueOf(value));
            imgButton[i].setImageResource(value);
        }

    }
    private void switchPicAndTag(int x) { //x = 0
        Log.e(TAG, "button clicked is: " + String.valueOf(x));
        String tag_key = (String)imgButton[x].getTag(); //button0 is 2
        int tag_num = Integer.parseInt(tag_key); //2
        int pic = dict.get(tag_num);             //pic2
        Log.e(TAG, "The clicked pic x is" + tag_key);

        if(app.nextTo(tag_num)) {
            for(int i = 0; i < imgButton.length; i ++) {
                String tagZero = (String)imgButton[i].getTag();

                if(tagZero.equals("0")) {
                    Log.e(TAG, "find 0 at index " + String.valueOf(i));
                    Log.e(TAG, "Pic 0 is" + tagZero);
                    int tagNZero = Integer.parseInt(tagZero);     //0
                    int picZero = dict.get(tagNZero);             //pic0

                    imgButton[x].setTag(String.valueOf(tagNZero));
                    imgButton[x].setImageResource(picZero);
                    imgButton[i].setTag(String.valueOf(tag_num));
                    imgButton[i].setImageResource(pic);
                    app.switchTo(tag_num);
                    Log.e(TAG, app.toString());
                    Log.e(TAG, "After switch 0 is: " + imgButton[x].getTag() + " clicked image is " + imgButton[i].getTag());
                    break;
                }
            }
        }
        else {
            Log.e(TAG, "this is not the adjcent button");
        }
    }
    public void imgClick(View v) {
        int temp = v.getId();
        if (temp == buttonID[0]) {
            switchPicAndTag(0);

            Log.e(TAG, "button00 clicked");
        }
        else if (temp == buttonID[1]) switchPicAndTag(1);
        else if (temp == buttonID[2]) switchPicAndTag(2);
        else if (temp == buttonID[3]) switchPicAndTag(3);
        else if (temp == buttonID[4]) switchPicAndTag(4);
        else if (temp == buttonID[5]) switchPicAndTag(5);
        else if (temp == buttonID[6]) switchPicAndTag(6);
        else if (temp == buttonID[7]) switchPicAndTag(7);
        else if (temp == buttonID[8]) switchPicAndTag(8);
        else {
            Log.e(TAG, "other button clicked");
        }
    }
    public void setButtons() {
        Img = new int[9];
        keys = new int[9];
        Img[0] = R.drawable.bb1;
        Img[1] = R.drawable.bb2;
        Img[2] = R.drawable.bb3;
        Img[3] = R.drawable.bb4;
        Img[4] = R.drawable.bb5;
        Img[5] = R.drawable.bb6;
        Img[6] = R.drawable.bb7;
        Img[7] = R.drawable.bb8;
        Img[8] = R.drawable.bb9;
        for(int i = 0; i < keys.length; i++) {
            keys[i] = i;
        }
        Log.d(TAG, Arrays.toString(Img));
        Log.d(TAG, Arrays.toString(keys));

        buttonID = new int[9];
        buttonID[0] = R.id.image00;
        buttonID[1] = R.id.image01;
        buttonID[2] = R.id.image02;
        buttonID[3] = R.id.image10;
        buttonID[4] = R.id.image11;
        buttonID[5] = R.id.image12;
        buttonID[6] = R.id.image20;
        buttonID[7] = R.id.image21;
        buttonID[8] = R.id.image22;

        imgButton = new ImageButton[9];
        for(int i = 0; i < imgButton.length; i++) {
            imgButton[i] = (ImageButton) findViewById(buttonID[i]);
            //imgButton[i].setTag(String.valueOf(i));
        }

        dict = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
           dict.put(keys[i], Img[i]);
        }
        for (Map.Entry<Integer, Integer> entry: dict.entrySet()) {
            String temp = entry.getKey() + " : " + entry.getValue();
            Log.d(TAG, temp);
        }



        app = new PuzzleObject(shuffle);

        int [][] temp = app.getArray();
        for (int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp[i].length; j++) {
                imgButton[i * 3 + j].setTag(String.valueOf(temp[i][j]));
            }
        }

        for (int i = 0; i < imgButton.length; i++) {
            String tag_key = (String)imgButton[i].getTag();
            int tag_num = Integer.parseInt(tag_key);
            int value = dict.get(tag_num);
            Log.d(TAG, tag_key + " : " + String.valueOf(value));
            imgButton[i].setImageResource(value);
        }
    }

    private void getUserName() {
        Intent receiver = getIntent();
        fireName = receiver.getStringExtra("Sender");
        Log.e(TAG, fireName);
    }

}