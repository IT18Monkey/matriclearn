package com.whh.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by whh on 2017/8/6.
 * Gauge
 * 作用：只返回一个简单值（一个瞬时值）
 */
public class GaugesTest {
    public static Queue<String> queue = new LinkedList<>();//队列

    public static void main(String[] args) {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        registry.register(MetricRegistry.name(GaugesTest.class, "queue", "size"), new Gauge<Integer>() {
            public Integer getValue() {
                return queue.size();
            }
        });

        while (true) {
            try {
                Thread.sleep(1000);
                queue.add("job - " + LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
