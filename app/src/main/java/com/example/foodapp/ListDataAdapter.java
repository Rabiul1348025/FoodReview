package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//this class is used to list items on the foods activity. it uses a list view
public class ListDataAdapter extends ArrayAdapter {
    List list= new ArrayList(  );
    public ListDataAdapter(Context context, int resource) {
        super( context, resource );
    }
    static class LayoutHandler{
        public RatingBar viewRating;
        TextView viewFood, viewDesc, viewPrice;
    }
    @Override
    public void add(Object object) {
        super.add( object );
        list.add( object );
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get( position );
    }

    @Override
    //lists all items
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutHandler layoutHandler;
        if (row==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE  );
            row=layoutInflater.inflate( R.layout.row_layout,parent,false );
            layoutHandler= new LayoutHandler();
            layoutHandler.viewFood=(TextView) row.findViewById( R.id.viewFood );
            layoutHandler.viewDesc=(TextView) row.findViewById( R.id.viewDesc );
            layoutHandler.viewPrice=(TextView) row.findViewById( R.id.viewPrice );
            layoutHandler.viewRating=(RatingBar) row.findViewById( R.id.ratingsBar );
            row.setTag( layoutHandler );

        }else {
            layoutHandler=(LayoutHandler) row.getTag();

        }
        DataProvider dataProvider=(DataProvider) this.getItem( position );
        layoutHandler.viewFood.setText( dataProvider.getFood() );
        layoutHandler.viewDesc.setText( dataProvider.getDes() );
        layoutHandler.viewPrice.setText( dataProvider.getPrice() );
        layoutHandler.viewRating.setRating( dataProvider.getRating() );
        return row;
    }
}
