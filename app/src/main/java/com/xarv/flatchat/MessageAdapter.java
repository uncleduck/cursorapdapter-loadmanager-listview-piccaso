package com.xarv.flatchat;

/**
 * Created by Administrator on 2015/11/22.
 */
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class MessageAdapter extends CursorAdapter {
    public MessageAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.msg_list_item, parent, false);    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView msgText = (TextView)view.findViewById(R.id.msg_text);
        ImageView msgImage = (ImageView)view.findViewById(R.id.msg_image);
        msgImage.setVisibility(View.VISIBLE);
        msgText.setVisibility(View.VISIBLE);
        String msgType = cursor.getString(cursor.getColumnIndex("MSG_TYPE"));
        if(msgType.equalsIgnoreCase("1")){
            msgText.setVisibility(View.GONE);
            Picasso.with(context)
                    .load(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))))
                    .fit()
                    .into(msgImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.wtf("Picasso", "Success");
                        }

                        @Override
                        public void onError() {
                            Log.wtf("Picasso", "Error");
                        }
                    });
        }
        else{
            msgImage.setVisibility(View.GONE);
            msgText.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3))));
        }
    }
}