package com.things.fk.library.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * @author tic
 *         created on 18-3-28
 */

public interface ImageLoader {

    /**
     * 加载图片资源接口
     *
     * @param imageView   imageview
     * @param url         picture url
     * @param placeHolder default picture
     */
    void load(Context context, ImageView imageView, String url, @DrawableRes int placeHolder, @DrawableRes int error);

}
