package com.example.administrator.testphoto.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.testphoto.R;
import com.example.administrator.testphoto.fragment.FrameFragment;
import com.example.administrator.testphoto.model.Frame;
import com.viewpagerindicator.PageIndicator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RATIO_11 = 11;
    public static final int RATIO_23 = 23;
    public static final int RATIO_32 = 32;
    public static final int RATIO_34 = 34;
    public static final int RATIO_43 = 43;
    public static final int RATIO_45 = 45;
    public static final int RATIO_916 = 916;
    public static final int RATIO_169 = 169;

    private TextView tvRatio;
    public PageIndicator mIndicator;
    private ViewPager mViewPager;
    private PagerAdapter pagerAdapter;
    File[] allFiles;

    private int ratio = 0;
    int images[] = {R.drawable.frame_00, R.drawable.frame_01,
            R.drawable.frame_02, R.drawable.frame_03, R.drawable.frame_04,
            R.drawable.frame_05, R.drawable.frame_06, R.drawable.frame_07,
            R.drawable.frame_08, R.drawable.frame_09, R.drawable.frame_10,
            R.drawable.frame_11, R.drawable.frame_12, R.drawable.frame_13,
            R.drawable.frame_14, R.drawable.frame_15, R.drawable.frame_16,
            R.drawable.frame_17};
    private int id[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        File folder = new File(Environment.getExternalStorageDirectory().getPath() + "/PhotoCollage/");
        allFiles = folder.listFiles();
        if (allFiles != null) {
            if (allFiles.length > 0) {
                new SingleMediaScanner(this, allFiles[0]);
            }
        }

        tvRatio = (TextView) findViewById(R.id.tvRatio);
        mIndicator = (PageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        tvRatio.setOnClickListener(this);

        setRatio(RATIO_11);

        List<FrameFragment> frameFragments = new ArrayList<FrameFragment>();
        ArrayList<Integer> listId = new ArrayList<Integer>();
        ArrayList<Integer> listImage = new ArrayList<Integer>();

        for (int i = 0; i < id.length; i++) {
            listId.add(i, id[i]);
            listImage.add(i, images[i]);
        }
        Iterator<Integer> itId = listId.iterator();
        Iterator<Integer> itImage = listImage.iterator();
        while (itId.hasNext()) {
            ArrayList<Frame> imLst = new ArrayList<Frame>();

            Frame itm = new Frame(itId.next(), itImage.next());
            imLst.add(itm);

            if (itId.hasNext()) {
                Frame itm1 = new Frame(itId.next(), itImage.next());
                imLst.add(itm1);
            }

            if (itId.hasNext()) {
                Frame itm2 = new Frame(itId.next(), itImage.next());
                imLst.add(itm2);
            }

            if (itId.hasNext()) {
                Frame itm3 = new Frame(itId.next(), itImage.next());
                imLst.add(itm3);
            }

            if (itId.hasNext()) {
                Frame itm4 = new Frame(itId.next(), itImage.next());
                imLst.add(itm4);
            }

            if (itId.hasNext()) {
                Frame itm5 = new Frame(itId.next(), itImage.next());
                imLst.add(itm5);
            }

            if (itId.hasNext()) {
                Frame itm6 = new Frame(itId.next(), itImage.next());
                imLst.add(itm6);
            }

            if (itId.hasNext()) {
                Frame itm7 = new Frame(itId.next(), itImage.next());
                imLst.add(itm7);
            }

            if (itId.hasNext()) {
                Frame itm8 = new Frame(itId.next(), itImage.next());
                imLst.add(itm8);
            }

            Frame[] gp = {};
            Frame[] gridPage = imLst.toArray(gp);
            frameFragments.add(new FrameFragment(gridPage, MainActivity.this));
        }

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), frameFragments);
        mViewPager.setAdapter(pagerAdapter);
        mIndicator.setViewPager(mViewPager);
    }

    public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

        private MediaScannerConnection mMs;
        private File mFile;

        public SingleMediaScanner(Context context, File f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }

        public void onMediaScannerConnected() {
            mMs.scanFile(mFile.getAbsolutePath(), null);
        }

        public void onScanCompleted(String path, Uri uri) {
            mMs.disconnect();
        }

    }

    private void showDialogFrame() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_ratio);
        dialog.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        dialog.getWindow().setDimAmount(0.0f);
        final TextView tvRatio11 = (TextView) dialog.findViewById(R.id.tvRatio11);
        final TextView tvRatio43 = (TextView) dialog.findViewById(R.id.tvRatio43);
        final TextView tvRatio34 = (TextView) dialog.findViewById(R.id.tvRatio34);
        final TextView tvRatio45 = (TextView) dialog.findViewById(R.id.tvRatio45);
        final TextView tvRatio32 = (TextView) dialog.findViewById(R.id.tvRatio32);
        final TextView tvRatio23 = (TextView) dialog.findViewById(R.id.tvRatio23);
        final TextView tvRatio916 = (TextView) dialog.findViewById(R.id.tvRatio916);
        final TextView tvRatio169 = (TextView) dialog.findViewById(R.id.tvRatio169);

        if (getRatio() == RATIO_11 || ratio == 0) {
            tvRatio11.setSelected(true);
        }
        if (getRatio() == RATIO_43) {
            tvRatio43.setSelected(true);
        }
        if (getRatio() == RATIO_34) {
            tvRatio34.setSelected(true);
        }
        if (getRatio() == RATIO_45) {
            tvRatio45.setSelected(true);
        }
        if (getRatio() == RATIO_32) {
            tvRatio32.setSelected(true);
        }
        if (getRatio() == RATIO_23) {
            tvRatio23.setSelected(true);
        }
        if (getRatio() == RATIO_916) {
            tvRatio916.setSelected(true);
        }
        if (getRatio() == RATIO_169) {
            tvRatio169.setSelected(true);
        }

        tvRatio11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_11);
                tvRatio11.setSelected(true);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("1x1");
                dialog.dismiss();
            }
        });
        tvRatio43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_43);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(true);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("4x3");
                dialog.dismiss();
            }
        });
        tvRatio34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_34);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(true);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("3x4");
                dialog.dismiss();
            }
        });
        tvRatio45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_45);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(true);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("4x5");
                dialog.dismiss();
            }
        });
        tvRatio32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_32);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(true);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("3x2");
                dialog.dismiss();
            }
        });
        tvRatio23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_23);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(true);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(false);
                tvRatio.setText("2x3");
                dialog.dismiss();
            }
        });
        tvRatio916.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_916);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(true);
                tvRatio169.setSelected(false);
                tvRatio.setText("9x16");
                dialog.dismiss();
            }
        });
        tvRatio169.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRatio(RATIO_169);
                tvRatio11.setSelected(false);
                tvRatio43.setSelected(false);
                tvRatio34.setSelected(false);
                tvRatio45.setSelected(false);
                tvRatio32.setSelected(false);
                tvRatio23.setSelected(false);
                tvRatio916.setSelected(false);
                tvRatio169.setSelected(true);
                tvRatio.setText("16x9");
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRatio:
                showDialogFrame();
                break;
            default:
                /*TODO*/
                break;
        }
    }

    public class PageAdapter extends FragmentStatePagerAdapter {
        private List<FrameFragment> fragments;

        public PageAdapter(FragmentManager fm, List<FrameFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

}
