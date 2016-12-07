package com.linchuan.textandgraphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.linchuan.textandgraphics.ImageViewPager.ImagePagerActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 图文布局，该布局是用LinearLayout布局的
 * Created by 林川 on 2016/12/6.
 */

public class TextAndGraphicsView extends LinearLayout {

    private Context context;
    private String[] mData;
    private final int PADDING_SIZE = 20;//边距
    private final int IMAGE_MAX_HEIGHT = 1000;//图片最大的高度限制
    private ImagePagerActivity.ImageSize imageSize;
    private List<String> ListPhotos;
    private int screenWidth;//屏幕宽度

    public TextAndGraphicsView(Context context, String[] mData) {
        super(context);
        this.setOrientation(VERTICAL);
        this.context = context;
        this.mData = mData;
        if (mData == null || mData.length == 0) {
            mData = new String[1];
        }
        ListPhotos = new ArrayList<>();
        imageSize = new ImagePagerActivity.ImageSize(50, 50);
        screenWidth = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        initView();
    }

    private void initView() {

        //根据数据动态创建TextView和ImageView并添加点击事件和属性
        for (int i = 0; i < mData.length; i++) {
            if (mData[i].contains(DataUtil.TEXT_TAG)) {
                TextView tv = new TextView(context);
                tv.setText(mData[i].replace(DataUtil.TEXT_TAG, ""));
                tv.setGravity(Gravity.CENTER_VERTICAL);
                tv.setIncludeFontPadding(false);
                tv.setPadding(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);
                tv.setTextSize(17);
                tv.setLineSpacing(10, 1.2f);// 参数：1、行间距 2、倍数
                tv.setTextColor(Color.BLACK);
                addView(tv);
            } else if (mData[i].contains(DataUtil.IMAGE_NET_TAG)) {
                final ImageView iv = new ImageView(context);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setPadding(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);

                final String url = mData[i].replace(DataUtil.IMAGE_NET_TAG, "");
                ListPhotos.add(url);

                //使用这个需要在gradle中添加Glide库
                Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(Color.WHITE).into(new GlideDrawableImageViewTarget(iv) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        int maxHeight = dp2px(context, IMAGE_MAX_HEIGHT);
                        int height = (int) ((float) iv.getWidth() / drawable.getMinimumWidth() * drawable.getMinimumHeight());
                        if (height > maxHeight) height = maxHeight;
                        iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));
                    }
                });

                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageSize = new ImagePagerActivity.ImageSize(50, 50);
                        ImagePagerActivity.startImagePagerActivity(context, ListPhotos, getUrlPosition(url), imageSize);
                    }
                });
                addView(iv);
            } else if (mData[i].contains(DataUtil.IMAGE_LOCAL_TAG)) {
                final ImageView iv = new ImageView(context);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setPadding(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE);

                final String localPath = mData[i].replace(DataUtil.IMAGE_LOCAL_TAG, "");
                ListPhotos.add(localPath);

                InputStream is = null;
                try {
                    is = context.getAssets().open(localPath.replace("file:///android_asset/",""));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (is == null) return;

                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                iv.setImageBitmap(bitmap);
                Drawable drawable = new BitmapDrawable(bitmap);

                int maxHeight = dp2px(context, IMAGE_MAX_HEIGHT);
                int height = (int) ((float) screenWidth / drawable.getMinimumWidth() * drawable.getMinimumHeight());
                if (height > maxHeight) height = maxHeight;
                iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));

                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImagePagerActivity.startImagePagerActivity(context, ListPhotos, getUrlPosition(localPath), imageSize);
                    }
                });
                addView(iv);
            } else {
                //扩展包含其他类型
            }
        }
    }

    //返回url在ListPhotos中的下标位置
    private int getUrlPosition(String url) {
        for (int i = 0; i < ListPhotos.size(); i++) {
            if (url.equals(ListPhotos.get(i))) {
                return i;
            }
        }
        return -1;
    }

    //dp转为像素
    private int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
