package edu.northeastern.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout shipname;
    private static final String TAG = "InMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button click = (Button) findViewById(R.id.button);
        FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.floatButton);
        shipname = (TextInputLayout) findViewById(R.id.textInput);

        Intent game = new Intent(MainActivity.this, Puzzle.class);
        Intent chat = new Intent(MainActivity.this, Chat.class);
        Intent story = new Intent(MainActivity.this, Story.class);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "button clicked");
                String text = String.valueOf(shipname.getEditText().getText());
                chat.putExtra("Firebase", text);
                game.putExtra("Firebase", text);
                story.putExtra("Sender", "Start");
                story.putExtra("Firebase", text);
                startActivity(story);
            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.valueOf(shipname.getEditText().getText());
                Log.e(TAG, "the ship name is: " + text);
                chat.putExtra("Firebase", text);
                game.putExtra("Firebase", text);

                startActivity(chat);
            }
        });
    }
}