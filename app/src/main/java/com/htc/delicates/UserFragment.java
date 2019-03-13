package com.htc.delicates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserFragment extends Fragment {

    TextView userName;

    TextView memberLevel;

    Button toBeMember;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userName = (TextView) view.findViewById(R.id.user_name);
        memberLevel = (TextView) view.findViewById(R.id.member_level);
        toBeMember = (Button) view.findViewById(R.id.to_be_member);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_user);
        mToolbar.inflateMenu(R.menu.menu_user);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_message:
                    Intent intent = new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        return view;
    }
}
