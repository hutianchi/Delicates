package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.R;

public class FeedbackActivity extends AppCompatActivity {

    Toolbar toolbar_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        toolbar_feedback = (Toolbar) findViewById(R.id.toolbar_feedback);
        setSupportActionBar(toolbar_feedback);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_feedback.setNavigationOnClickListener(v -> onBackPressed());

        TextView commit = (TextView) findViewById(R.id.feedback_commit);
        EditText editText = (EditText) findViewById(R.id.feedback_text);
        commit.setOnClickListener(v -> {
            Toast.makeText(this, "您的反馈已提交成功", Toast.LENGTH_SHORT).show();
        });
    }
}
