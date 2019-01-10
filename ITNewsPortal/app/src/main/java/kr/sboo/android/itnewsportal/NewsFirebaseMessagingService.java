package kr.sboo.android.itnewsportal;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class NewsFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        Log.e("Token", s);
    }
}
