package com.picturecollage.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.picturecollage.R;
import com.picturecollage.activity.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by TrungTV on 02/14/2017.
 */

public class Utils {

    private static final int SIZE_3M = 3145728;
    private static final int SIZE_5M = 5038848;
    private static final int SIZE_8M = 7990272;

    public static int setAddLayout(int id) {
        int resource = 0;
        switch (id) {
            case 0:
                resource = R.layout.frame_00;
                break;
            case 1:
                resource = R.layout.frame_01;
                break;
            case 2:
                resource = R.layout.frame_02;
                break;
            case 3:
                resource = R.layout.frame_03;
                break;
            case 4:
                resource = R.layout.frame_04;
                break;
            case 5:
                resource = R.layout.frame_05;
                break;
            case 6:
                resource = R.layout.frame_06;
                break;
            case 7:
                resource = R.layout.frame_07;
                break;
            case 8:
                resource = R.layout.frame_08;
                break;
            case 9:
                resource = R.layout.frame_09;
                break;
            case 10:
                resource = R.layout.frame_10;
                break;
            case 11:
                resource = R.layout.frame_11;
                break;
            case 12:
                resource = R.layout.frame_12;
                break;
            case 13:
                resource = R.layout.frame_13;
                break;
            case 14:
                resource = R.layout.frame_14;
                break;
            case 15:
                resource = R.layout.frame_15;
                break;
            case 16:
                resource = R.layout.frame_16;
                break;
            case 17:
                resource = R.layout.frame_17;
                break;
            case 18:
                resource = R.layout.frame_18;
                break;
            case 19:
                resource = R.layout.frame_19;
                break;
            case 20:
                resource = R.layout.frame_20;
                break;
            case 21:
                resource = R.layout.frame_21;
                break;
            case 22:
                resource = R.layout.frame_22;
                break;
            case 23:
                resource = R.layout.frame_23;
                break;
            case 24:
                resource = R.layout.frame_24;
                break;
            case 25:
                resource = R.layout.frame_25;
                break;
            case 26:
                resource = R.layout.frame_26;
                break;
            case 27:
                resource = R.layout.frame_27;
                break;
            case 28:
                resource = R.layout.frame_28;
                break;
            case 29:
                resource = R.layout.frame_29;
                break;
            case 30:
                resource = R.layout.frame_30;
                break;
            case 31:
                resource = R.layout.frame_31;
                break;
            case 32:
                resource = R.layout.frame_32;
                break;
            case 33:
                resource = R.layout.frame_33;
                break;
            case 34:
                resource = R.layout.frame_34;
                break;
            case 35:
                resource = R.layout.frame_35;
                break;
            default:
                resource = R.layout.frame_00;
                break;
        }
        return resource;
    }

    public static float calHeight(int ratio, int width) {
        float height = 0;
        switch (ratio) {
            case MainActivity.RATIO_11:
                height = width;
                break;
            case MainActivity.RATIO_34:
                height = (width * 4) / 3;
                break;
            case MainActivity.RATIO_43:
                height = (width * 3) / 4;
                break;
            case MainActivity.RATIO_45:
                height = (width * 5) / 4;
                break;
            case MainActivity.RATIO_23:
                height = (width * 3) / 2;
                break;
            case MainActivity.RATIO_32:
                height = (width * 2) / 3;
                break;
            case MainActivity.RATIO_916:
                height = (width * 16) / 9;
                break;
            case MainActivity.RATIO_169:
                height = (width * 9) / 16;
                break;
            default:
                height = width;
                break;

        }
        return height;
    }

    public static float calHeightForBitmap(int ratio, int width) {
        float height = 0;
        switch (ratio) {
            case MainActivity.RATIO_43:
                height = (width * 3) / 4;
                break;
            case MainActivity.RATIO_32:
                height = (width * 2) / 3;
                break;
            case MainActivity.RATIO_169:
                height = (width * 9) / 16;
                break;
            default:
                height = width;
                break;

        }
        return height;
    }

    public static float calWidthForBitmap(int ratio, int height) {
        float width = 0;
        switch (ratio) {
            case MainActivity.RATIO_34:
                width = (height * 3) / 4;
                break;
            case MainActivity.RATIO_45:
                width = (height * 4) / 5;
                break;
            case MainActivity.RATIO_23:
                width = (height * 2) / 3;
                break;
            case MainActivity.RATIO_916:
                width = (height * 9) / 16;
                break;
            default:
                width = height;
                break;

        }
        return width;
    }

    public static void saveImage(Context context, Bitmap bitmap) {
        String fileName = "PhotoCollage_" + getCurrentDate() + ".jpg";
        if (bitmap != null) {

            File file = new File(Environment.getExternalStorageDirectory() + "/PhotoCollage");
            if (!file.isDirectory()) {
                file.mkdir();
            }
            file = new File(Environment.getExternalStorageDirectory() + "/PhotoCollage", fileName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);

                fileOutputStream.flush();
                fileOutputStream.close();
                bitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION, "PhotoCollage");
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.ImageColumns.BUCKET_ID, file.toString().hashCode());
            values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, file.getName().toString());
            values.put("_data", file.getAbsolutePath());

            ContentResolver cr = context.getContentResolver();
            cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        }
    }

    private static String getCurrentDate() {
        String date = "";
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        date = year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + seconds;
        return date;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        while (SIZE_5M <= bitmap.getWidth() * bitmap.getHeight()) {
            if (Build.VERSION_CODES.JELLY_BEAN >= Build.VERSION.SDK_INT) {
                if (SIZE_8M <= bitmap.getWidth() * bitmap.getHeight()) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.4), (int) (bitmap.getHeight() * 0.4), false);
                } else {
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.6), (int) (bitmap.getHeight() * 0.6), false);
                }
            } else {
                if (SIZE_8M <= bitmap.getWidth() * bitmap.getHeight()) {
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.6), (int) (bitmap.getHeight() * 0.6), false);
                } else {
                    bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * 0.8), (int) (bitmap.getHeight() * 0.8), false);
                }
            }

        }
        return bitmap;
    }
}
