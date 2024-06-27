package edu.northeastern.finalproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.List;
public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
        public List<Sticker> newinfo;
        public final String TAG = "recycler_view";

        // constructor
        public UserAdapter (List <Sticker> newinfo) {

            this.newinfo = newinfo;
            Log.d(TAG, "constructor run");
        }
        public UserAdapter () {}
        //==================================================================================

        public static class UserViewHolder extends RecyclerView.ViewHolder {
            //public ConstraintLayout container;
            public LinearLayout container;
            public ImageView sticker;
            public TextView textView;

            public UserViewHolder(View view) {
                super(view);
                container = (LinearLayout) view.findViewById(R.id.url_cell);
                Log.d("innerclass", "get container");
                sticker = (ImageView) view.findViewById(R.id.icon);
                Log.d("innerclass", "get sticker");
                textView = (TextView) view.findViewById(R.id.message);
                Log.d("innerclass", "get textView");
                //Intent i = new Intent(Intent.ACTION_VIEW);
                Log.d("innerclass", "get intent");


                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("recycler_view", "click successful");
                    }
                });
            }
        }

            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell, parent, false);
                Log.d(TAG, "onCreateViewHolder run");
                return new UserViewHolder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
                Log.d(TAG, "======onBindViewHolder start");
                Sticker current = newinfo.get(position);
                Log.d(TAG, "======line1 runs");

                holder.sticker.setImageResource(current.getSrcCompat());
                //holder.sticker.setImageResource(R.drawable.suba1);
                //holder.sticker.setBackgroundResource(R.drawable.suba1);
                Log.d(TAG, "======line2 runs");
                holder.textView.setText(current.getStatus());
                Log.d(TAG, "======line3 runs");
                Log.d(TAG, "onBindViewHolder finish");
            }
            @Override
            public int getItemCount() {
                Log.d(TAG, "getItem run");
                return newinfo.size();
            }

            public void addValue(int srcCompat, String status) {
                Log.d(TAG, "addValue run");
                newinfo.add(new Sticker(srcCompat, status));
                notifyDataSetChanged();
            }
}
