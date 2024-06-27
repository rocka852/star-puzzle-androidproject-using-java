package edu.northeastern.finalproject;

import android.graphics.drawable.Drawable;
import android.util.Log;
public class Sticker {
    public final String TAG = "interface2";
    public String status;
    public int srcCompat;

    public Sticker(int srcCompat, String status) {
        this.srcCompat = srcCompat;
        this.status = status;
        Log.d(TAG, String.format("In Sticker constructor %s, %d", this.status,this.srcCompat));
    }

    public Sticker() {}

    public String getStatus() {
        //Log.d(TAG, "getter in sticker return string" + this.status);
        return this.status;
    }


    public int getSrcCompat() {
        //Log.d(TAG,String.format("getter in Sticker return numbber %d", this.srcCompat));
        return this.srcCompat;
    }

    public void setSrcCompat(int i) {
        this.srcCompat = i;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if(!(other instanceof Sticker)) {
            return false;
        }
        Sticker temp = (Sticker) other;
        return (temp.getSrcCompat() == this.getSrcCompat()) && (temp.getStatus().equals(this.getStatus()));
    }
}
