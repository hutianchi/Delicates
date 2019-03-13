package com.htc.delicates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.htc.delicates.Model.Order;
import com.htc.delicates.Util.OrderAdapter;
import com.htc.delicates.Util.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChangfuOrderFragment extends Fragment {


    SlideRecyclerView mRecyclerView;

    OrderAdapter orderAdapter;

    List<Order> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_changfu, container, false);
        mRecyclerView = (SlideRecyclerView) view.findViewById(R.id.order_changfu_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.my_divider));
        mRecyclerView.addItemDecoration(divider);
        init();
        orderAdapter = new OrderAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener((adapter, v, position) ->{

            //
            //  订单详情页跳转
            //

            Toast.makeText(getActivity(),"this is order detail!!!     "+ mList.get(position).orderName, Toast.LENGTH_SHORT).show();
        });
        orderAdapter.setOnDeleteClickListener((view1, position) -> {
            mList.remove(position);
            orderAdapter.notifyDataSetChanged();
            mRecyclerView.closeMenu();
        });
        return view;
    }

    private void init(){
        for (int i=0;i<3;i++){
            Order order1 = new Order(R.drawable.test1,"test1");
            Order order2 = new Order(R.drawable.test2,"test2");
            Order order3 = new Order(R.drawable.test3,"test3");
            Order order4 = new Order(R.drawable.test4,"test4");
            Order order5 = new Order(R.drawable.test5,"test5");
            mList.add(order1);
            mList.add(order2);
            mList.add(order3);
            mList.add(order4);
            mList.add(order5);
        }
    }

}
