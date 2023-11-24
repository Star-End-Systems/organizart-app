package com.example.organizarty.utils.Async;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

public class Fetcher {
    public static void async(Runnable runnable){
        long currentTimeMillis = System.currentTimeMillis();

        String threadName = String.format("Thread_%s", currentTimeMillis);
        HandlerThread thread = new HandlerThread(threadName);
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                runnable.run();
            }finally {
                thread.quit();
            }
        });
    }

    public static void asyncUI(AppCompatActivity context, Runnable runnable){
        context.runOnUiThread(runnable);
    }
}
