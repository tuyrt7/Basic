package com.tuyrt.basic.func.grayscale;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tuyrt.basic.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
  设置整页的灰色调
 */
public class GrayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

    }

    /**
     第一种方案： 替换Activity根布局 android.id.content 对应的FrameLayout为GrayFrameLayout
     把下方代码复制到BaseActivity中，实现所有的页面添加灰色调样式
     还有一些特殊细节问题需要根据实际情况处理：
     -  Activity的window设置background,咋办？
     -  theme中设置windowBackground,就需要从theme中提取drawable
     -  dialog 支持吗？
     -  android.R.id.content失宠了，不是FrameLayout ？
     -
     -  注意 webView和视频播放有些问题 ？
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if ("FrameLayout".equals(name)) {
            int count = attrs.getAttributeCount();
            for (int i = 0; i < count; i++) {
                String attributeName = attrs.getAttributeName(i);
                String attributeValue = attrs.getAttributeValue(i);
                if (attributeName.equals("id")) {
                    int id = Integer.parseInt(attributeValue.substring(1));
                    String idVal = getResources().getResourceName(id);
                    if ("android:id/content".equals(idVal)) {
                        GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
                        //Activity的window设置background,咋办？
                        //grayFrameLayout.setBackgroundDrawable(getWindow().getDecorView().getBackground());
                        //theme中设置windowBackground ？
                        TypedValue a = new TypedValue();
                        getTheme().resolveAttribute(android.R.id.content, a, true);
                        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                            //windowBackground is a color
                            int color = a.data;
                            grayFrameLayout.setBackgroundColor(color);
                        } else {
                            //windowBackground is not a color,probably a drawable
                            Drawable drawable = getResources().getDrawable(a.resourceId);
                            grayFrameLayout.setBackground(drawable);
                        }

                        return grayFrameLayout;
                    }
                }
            }
        }


        return super.onCreateView(name, context, attrs);
    }

    /**
     第二种方案： 通过硬件绘制会调用的API，view.setLayerType(layerType,null);直接在BaseActivity中获取decorView
     对象，然后设置硬件绘制灰色调 （依赖硬件加速，）
     */
    private void setGrayDecorView() {
        //
        Paint mPaint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
        getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE,mPaint);
    }

}
