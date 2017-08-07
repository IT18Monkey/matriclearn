package com.whh.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

/**
 * Created by whh on 2017/8/7.
 * Timers
 * 作用：histogram和meter的组合体
 */
public class TimerTest {
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        Timer timer = registry.timer(MetricRegistry.name(TimerTest.class, "get-latency"));
        Timer.Context ctx = timer.time();

        try {
            Thread.sleep(2000);
        } finally {
            ctx.stop();
        }
    }
}
