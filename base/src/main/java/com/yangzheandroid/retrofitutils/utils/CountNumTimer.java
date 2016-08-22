package com.yangzheandroid.retrofitutils.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

/**
 * 解决：onTick中调用cancel方法无效
 */
public abstract class CountNumTimer {

    /**
     * Millis since epoch when alarm should stop.
     */
    private final long mMillisInFuture;

    /**
     * The interval in millis that the user receives callbacks
     */
    private final long mCountdownInterval;

    private long mStopTimeInFuture;

    /**
     * boolean representing if the timer was cancelled
     */
    private boolean mCancelled = false;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountNumTimer(long millisInFuture, long countDownInterval) {
        mMillisInFuture = millisInFuture;
        mCountdownInterval = countDownInterval;
    }

    /**
     * Cancel the countdown.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    /**
     * Start the countdown.
     */
    public synchronized final CountNumTimer start() {
        mCancelled = false;
        if (mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        mHandler.sendMessage(mHandler.obtainMessage(MSG));
        return this;
    }


    /**
     * Callback fired on regular interval.
     *
     * @param millisUntilFinished The amount of time until finished.
     */
    public abstract void onTick(long millisUntilFinished);

    /**
     * Callback fired when the time is up.
     */
    public abstract void onFinish();


    private static final int MSG = 1;


    /**
     * 解决Handler内存泄漏
     */
    private final MyHandler mHandler = new MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<CountNumTimer> mCountNumTimer;

        public MyHandler(CountNumTimer countNumTimer) {
            mCountNumTimer = new WeakReference<CountNumTimer>(countNumTimer);
        }

        @Override
        public void handleMessage(final Message message) {

            synchronized (CountNumTimer.this) {

                if (mCountNumTimer.get() != null) {

                    if (mCancelled) {
                        return;
                    }

                    final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();

                    if (millisLeft <= 0) {
                        onFinish();
                    } else if (millisLeft < mCountdownInterval) {
                        // no tick, just delay until done
                        sendMessageDelayed(obtainMessage(MSG), millisLeft);
                    } else {
                        long lastTickStart = SystemClock.elapsedRealtime();
                        onTick(millisLeft);

                        // take into account user's onTick taking time to execute
                        long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                        // special case: user's onTick took more than interval to
                        // complete, skip to next interval
                        while (delay < 0) delay += mCountdownInterval;

                        sendMessageDelayed(obtainMessage(MSG), delay);
                    }
                }
            }
        }
    }


//    // handles counting down
//    private Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            synchronized (CountNumTimer.this) {
//                if (mCancelled) {
//                    return;
//                }
//
//                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
//
//                if (millisLeft <= 0) {
//                    onFinish();
//                } else if (millisLeft < mCountdownInterval) {
//                    // no tick, just delay until done
//                    sendMessageDelayed(obtainMessage(MSG), millisLeft);
//                } else {
//                    long lastTickStart = SystemClock.elapsedRealtime();
//                    onTick(millisLeft);
//
//                    // take into account user's onTick taking time to execute
//                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();
//
//                    // special case: user's onTick took more than interval to
//                    // complete, skip to next interval
//                    while (delay < 0) delay += mCountdownInterval;
//
//                    sendMessageDelayed(obtainMessage(MSG), delay);
//                }
//            }
//        }
//    };
}