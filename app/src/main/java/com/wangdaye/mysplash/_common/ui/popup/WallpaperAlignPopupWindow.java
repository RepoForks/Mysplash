package com.wangdaye.mysplash._common.ui.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangdaye.mysplash.R;
import com.wangdaye.mysplash._common._basic.MysplashPopupWindow;
import com.wangdaye.mysplash._common.ui.activity.SetWallpaperActivity;
import com.wangdaye.mysplash._common.utils.DisplayUtils;

/**
 * Wallpaper align popup window.
 * */

public class WallpaperAlignPopupWindow extends MysplashPopupWindow
        implements View.OnClickListener {
    // widget
    private OnAlignTypeChangedListener listener;

    // data
    private int valueNow;

    /** <br> life cycle. */

    public WallpaperAlignPopupWindow(Context c, View anchor, int valueNow) {
        super(c);
        this.initialize(c, anchor, valueNow);
    }

    @SuppressLint("InflateParams")
    private void initialize(Context c, View anchor, int valueNow) {
        View v = LayoutInflater.from(c).inflate(R.layout.popup_wallpaper_align, null);
        setContentView(v);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        initData(valueNow);
        initWidget();

        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(10);
        }
        showAsDropDown(anchor, 0, 0, Gravity.CENTER);
    }

    /** <br> UI. */

    private void initWidget() {
        View v = getContentView();

        v.findViewById(R.id.popup_wallpaper_align_left).setOnClickListener(this);
        v.findViewById(R.id.popup_wallpaper_align_center).setOnClickListener(this);
        v.findViewById(R.id.popup_wallpaper_align_right).setOnClickListener(this);

        TextView leftTxt = (TextView) v.findViewById(R.id.popup_wallpaper_align_leftTxt);
        DisplayUtils.setTypeface(v.getContext(), leftTxt);
        if (valueNow == SetWallpaperActivity.ALIGN_TYPE_LEFT) {
            leftTxt.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorTextSubtitle_light));
        }

        TextView centerTxt = (TextView) v.findViewById(R.id.popup_wallpaper_align_centerTxt);
        DisplayUtils.setTypeface(v.getContext(), centerTxt);
        if (valueNow == SetWallpaperActivity.ALIGN_TYPE_CENTER) {
            centerTxt.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorTextSubtitle_light));
        }

        TextView rightTxt = (TextView) v.findViewById(R.id.popup_wallpaper_align_rightTxt);
        DisplayUtils.setTypeface(v.getContext(), rightTxt);
        if (valueNow == SetWallpaperActivity.ALIGN_TYPE_RIGHT) {
            rightTxt.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorTextSubtitle_light));
        }
    }

    /** <br> data. */

    private void initData(int valueNow) {
        this.valueNow = valueNow;
    }

    /** <br> interface. */

    public interface OnAlignTypeChangedListener {
        void onAlignTypeChanged(int type);
    }

    public void setAlignTypeChangedListener(OnAlignTypeChangedListener l) {
        listener = l;
    }

    @Override
    public void onClick(View view) {
        int newValue = valueNow;
        switch (view.getId()) {
            case R.id.popup_wallpaper_align_left:
                newValue = SetWallpaperActivity.ALIGN_TYPE_LEFT;
                break;

            case R.id.popup_wallpaper_align_center:
                newValue = SetWallpaperActivity.ALIGN_TYPE_CENTER;
                break;

            case R.id.popup_wallpaper_align_right:
                newValue = SetWallpaperActivity.ALIGN_TYPE_RIGHT;
                break;
        }

        if (newValue != valueNow && listener != null) {
            listener.onAlignTypeChanged(newValue);
            dismiss();
        }
    }
}