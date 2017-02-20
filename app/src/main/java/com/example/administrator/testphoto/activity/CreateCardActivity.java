package com.example.administrator.testphoto.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.testphoto.R;
import com.example.administrator.testphoto.customviews.MultiTouchListener;
import com.example.administrator.testphoto.customviews.ScalingImageView;
import com.example.administrator.testphoto.utils.Utils;

/**
 * Created by TrungTV on 02/13/2017.
 */

public class CreateCardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int WIDTH = 4096;

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
    private int widthLayout = 0;
    private int widthBitmap = 0;
    private int heightBitmap = 0;
    private Bitmap bm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.create_photo));

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
                widthLayout = width;
                if (ratio == MainActivity.RATIO_34) {
                    widthLayout = widthLayout - 75;
                }
                if (ratio == MainActivity.RATIO_916) {
                    widthLayout = widthLayout - 180;
                }
                if( ratio == MainActivity.RATIO_23){
                    widthLayout = widthLayout - 150;
                }
                layoutParams = new LinearLayout.LayoutParams(widthLayout, (int) Utils.calHeight(ratio, widthLayout));
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
            case android.R.id.home:
                finish();
                return true;
            case R.id.actionSave:
                new ImageSaver().execute();
                return true;
            case R.id.actionDelete:
                deleteImage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class ImageSaver extends AsyncTask<Void, Void, Void> {
        Context context;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            context = CreateCardActivity.this;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.msg_progress_dialog));
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Utils.saveImage(context, createBitmap(lnFrame));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            bm.recycle();
            Toast.makeText(context, context.getResources().getString(R.string.msg_image_save_success), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void deleteImage() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.title_dialog_delete));
        dialog.setMessage(getResources().getString(R.string.msg_dialog_delete));
        dialog.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                img1.setOnTouchListener(null);
                img1.setImageBitmap(null);
                if (img2 != null) {
                    img2.setOnTouchListener(null);
                    img2.setImageBitmap(null);
                }
                if (img3 != null) {
                    img3.setOnTouchListener(null);
                    img3.setImageBitmap(null);
                }
                if (img4 != null) {
                    img4.setOnTouchListener(null);
                    img4.setImageBitmap(null);
                }
                if (img5 != null) {
                    img5.setOnTouchListener(null);
                    img5.setImageBitmap(null);
                }
                if (img6 != null) {
                    img6.setOnTouchListener(null);
                    img6.setImageBitmap(null);
                }
                if (img7 != null) {
                    img7.setOnTouchListener(null);
                    img7.setImageBitmap(null);
                }
            }
        });
        dialog.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void chooserImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }


    public Bitmap createBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(widthLayout, (int) Utils.calHeight(ratio, widthLayout), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        if (widthLayout > (int) Utils.calHeight(ratio, widthLayout)) {
            widthBitmap = WIDTH;
            heightBitmap = (int) Utils.calHeightForBitmap(ratio, widthBitmap);
        } else if (widthLayout < (int) Utils.calHeight(ratio, widthLayout)) {
            heightBitmap = WIDTH;
            widthBitmap = (int) Utils.calWidthForBitmap(ratio, heightBitmap);
        } else {
            widthBitmap = WIDTH;
            heightBitmap = WIDTH;
        }
        bm = Bitmap.createScaledBitmap(bitmap, widthBitmap, heightBitmap, false);
        return bm;
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
