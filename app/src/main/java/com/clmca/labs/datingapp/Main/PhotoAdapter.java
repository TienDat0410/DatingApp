package com.clmca.labs.datingapp.Main;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.clmca.labs.datingapp.R;

import java.util.List;
/*github TienDatFE*/

public class PhotoAdapter extends ArrayAdapter<Cards> {
    Context mContext;
    private ImageView imageView;


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
        //ánh xạ hình ảnh
        imageView = convertView.findViewById(R.id.image);
        //chi tiết
        ImageButton btnInfo = convertView.findViewById(R.id.checkInfoBeforeMatched);
        name.setText(card_item.getName() + ", " + card_item.getAge());
        //detail cards
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileCheckinMain.class);
                intent.putExtra("name", card_item.getName() + ", " + card_item.getAge());
                intent.putExtra("photo", card_item.getProfileImageUr());
                intent.putExtra("bio", card_item.getBio());
                intent.putExtra("interest", card_item.getInterest());
                intent.putExtra("distance", card_item.getDistance());
                intent.putExtra("moreimage", card_item.getMoreImageUr());
                mContext.startActivity(intent);
            }
        });
        switch (card_item.getProfileImageUr()) {
            case "defaultFemale":
                Glide.with(getContext()).load(R.drawable.default_woman).into(imageView);
                break;
            case "defaultMale":
                Glide.with(getContext()).load(R.drawable.default_man).into(imageView);
                break;
            default:
                Glide.with(getContext()).load(card_item.getProfileImageUr()).into(imageView);
                break;
        }

        return convertView;
    }

}
