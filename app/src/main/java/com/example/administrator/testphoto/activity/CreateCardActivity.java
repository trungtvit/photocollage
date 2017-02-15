package com.example.administrator.testphoto.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.administrator.testphoto.R;
import com.example.administrator.testphoto.customviews.MultiTouchListener;
import com.example.administrator.testphoto.customviews.ScalingImageView;
import com.example.administrator.testphoto.utils.Utils;

/**
 * Created by TrungTV on 02/13/2017.
 */

public class CreateCardActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout lnFrame;
    private ScalingImageView img1;
    private ScalingImageView img2;
    private ScalingImageView img3;
    private ScalingImageView img4;
    private ScalingImageView img5;
    private ScalingImageView img6;
    private ScalingImageView img7;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout mainLayout;
    private LinearLayout addLayout;

    private boolean isImg1 = false;
    private boolean isImg2 = false;
    private boolean isImg3 = false;
    private boolean isImg4 = false;
    private boolean isImg5 = false;
    private boolean isImg6 = false;
    private boolean isImg7 = false;
    private int width = 0;
    private int ratio = 0;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        id = getIntent().getIntExtra("ID", 0);
        ratio = getIntent().getIntExtra("RATIO", 0);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        addLayout = (LinearLayout) View.inflate(this, Utils.setAddLayout(id), null);
        mainLayout.addView(addLayout);

        lnFrame = (LinearLayout) findViewById(R.id.lnFrame);
        img1 = (ScalingImageView) findViewById(R.id.img1);
        img2 = (ScalingImageView) findViewById(R.id.img2);
        img3 = (ScalingImageView) findViewById(R.id.img3);
        img4 = (ScalingImageView) findViewById(R.id.img4);
        img5 = (ScalingImageView) findViewById(R.id.img5);
        img6 = (ScalingImageView) findViewById(R.id.img6);
        img7 = (ScalingImageView) findViewById(R.id.img7);

        img1.setOnClickListener(this);
        if (img2 != null)
            img2.setOnClickListener(this);
        if (img3 != null)
            img3.setOnClickListener(this);
        if (img4 != null)
            img4.setOnClickListener(this);
        if (img5 != null)
            img5.setOnClickListener(this);
        if (img6 != null)
            img6.setOnClickListener(this);
        if (img7 != null)
            img7.setOnClickListener(this);


        ViewTreeObserver observer = lnFrame.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = lnFrame.getWidth();
                layoutParams = new LinearLayout.LayoutParams(width, (int) Utils.calHeight(ratio, width));
                lnFrame.setLayoutParams(layoutParams);
                lnFrame.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSave:
                Utils.saveImage(this,createBitmap(lnFrame));
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void chooserImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }


    public Bitmap createBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(width, (int) Utils.calHeight(ratio, width), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img1:
                chooserImage();
                isImg1 = true;
                isImg2 = false;
                isImg3 = false;
                isImg4 = false;
                isImg5 = false;
                isImg6 = false;
                isImg7 = false;
                break;
            case R.id.img2:
                chooserImage();
                isImg1 = false;
                isImg2 = true;
                isImg3 = false;
                isImg4 = false;
                isImg5 = false;
                isImg6 = false;
                isImg7 = false;
                break;
            case R.id.img3:
                chooserImage();
                isImg1 = false;
                isImg2 = false;
                isImg3 = true;
                isImg4 = false;
                isImg5 = false;
                isImg6 = false;
                isImg7 = false;
                break;
            case R.id.img4:
                chooserImage();
                isImg1 = false;
                isImg2 = false;
                isImg3 = false;
                isImg4 = true;
                isImg5 = false;
                isImg6 = false;
                isImg7 = false;
                break;
            case R.id.img5:
                chooserImage();
                isImg1 = false;
                isImg2 = false;
                isImg3 = false;
                isImg4 = false;
                isImg5 = true;
                isImg6 = false;
                isImg7 = false;
                break;
            case R.id.img6:
                chooserImage();
                isImg1 = false;
                isImg2 = false;
                isImg3 = false;
                isImg4 = false;
                isImg5 = false;
                isImg6 = true;
                isImg7 = false;
                break;
            case R.id.img7:
                chooserImage();
                isImg1 = false;
                isImg2 = false;
                isImg3 = false;
                isImg4 = false;
                isImg5 = false;
                isImg6 = false;
                isImg7 = true;
                break;
            default:
                /*TODO*/
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                if (data != null) {
                    Uri uri = data.getData();
                    try {
                        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        if (isImg1) {
                            img1.setImageBitmap(bm);
                            isImg1 = false;
                            img1.setOnTouchListener(new MultiTouchListener());
                            bm = null;
                        }
                        if (isImg2) {
                            img2.setImageBitmap(bm);
                            isImg2 = false;
                            img2.setOnTouchListener(new MultiTouchListener());
                            bm = null;
                        }
                        if (isImg3) {
                            img3.setImageBitmap(bm);
                            img3.setOnTouchListener(new MultiTouchListener());
                            isImg3 = false;
                            bm = null;
                        }
                        if (isImg4) {
                            img4.setImageBitmap(bm);
                            img4.setOnTouchListener(new MultiTouchListener());
                            isImg4 = false;
                            bm = null;
                        }
                        if (isImg5) {
                            img5.setImageBitmap(bm);
                            img5.setOnTouchListener(new MultiTouchListener());
                            isImg5 = false;
                            bm = null;
                        }
                        if (isImg6) {
                            img6.setImageBitmap(bm);
                            img6.setOnTouchListener(new MultiTouchListener());
                            isImg6 = false;
                            bm = null;
                        }
                        if (isImg7) {
                            img7.setImageBitmap(bm);
                            img7.setOnTouchListener(new MultiTouchListener());
                            isImg7 = false;
                            bm = null;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
