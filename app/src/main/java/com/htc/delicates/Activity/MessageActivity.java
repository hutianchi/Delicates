package com.htc.delicates.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.htc.delicates.Model.Message;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private int resourceId;

    Toolbar mToolbar_message;

    ListView listView;

    private ArrayAdapter<Message> arrayAdapter;

    private List<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        listView = (ListView) findViewById(R.id.listView_message);
        mToolbar_message = (Toolbar) findViewById(R.id.toolbar_message);
        setSupportActionBar(mToolbar_message);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar_message.setNavigationOnClickListener(v -> onBackPressed());
        initMessageList();
        arrayAdapter = new MessageAdapter(this, R.layout.message_item, messageList);
        listView.setAdapter(arrayAdapter);
    }

    private void initMessageList(){
        for (int i=0; i<15; i++){
            Message ms = new Message("2019.03.0"+i , "Message"+i);
            messageList.add(ms);

        }
    }

    public class MessageAdapter extends ArrayAdapter<Message>{
        public MessageAdapter(Context context, int textViewResourceId, List<Message> list){
            super(context, textViewResourceId, list);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Message message = getItem(position);
            View view;
            ViewHolder viewHolder;
            if (convertView == null){
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.messageTime = (TextView) view.findViewById(R.id.message_time);
                viewHolder.message = (TextView) view.findViewById(R.id.message);
                view.setTag(viewHolder);
            }
            else{
                view = convertView;
                viewHolder =(ViewHolder) view.getTag();
            }
            viewHolder.messageTime.setText(message.messageTime);
            viewHolder.message.setText(message.message);
            return view;
        }


        class ViewHolder{
            TextView messageTime;
            TextView message;
        }
    }

}
