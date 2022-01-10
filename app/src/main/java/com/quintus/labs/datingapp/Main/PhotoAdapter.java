package com.quintus.labs.datingapp.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.clmca.labs.datingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
/*github TienDatFE*/

public class PhotoAdapter extends ArrayAdapter<Cards> {
    Context mContext;
    List<String> pictures;


    public PhotoAdapter(@NonNull Context context, int resource, @NonNull List<Cards> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Cards card_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        //chi tiết
        ImageButton btnInfo = convertView.findViewById(R.id.checkInfoBeforeMatched);
        name.setText(card_item.getName() + ", " + card_item.getAge());

        ImageView images = convertView.findViewById(R.id.images);

        for (String picture : card_item.getPictures()) {
            Glide.with(convertView.getContext()).load(picture).into(images);
        }
        Glide.with(convertView.getContext()).load(card_item.getPictures().get(0)).into(images);





        //detail cards
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileCheckinMain.class);
                intent.putExtra("name", card_item.getName() + ", " + card_item.getAge());
                intent.putExtra("bio", card_item.getBio());
                intent.putExtra("interest", card_item.getInterest());
                intent.putExtra("distance", card_item.getDistance());
                intent.putExtra("pictures", new ArrayList<>(card_item.getPictures()));

                mContext.startActivity(intent);


            }
        });


        return convertView;
    }

}
