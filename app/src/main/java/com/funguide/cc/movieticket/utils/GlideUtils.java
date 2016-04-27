package com.funguide.cc.movieticket.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.funguide.cc.movieticket.R;

import java.io.File;

/**
 * Created by tom on 2016/4/13.
 */
public class GlideUtils {
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";
    /**
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImag(Context context,String url,ImageView imageView){
        Glide.with(context).load(url)
//                .asBitmap()//加载图片
//                .asGif()//加载gif动画
//                .skipMemoryCache(true)//是否跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)//
//        DiskCacheStrategy.NONE caches nothing, as discussed
//        DiskCacheStrategy.SOURCE caches only the original full-resolution image. In our example above that would be the 1000x1000 pixel one
//        DiskCacheStrategy.RESULT caches only the final image, after reducing the resolution (and possibly transformations)
//        DiskCacheStrategy.ALL caches all versions of the image (default behavior)
                .priority(Priority.HIGH)//图片加载的优先级
//        Priority.LOW
//        Priority.NORMAL
//        Priority.HIGH
//        Priority.IMMEDIATE
//                .thumbnail();//缩略图
                .listener(new RequestListener<String, GlideDrawable>() {//监听器
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        // todo log exception

                        // important to return false so the error placeholder can be placed
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                //变换的库 https://github.com/wasabeef/glide-transformations
//                .transform()

//                .placeholder( R.mipmap.ic_launcher)//can also be a drawable
//                .error(R.mipmap.ic_launcher)     // will be displayed if the image cannot be loaded
//                .dontAnimate()
//                .override(600,200)//展示图片的大小
//                .fitCenter() //把图片按比例扩大/缩小到View的宽度，居中显示
//                .centerCrop()//按比例扩大图片的size居中显示，使得图片长(宽)等于或大于View的长(宽)
                .into(imageView);
    }
    public static void loadImagByUrl(Context context,String url, ImageView imageView){
        Glide.with(context).load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }
    public static void loadImagByFile(Context context, File file, ImageView imageView){
        Glide.with(context).load(file).into(imageView);
    }
    public static void loadImagByResource(Context context, int resourceId, ImageView imageView){
        Uri uri = resourceIdToUri(context, resourceId);
        Glide.with(context).load(uri).into(imageView);
        Glide.with(context).load(uri).into(imageView);
    }
    private static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
