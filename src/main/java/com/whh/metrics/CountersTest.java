package com.whh.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by whh on 2017/8/7.
 * counter:
 * 作用：计数器（用gauge封装了AtomicLong）
 */
public class CountersTest {
    public static Queue<String> queue = new LinkedBlockingQueue<>();
    public static Counter counter;//计算queue的大小

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
        counter = registry.counter(MetricRegistry.name(CountersTest.class, "queue", "size"));

        int num = 0;
        while (true) {
            if (num < 10) {
                queue.add("job - " + num);
                counter.inc();
            } else if (num > 10 && num < 16) {
                queue.poll();
                counter.dec();
            } else {
                queue.add("job - " + num);
                counter.inc();
            }
            num++;
            Thread.sleep(500);
        }
    }
}
