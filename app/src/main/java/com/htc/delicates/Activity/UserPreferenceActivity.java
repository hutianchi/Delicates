package com.htc.delicates.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UserPreferenceActivity extends AppCompatActivity {

    private String tags[] = new String[]{"7日上新","可爱减龄","优雅职场","通勤造型","美式休闲","浪漫度假","潮流风尚","约会着装","high店着装"};
    private Boolean tagsflag[] = new Boolean[9];
    private ArrayList<String> tagList = new ArrayList<>();

    Toolbar toolbar_pref;

    GridView dislikeList;

    TextView saveDislike;

    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);

        toolbar_pref = (Toolbar) findViewById(R.id.toolbar_preference);
        setSupportActionBar(toolbar_pref);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_pref.setNavigationOnClickListener(v -> onBackPressed());

        Collections.addAll(tagList,tags);
        Arrays.fill(tagsflag, false);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(45, 5, 30, 5);

        dislikeList = (GridView) findViewById(R.id.dislike_list);
        dislikeList.setAdapter(new ArrayAdapter<String>(this, R.layout.select_tag_item, tagList));
        dislikeList.setOnItemClickListener((parent, view1, position, id) -> {
            TextView tv = (TextView)  view1.findViewById(R.id.tv_select_tag);
            if (tagsflag[position]){
                tagsflag[position] = false;
                tv.setTextColor(Color.parseColor("#8a000000"));
                tv.setBackgroundColor(Color.parseColor("#fcfcfc"));
                --count;
            }else {
                if (count<4){
                    tagsflag[position] = true;
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setBackgroundColor(Color.parseColor("#e0e0e0"));
                    ++count;
                }else {
                    Toast.makeText(this, "最多选择4个不喜欢的标签！", Toast.LENGTH_SHORT).show();
                }
            }

        //    Toast.makeText(this,tv.getText().toString()+position,Toast.LENGTH_SHORT).show();
        });

        saveDislike = (TextView) findViewById(R.id.dislike_save);
        saveDislike.setOnClickListener(v -> {
            String str = "";
            for (int i=0; i<tags.length; i++){
                if (tagsflag[i]){
                    str += tags[i];
                }
            }
            Toast.makeText(this, str,Toast.LENGTH_SHORT).show();
        });
    }
}
