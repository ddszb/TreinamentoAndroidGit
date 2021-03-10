package com.danielzambrano.hubrepofinder.asynctask;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final RunListener<T> runListener;
    private final EndListener<T> endListener;

    public BaseAsyncTask(RunListener<T> runListener,
                         EndListener<T> endListener) {
        this.runListener = runListener;
        this.endListener = endListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return runListener.whenRun();
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        endListener.whenEnd(result);
    }

    public interface RunListener<T> {
        T whenRun();
    }

    public interface EndListener<T> {
        void whenEnd(T result);
    }

}