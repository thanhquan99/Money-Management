package com.example.quanlichitieu1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlichitieu1.R;
import com.example.quanlichitieu1.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private List<Contact> arrayContact;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,parent,false);
            viewHolder.imgHangMuc = convertView.findViewById(R.id.img_hang_muc);
            viewHolder.tvTien = convertView.findViewById(R.id.tv_tien);
            viewHolder.tvHangMuc = convertView.findViewById(R.id.tv_hangmuc);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Contact contact = arrayContact.get(position);
        viewHolder.tvHangMuc.setText(contact.getmHangMuc());
        viewHolder.tvTime.setText(contact.getmTime());
        viewHolder.tvTien.setText(contact.getmTien());
        switch (contact.getmViTriHangMuc()){
            case 0:
                viewHolder.imgHangMuc.setBackgroundResource(R.drawable.ic_launcher_foreground);
                break;
            case 1:
                viewHolder.imgHangMuc.setBackgroundResource(R.drawable.ic_launcher_background);
                break;
            case 2:
                viewHolder.imgHangMuc.setBackgroundResource(R.mipmap.ic_launcher);
                break;
        }

        return  convertView;
    }

    public class ViewHolder{
        ImageView imgHangMuc;
        TextView tvTien,tvHangMuc,tvTime;
    }



}
