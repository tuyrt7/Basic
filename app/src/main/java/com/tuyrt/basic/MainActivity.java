package com.tuyrt.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tuyrt.basic.ui.permission.PermissionActivity;
import com.tuyrt.tui.widget.WaveView;
import com.tuyrt.tui.widget.button.loading.LoadingButton;

public class MainActivity extends AppCompatActivity {

    private LoadingButton loadingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingBtn = findViewById(R.id.loadingBtn);
        initLoadingButton();
    }

    private void initLoadingButton() {
        loadingBtn.setOnClickListener(v -> {
            loadingBtn.start();
            loadingBtn.postDelayed(() -> loadingBtn.complete(), 3000);
        });
        loadingBtn.cancel();
        //        loadingBtn.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        loadingBtn//.setEnableShrink(true)
                //.setDisableClickOnLoading(true)
                //.setLoadingColor(loadingBtn.getTextColors().getDefaultColor())
                ////.setLoadingStrokeWidth((int) (loadingBtn.getTextSize() * 0.14f))
                .setOnLoadingListener(new LoadingButton.OnLoadingListenerAdapter() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "go go go", Toast.LENGTH_SHORT).show();
                        startWave();
                    }
                });
    }

    void startWave() {
        WaveView mWaveView = findViewById(R.id.wave_view);
         /* mWaveView.setDuration(5000);
        mWaveView.setStyle(Paint.Style.STROKE);
        mWaveView.setSpeed(400);
        mWaveView.setColor(Color.RED);
        mWaveView.setInterpolator(new AccelerateInterpolator(1.2f));
        mWaveView.start();*/

        mWaveView.setDuration(5000);
        mWaveView.setStyle(Paint.Style.FILL);
        mWaveView.setColor(Color.RED);
        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
        mWaveView.start();
        mWaveView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWaveView.stop();
            }
        }, 10000);
    }

    public void goPermission(View view) {
        startActivity(new Intent(this, PermissionActivity.class));

    }
}
