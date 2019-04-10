package com.htc.delicates.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.htc.delicates.R;

public class CommonProblemActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar_problem;

    TextView rent1;
    TextView rent1More;
    TextView rent2;
    TextView rent2More;
    TextView rent3;
    TextView rent3More;
    TextView rent4;
    TextView rent4More;
    TextView rent5;
    TextView rent5More;

    TextView return1;
    TextView return1More;
    TextView return2;
    TextView return2More;
    TextView return3;
    TextView return3More;
    TextView return4;
    TextView return4More;

    TextView other1;
    TextView other1More;
    TextView other2;
    TextView other2More;
    TextView other3;
    TextView other3More;
    TextView other4;
    TextView other4More;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_problem);

        toolbar_problem = (Toolbar) findViewById(R.id.toolbar_problem);
        setSupportActionBar(toolbar_problem);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_problem.setNavigationOnClickListener(v -> onBackPressed());

        rent1 = (TextView) findViewById(R.id.rent1);
        rent1More = (TextView) findViewById(R.id.rent1_more);
        rent2 = (TextView) findViewById(R.id.rent2);
        rent2More = (TextView) findViewById(R.id.rent2_more);
        rent3 = (TextView) findViewById(R.id.rent3);
        rent3More = (TextView) findViewById(R.id.rent3_more);
        rent4 = (TextView) findViewById(R.id.rent4);
        rent4More = (TextView) findViewById(R.id.rent4_more);
        rent5 = (TextView) findViewById(R.id.rent5);
        rent5More = (TextView) findViewById(R.id.rent5_more);

        return1 = (TextView) findViewById(R.id.return1);
        return1More = (TextView) findViewById(R.id.return1_more);
        return2 = (TextView) findViewById(R.id.return2);
        return2More = (TextView) findViewById(R.id.return2_more);
        return3 = (TextView) findViewById(R.id.return3);
        return3More = (TextView) findViewById(R.id.return3_more);
        return4 = (TextView) findViewById(R.id.return4);
        return4More = (TextView) findViewById(R.id.return4_more);

        other1 = (TextView) findViewById(R.id.other1);
        other1More = (TextView) findViewById(R.id.other1_more);
        other2 = (TextView) findViewById(R.id.other2);
        other2More = (TextView) findViewById(R.id.other2_more);
        other3 = (TextView) findViewById(R.id.other3);
        other3More = (TextView) findViewById(R.id.other3_more);
        other4 = (TextView) findViewById(R.id.other4);
        other4More = (TextView) findViewById(R.id.other4_more);


        rent1.setOnClickListener(this);
        rent2.setOnClickListener(this);
        rent3.setOnClickListener(this);
        rent4.setOnClickListener(this);
        rent5.setOnClickListener(this);
        return1.setOnClickListener(this);
        return2.setOnClickListener(this);
        return3.setOnClickListener(this);
        return4.setOnClickListener(this);
        other1.setOnClickListener(this);
        other2.setOnClickListener(this);
        other3.setOnClickListener(this);
        other4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rent1:
                if (rent1More.getVisibility() == View.GONE){
                    rent1More.setVisibility(View.VISIBLE);
                }else{
                    rent1More.setVisibility(View.GONE);
                }
                break;
            case R.id.rent2:
                if (rent2More.getVisibility() == View.GONE){
                    rent2More.setVisibility(View.VISIBLE);
                }else{
                    rent2More.setVisibility(View.GONE);
                }
                break;
            case R.id.rent3:
                if (rent3More.getVisibility() == View.GONE){
                    rent3More.setVisibility(View.VISIBLE);
                }else{
                    rent3More.setVisibility(View.GONE);
                }
                break;
            case R.id.rent4:
                if (rent4More.getVisibility() == View.GONE){
                    rent4More.setVisibility(View.VISIBLE);
                }else{
                    rent4More.setVisibility(View.GONE);
                }
                break;
            case R.id.rent5:
                if (rent5More.getVisibility() == View.GONE){
                    rent5More.setVisibility(View.VISIBLE);
                }else{
                    rent5More.setVisibility(View.GONE);
                }
                break;
            case R.id.return1:
                if (return1More.getVisibility() == View.GONE){
                    return1More.setVisibility(View.VISIBLE);
                }else{
                    return1More.setVisibility(View.GONE);
                }
                break;
            case R.id.return2:
                if (return2More.getVisibility() == View.GONE){
                    return2More.setVisibility(View.VISIBLE);
                }else{
                    return2More.setVisibility(View.GONE);
                }
                break;
            case R.id.return3:
                if (return3More.getVisibility() == View.GONE){
                    return3More.setVisibility(View.VISIBLE);
                }else{
                    return3More.setVisibility(View.GONE);
                }
                break;
            case R.id.return4:
                if (return4More.getVisibility() == View.GONE){
                    return4More.setVisibility(View.VISIBLE);
                }else{
                    return4More.setVisibility(View.GONE);
                }
                break;
            case R.id.other1:
                if (other1More.getVisibility() == View.GONE){
                    other1More.setVisibility(View.VISIBLE);
                }else{
                    other1More.setVisibility(View.GONE);
                }
                break;
            case R.id.other2:
                if (other2More.getVisibility() == View.GONE){
                    other2More.setVisibility(View.VISIBLE);
                }else{
                    other2More.setVisibility(View.GONE);
                }
                break;
            case R.id.other3:
                if (other3More.getVisibility() == View.GONE){
                    other3More.setVisibility(View.VISIBLE);
                }else{
                    other3More.setVisibility(View.GONE);
                }
                break;
            case R.id.other4:
                if (other4More.getVisibility() == View.GONE){
                    other4More.setVisibility(View.VISIBLE);
                }else{
                    other4More.setVisibility(View.GONE);
                }
                break;
        }
    }
}
