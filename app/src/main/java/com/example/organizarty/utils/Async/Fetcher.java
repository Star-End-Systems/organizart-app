package com.example.organizarty.utils.Async;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

import com.example.organizarty.exceptions.OrganizartyAPIException;

import java.io.IOException;

public class Fetcher {
    public interface ReturnableAsync<T>{
        T run() throws RuntimeException, Exception;
    }

    public interface CallbackAsync<T>{
        void call(T param);
    }

    public interface HandleException{
        void call(Exception param);
    }

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

    public static <T> void async(ReturnableAsync<T> returnable, CallbackAsync<T> callback, HandleException handleException){
        long currentTimeMillis = System.currentTimeMillis();

        String threadName = String.format("Thread_%s", currentTimeMillis);
        HandlerThread thread = new HandlerThread(threadName);
        thread.start();
        Handler handler = new Handler(thread.getLooper());

        handler.post(() -> {
            try {
                callback.call(returnable.run());
            } catch (Exception e){
                handleException.call(e);
            }
            finally {
                thread.quit();
            }
        });
    }


    public static void asyncUI(AppCompatActivity context, Runnable runnable){
        context.runOnUiThread(runnable);
    }
}
