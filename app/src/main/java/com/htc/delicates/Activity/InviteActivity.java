package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.htc.delicates.R;

public class InviteActivity extends AppCompatActivity {

    Toolbar toolbar_invite;

    EditText insertCode;

    TextView codeCommit;

    TextView code;

    String inviteCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        toolbar_invite = (Toolbar) findViewById(R.id.toolbar_invite);
        setSupportActionBar(toolbar_invite);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_invite.setNavigationOnClickListener(v -> onBackPressed());

        code = (TextView) findViewById(R.id.invite_code);
        codeCommit = (TextView) findViewById(R.id.invite_code_commit);
        insertCode = (EditText) findViewById(R.id.insert_invite_code);

        inviteCode = "GH78BB6";

        code.setText(inviteCode);
        codeCommit.setOnClickListener(v -> {
            Toast.makeText(this, insertCode.getText()+"  提交成功", Toast.LENGTH_SHORT).show();
        });


    }
}
