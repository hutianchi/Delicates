package com.htc.delicates.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htc.delicates.Model.Address;
import com.htc.delicates.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> implements View.OnClickListener{

    private List<Address> addressList;

    private Context mContext;

    private OnDeleteClickLister mDeleteClickListener;

    private OnEditClickLister editClickLister;
    private OnItemClickListener mListener;

    public AddressAdapter(Context mContext, List<Address> addressList) {
        this.addressList = addressList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.address_item, viewGroup, false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if (addressList!=null){
            myViewHolder.itemView.setTag(i);
            View view = myViewHolder.address_delete;
            view.setTag(i);
            if (!view.hasOnClickListeners()) {
                view.setOnClickListener(v -> {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                });
            }
            View view1 = myViewHolder.address_edit;
            view1.setTag(i);
            if (!view1.hasOnClickListeners()) {
                view1.setOnClickListener(v -> {
                    if (editClickLister != null) {
                        editClickLister.onEditClick(v, (Integer) v.getTag());
                    }
                });
            }
            Address address = addressList.get(i);
            myViewHolder.address_name.setText(address.userName);
            myViewHolder.address_phone.setText(address.phoneNumber);
            myViewHolder.address_details.setText(address.address);
        }
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(this, v, (Integer) v.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView address_name;
        TextView address_phone;
        TextView address_details;
        TextView address_edit;
        TextView address_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address_name = (TextView) itemView.findViewById(R.id.address_name);
            address_phone = (TextView) itemView.findViewById(R.id.address_phone);
            address_details = (TextView) itemView.findViewById(R.id.address_details);
            address_edit = (TextView) itemView.findViewById(R.id.tv_edit);
            address_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        }

    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }

    public void setOnEditClickListener(OnEditClickLister listener) {
        this.editClickLister = listener;
    }

    public interface OnEditClickLister {
        void onEditClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        //item点击回调
        void onItemClick(RecyclerView.Adapter adapter, View v, int position);

    }
}
