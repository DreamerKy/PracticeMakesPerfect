package com.example.dreams;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AnimationSet;
import android.widget.Button;

import com.example.dreams.widget.FlipView;

import androidx.appcompat.app.AppCompatActivity;

public class FlipViewActivity extends AppCompatActivity {

    private FlipView flipView;
    private Button button;
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_view);
        flipView = findViewById(R.id.myFlipView);
        button = findViewById(R.id.btn_play);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animatorSet != null && !animatorSet.isRunning()){
                    animatorSet.start();
                }
            }
        });

        playAnimator();
    }

    private void playAnimator() {
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(flipView, "bottomFlip", 45);
        bottomFlipAnimator.setDuration(800);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(flipView, "flipRotation", 720);
        flipRotationAnimator.setDuration(3000);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(flipView, "topFlip", -45);
        topFlipAnimator.setDuration(800);

        ObjectAnimator bottomFlipAnimator2 = ObjectAnimator.ofFloat(flipView, "bottomFlip", 0);
        bottomFlipAnimator.setDuration(800);

        ObjectAnimator topFlipAnimator2 = ObjectAnimator.ofFloat(flipView, "topFlip", 0);
        topFlipAnimator.setDuration(800);

        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator, bottomFlipAnimator2, topFlipAnimator2);
        animatorSet.setStartDelay(500);
        animatorSet.start();
    }
}
