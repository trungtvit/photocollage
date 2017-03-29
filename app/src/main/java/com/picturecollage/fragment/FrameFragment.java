package com.picturecollage.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.picturecollage.R;
import com.picturecollage.activity.CreateCardActivity;
import com.picturecollage.activity.MainActivity;
import com.picturecollage.adpater.FrameAdapter;
import com.picturecollage.model.Frame;

/**
 * Created by TrungDK on 2/13/2017.
 */

@SuppressLint("ValidFragment")
public class FrameFragment extends Fragment {
    private GridView mGridView;
    private FrameAdapter mFrameAdapter;
    private Frame[] frames = {};
    private MainActivity activity;

    public FrameFragment(Frame[] frames, MainActivity activity) {
        this.frames = frames;
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_frame, container, false);
        mGridView = (GridView) view.findViewById(R.id.grvFrame);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (activity != null) {

            mFrameAdapter = new FrameAdapter(activity, frames);
            if (mGridView != null) {
                mGridView.setAdapter(mFrameAdapter);
            }

            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(getActivity(), CreateCardActivity.class);
                    intent.putExtra("ID", frames[position].getId());
                    intent.putExtra("RATIO", activity.getRatio());
                    startActivity(intent);

                }
            });
        }
    }
}
