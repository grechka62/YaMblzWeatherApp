package me.grechka.yamblz.yamblzweatherapp.events;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by alexander on 08.04.17.
 */

public class StopTypingDetector implements TextWatcher {

    private static final String TAG = StopTypingDetector.class.getCanonicalName();

    public interface TypingListener {
        void onStopTyping();
    }

    private long delay = 750;
    private long lastTextEdit = 0;

    private Handler handler;
    private TypingListener typingListener;

    public StopTypingDetector(Handler handler, TypingListener typingListener) {
        this.handler = handler;
        this.typingListener = typingListener;
    }

    private Runnable finishTypingRunnable = new Runnable() {
        public void run() {
            if (System.currentTimeMillis() <= (lastTextEdit + delay - 500)) return;
            typingListener.onStopTyping();
        }
    };

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        handler.removeCallbacks(finishTypingRunnable);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() <= 0) return;
        subscribeDetector();
    }

    private void subscribeDetector() {
        lastTextEdit = System.currentTimeMillis();
        handler.postDelayed(finishTypingRunnable, delay);
    }
}
