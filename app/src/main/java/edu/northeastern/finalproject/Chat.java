package edu.northeastern.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chat extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public List<Sticker> myList;
    static final String STATE_USER = "user";
    private String mUser;
    private String message;
    private final String TAG = "InChat";
    private String fireName;
    private TextInputLayout recipient;
    private TextInputLayout recipientMessage;
    private FloatingActionButton send;
    private DatabaseReference myDatabase;
    private int [] images;
    private List <String> fireUserNames;
    private DatabaseReference root;
    private List<DataSnapshot> firechild;
    private Random rand;
    private int sourceCompat;

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
        setContentView(R.layout.activity_chat);
        getUserName();
        addListener();
        getPic();
        //========================================================================================
        recyclerView = findViewById(R.id.rv);
        myList = new ArrayList<>();
        adapter = new UserAdapter(myList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        fireUserNames = new ArrayList<>();
        firechild = new ArrayList<>();
        rand = new Random();
        myDatabase = FirebaseDatabase.getInstance().getReference();
        sourceCompat = images[0];

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Sticker tempSticker = snapshot.getValue(Sticker.class);
                ///Iterable <DataSnapshot> stickerArr = snapshot.getChildren();
                Iterable <DataSnapshot> child = snapshot.getChildren();
                Log.i(TAG, "child created successfully");
                for (DataSnapshot each: child) {
                    //Log.i(TAG, "pass line1");
                    String tempString = each.getKey();
                    //Log.i(TAG, "pass line2");
                    fireUserNames.add(tempString);
                    //Log.i(TAG, "pass line3");
                    firechild.add(each);
                    //Log.i(TAG, "pass line4");
                }
                Log.e(TAG, fireUserNames.toString());

                for (int i = 0; i < fireUserNames.size(); i++) {
                    Log.i(TAG, "pass line1");
                    DatabaseReference usr = root.child(fireUserNames.get(i));
                    Log.i(TAG, "pass line2");
                    Log.i(TAG, firechild.toString());
                    Iterable <DataSnapshot> stickerArr = firechild.get(i).getChildren();
                    Log.i(TAG, "pass line3");
                    for (DataSnapshot each: stickerArr) {
                        Sticker tempSticker = each.getValue(Sticker.class);


                        if (tempSticker != null ) {
                            //int temppic = tempSticker.getSrcCompat();
                            //String status = tempSticker.getStatus();

                            /*2
                            if (fireName.equalsIgnoreCase("adi")) {
                             Log.w(TAG, "in the first if name == adi");
                             tempSticker.setSrcCompat(images[2]);

                             }
                             else if (fireName.toLowerCase().charAt(0) == 'a') {

                             tempSticker.setSrcCompat(images[3]);
                             }
                             else if (fireName.toLowerCase().charAt(0) == 'k') {
                             Log.w(TAG, "in the first if name == kellen");
                             tempSticker.setSrcCompat(images[4]);
                             }
                             else if (fireName.toLowerCase().charAt(0) == 'z' || fireName.toLowerCase().charAt(0) == 'h' || fireName.toLowerCase().charAt(0) == 'm'  ) {
                             tempSticker.setSrcCompat(images[1]);
                             }
                             else {
                             tempSticker.setSrcCompat(images[0]);
                             }2*/

                            //1myList.add(tempSticker);
                            //1adapter.notifyItemInserted(1);
                        }
                        else{
                            Log.d(TAG, "got a null");
                        }
                        Log.e(TAG, "test before rv list(8): " + fireUserNames.get(i) + " ?? fireName: " + fireName);
                        if (fireUserNames.get(i).equalsIgnoreCase(fireName)) {
                            if (!myList.contains(tempSticker)) {
                                myList.add(tempSticker);
                                adapter.notifyItemInserted(1);
                            }


                        }
                    }

                }


                /*
                if (tempSticker != null) {
                    //int temppic = tempSticker.getSrcCompat();
                    //String status = tempSticker.getStatus();
                    myList.add(tempSticker);
                    adapter.notifyItemInserted(1);
                }
                else{
                    Log.d(TAG, "got a null");
                }*/
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "data load has errors");
            }
        };

        root = FirebaseDatabase.getInstance().getReference("Project");
        if (fireName == null) {
            Log.e(TAG, "fireName is null");
            Toast.makeText(Chat.this, "You are a unregistered user", Toast.LENGTH_LONG).show();
        }
        else {
            Log.d(TAG, "===firebase received name: " + fireName);
            ///DatabaseReference usr = root.child(fireName);
            ///use to be usr
            root.addValueEventListener(postListener);
        }




    }


    /*public void writeUserDatabase(String userID) {
        myDatabase.child("User").child(userID);
    }*/

    private void getUserName() {
        Intent receiver = getIntent();
        fireName = receiver.getStringExtra("Firebase");
        Log.e(TAG, fireName);
    }
    private void addListener() {
        send = (FloatingActionButton) findViewById(R.id.floatingButton);
        recipient = (TextInputLayout) findViewById(R.id.textInput);
        recipientMessage = (TextInputLayout) findViewById(R.id.textInput2);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Chat.this,"send Button clicked", Toast.LENGTH_LONG).show();
                String text1 = String.valueOf(recipient.getEditText().getText());
                String text2 = String.valueOf(recipientMessage.getEditText().getText());
                Log.e(TAG, "Firename: " + fireName);
                Log.e(TAG, "recipient: " + text1);
                Log.e(TAG, "Message: " + text2);
                if (text1 == null || text2 == null ){
                    Toast.makeText(Chat.this, "Don't miss recipent name", Toast.LENGTH_LONG).show();
                }
                else if (fireName == null) {
                    Toast.makeText(Chat.this, "you are a unregistered user", Toast.LENGTH_LONG).show();
                }
                else {
                    Log.e(TAG, "======in final else fireName = " + fireName);


                    if (fireName.equalsIgnoreCase("adi")) {
                        Log.w(TAG, "in the first if name == adi");
                        sourceCompat = images[2];

                    }
                    else if (fireName.toLowerCase().charAt(0) == 'a') {

                        sourceCompat = images[3];
                    }
                    else if (fireName.toLowerCase().charAt(0) == 'k') {
                        Log.w(TAG, "in the first if name == kellen");
                        sourceCompat = images[4];
                    }
                    else if (fireName.toLowerCase().charAt(0) == 'z' || fireName.toLowerCase().charAt(0) == 'h' || fireName.toLowerCase().charAt(0) == 'm'  ) {
                        sourceCompat = images[1];
                    }
                    else {
                        sourceCompat = images[0];
                    }




                    writeNewSticker(text1, String.valueOf(rand.nextInt(100000)), text2, sourceCompat);
                    //myList.add(new Sticker( images[0], "this is a test code"));
                    //adapter.notifyItemInserted(1);
                    Log.e(TAG, "Recycler view should inserted");
                }
            }
        });
    }
    public void writeNewSticker(String userID, String collection ,String status, int scrCompat) {
        Sticker temp = new Sticker( scrCompat,status);
        Log.e(TAG, "a temp sticker created in function writeNewSticker");
        myDatabase.child("Project").child(userID).child(collection).setValue(temp);
    }
    private void getPic() {
        images = new int[5];
        images[0] = R.drawable.suba1;
        images[1] = R.drawable.suba2;
        images[2] = R.drawable.suba3;
        images[3] = R.drawable.suba4;
        images[4] = R.drawable.suba5;
    }

}