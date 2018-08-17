package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Pair;

import com.udacity.gradle.jokes.Joker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private CountDownLatch mLatch;

    @Test
    public void testBackend() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        EventBus.getDefault().register(this);
        mLatch = new CountDownLatch(1);
        new EndpointsAsyncTask().execute(new Pair<>(appContext, "Test Joke"));
        try {
            mLatch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String joke) {
        EventBus.getDefault().unregister(this);
        mLatch.countDown();
        if (joke.equals("101")) {
            Assert.fail();
        }
    }
}
