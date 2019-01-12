package kr.sboo.android.itnewsportal;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;

public class TransitionActivityBase extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            getWindow().setExitTransition(new Slide(Gravity.RIGHT));
            getWindow().setEnterTransition(new Slide(Gravity.LEFT));
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            super.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else{
            super.startActivity(intent);
        }
    }
}
