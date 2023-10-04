package com.example.pet2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ThuCungAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ThuCung> list;
    public ThuCungAdapter(Context context, int layout, List<ThuCung> list ) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView txtTenTT,txtGiongLoai,txtTrongLuong,txtMauLong;
        ImageView imgHinhThuCung;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTenTT = (TextView) view.findViewById(R.id.txtTenTT);
            holder.txtGiongLoai = (TextView) view.findViewById(R.id.txtGiongLoai);
            holder.txtTrongLuong = (TextView) view.findViewById(R.id.txtTrongLuong);
            holder.txtMauLong = (TextView) view.findViewById(R.id.txtMauLong);
            holder.imgHinhThuCung = (ImageView) view.findViewById(R.id.imgThuCung);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }
        ThuCung thuCung = list.get(i);
        holder.txtTenTT.setText(thuCung.getTenTT());
        holder.txtGiongLoai.setText(thuCung.getGiongLoai());
        holder.txtTrongLuong.setText(thuCung.getTrongLuong());
        holder.txtMauLong.setText(thuCung.getMauLong());
        //chuyen mang byte[] sang kieu bitmap
        byte[] hinhAnh = thuCung.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        holder.imgHinhThuCung.setImageBitmap(bitmap);

        return view;
    }
}
