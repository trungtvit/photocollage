package com.example.administrator.testphoto.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.administrator.testphoto.R;
import com.example.administrator.testphoto.activity.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by TrungTV on 02/14/2017.
 */

public class Utils {
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
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, fileName);
            values.put(MediaStore.Images.Media.DESCRIPTION, "PhotoCollage");
            values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(MediaStore.Images.ImageColumns.BUCKET_ID, file.toString().toLowerCase(Locale.US).hashCode());
            values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, file.getName().toLowerCase(Locale.US));
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
}
