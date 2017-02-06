package org.example.tanmay.droidvbeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<PostsData>{

    private TextView title;
    private TextView link;
    private TextView date;

    public CustomAdapter(Context context, ArrayList<PostsData> posts) {
       super(context, R.layout.custom_list_row, posts);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_list_row,parent,false);
        title = (TextView)customView.findViewById(R.id.title);
        link = (TextView)customView.findViewById(R.id.link);
        date = (TextView)customView.findViewById(R.id.date);

        title.setText( getItem(position).getTitle());
        link.setText( getItem(position).getLink());
        date.setText( getItem(position).getPubDate());

        return customView;
    }

}
