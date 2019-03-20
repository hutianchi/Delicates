package com.htc.delicates.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.htc.delicates.Adapter.OrderAdapter;
import com.htc.delicates.Model.Order;
import com.htc.delicates.MyView.SlideRecyclerView;
import com.htc.delicates.R;

import java.util.ArrayList;
import java.util.List;

public class ExceptionOrderFragment extends Fragment {

    SlideRecyclerView recyclerView_compensate;

    SlideRecyclerView recyclerView_compensated;

    OrderAdapter compensateAdapter;

    OrderAdapter compensatedAdapter;

    List<Order> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_exception, container, false);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.my_divider));
        init();

        recyclerView_compensate = (SlideRecyclerView) view.findViewById(R.id.recyclerView_compensate);
        recyclerView_compensate.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_compensate.setItemAnimator(new DefaultItemAnimator());
        recyclerView_compensate.addItemDecoration(divider);
        compensateAdapter = new OrderAdapter(getActivity(), mList, true);
        recyclerView_compensate.setAdapter(compensateAdapter);
        compensateAdapter.setOnItemClickListener((adapter, v, position) ->{
            //
            //  订单详情页跳转
            //
            Toast.makeText(getActivity(),"this is order detail!!!     "+ mList.get(position).orderName, Toast.LENGTH_SHORT).show();
        });

        recyclerView_compensated = (SlideRecyclerView) view.findViewById(R.id.recyclerView_compensated);
        recyclerView_compensated.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_compensated.setItemAnimator(new DefaultItemAnimator());
        recyclerView_compensated.addItemDecoration(divider);
        init();
        compensatedAdapter = new OrderAdapter(getActivity(), mList, false);
        recyclerView_compensated.setAdapter(compensatedAdapter);
        compensatedAdapter.setOnItemClickListener((adapter, v, position) ->{
            //
            //  订单详情页跳转
            //
            Toast.makeText(getActivity(),"this is order detail!!!     "+ mList.get(position).orderName, Toast.LENGTH_SHORT).show();
        });
        compensatedAdapter.setOnDeleteClickListener((view1, position) -> {
            mList.remove(position);
            compensatedAdapter.notifyDataSetChanged();
            recyclerView_compensated.closeMenu();
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
