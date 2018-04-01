package com.things.fk.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Intent 工具类
 */
public class Intents {

    public static Intent getIntentByUri(String uri) {
        Intent intent = null;
        try {
            intent = Intent.parseUri(uri, 0);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return intent;
    }

    public static Intent openFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        String filename = file.getName();
        int lastPointIndex = filename.lastIndexOf(".");
        String suffix = filename.substring(lastPointIndex + 1);
        if (suffix.equals("m4a") || suffix.equals("mp3") || suffix.equals("mid")
                || suffix.equals("xmf") || suffix.equals("ogg") || suffix.equals("wav") || suffix.equals("amr")) {
            return getAudioFileIntent(file);
        } else if (suffix.equals("3gp") || suffix.equals("mp4")) {
            return getVideoFileIntent(file);
        } else if (suffix.equals("jpg") || suffix.equals("gif") || suffix.equals("png")
                || suffix.equals("jpeg") || suffix.equals("bmp")) {
            return getImageFileIntent(file);
        } else if (suffix.equals("apk")) {
            return getApkFileIntent(file);
        } else if (suffix.equals("ppt")) {
            return getPptFileIntent(file);
        } else if (suffix.equals("xls")) {
            return getExcelFileIntent(file);
        } else if (suffix.equals("doc")) {
            return getWordFileIntent(file);
        } else if (suffix.equals("pdf")) {
            return getPdfFileIntent(file);
        } else if (suffix.equals("chm")) {
            return getChmFileIntent(file);
        } else if (suffix.equals("txt")) {
            return getTextFileIntent(file);
        } else {
            return getAllIntent(file);
        }
    }

    public static Intent getHtmlFileIntent(File file) {
        Uri uri = Uri.parse(file.toString()).buildUpon()
                .encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(file.toString()).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    public static Intent getImageFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "image/*");

        return intent;

    }

    public static Intent getPdfFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "application/pdf");

        return intent;

    }

    public static Intent getPptFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    public static Intent getTextFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "text/plain");

        return intent;

    }

    public static Intent getAudioFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("oneshot", 0);

        intent.putExtra("configchange", 0);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "audio/*");

        return intent;

    }

    public static Intent getVideoFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("oneshot", 0);

        intent.putExtra("configchange", 0);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "video/*");

        return intent;

    }

    public static Intent getChmFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "application/x-chm");

        return intent;

    }

    public static Intent getWordFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "application/msword");

        return intent;

    }

    public static Intent getExcelFileIntent(File file) {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "application/vnd.ms-excel");

        return intent;

    }

    public static Intent getPPTFileIntent(File file)

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(file);

        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");

        return intent;

    }

    public static Intent getApkFileIntent(File file) {

        Intent intent = new Intent();

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setAction(Intent.ACTION_VIEW);

        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");

        return intent;

    }

    public static Intent getAllIntent(File file) {

        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    public static void openImageByPath(Context mContext, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(path)), "image/*");
        mContext.startActivity(intent);
    }

    /**
     * Share a page.
     *
     * @param context The parent activity.
     * @param title   The page title.
     * @param text,   The page url.
     */
    public static void sharePage(Context context, String title, String text, String viaTitle) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, title);

        try {
            context.startActivity(Intent.createChooser(shareIntent, viaTitle));
        } catch (android.content.ActivityNotFoundException ex) {
            // if no app handles it, do nothing
        }
    }

}
