package com.picturecollage.adpater;

/**
 * Created by TrungDK on 2/13/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.picturecollage.R;
import com.picturecollage.model.Frame;

public class FrameAdapter extends BaseAdapter {

    Context context;

    public class ViewHolder {
        public ImageView imageView;
    }

    private Frame[] items;
    private LayoutInflater mInflater;

    public FrameAdapter(Context context, Frame[] locations) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        items = locations;

    }

    public Frame[] getItems() {
        return items;
    }

    public void setItems(Frame[] items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.length;
        }
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        if (items != null && position >= 0 && position < getCount()) {
            return items[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (items != null && position >= 0 && position < getCount()) {
            return items[position].getId();
        }
        return 0;
    }

    public void setItemsList(Frame[] locations) {
        this.items = locations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {

            view = mInflater.inflate(R.layout.item_frame, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view
                    .findViewById(R.id.imgFrame);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Frame frame = items[position];
        viewHolder.imageView.setImageResource(frame.getFrameImage());
        return view;
    }

}
