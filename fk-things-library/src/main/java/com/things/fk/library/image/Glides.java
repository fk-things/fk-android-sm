package com.things.fk.library.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * TODO 自定义的View加载
 *
 * @author tic
 *         created on 18-3-28
 */

public class Glides implements ImageLoader {

    @Override
    public void load(Context context, ImageView imageView, String url, @DrawableRes int placeHolder, @DrawableRes int error) {
        RequestManager requestManager = Glide.with(context);
        load(requestManager, imageView, url, placeHolder, error);
    }

    public void load(Fragment fragment, ImageView imageView, String url, @DrawableRes int placeHolder, @DrawableRes int error) {
        RequestManager requestManager = Glide.with(fragment);
        load(requestManager, imageView, url, placeHolder, error);
    }

    private void load(RequestManager requestManager, ImageView imageView, String url, @DrawableRes int placeHolder, @DrawableRes int error) {
        requestManager.load(url)
                .placeholder(placeHolder)
                .error(error)
                .into(imageView);
    }
}
