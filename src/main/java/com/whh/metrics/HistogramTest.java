package com.whh.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by whh on 2017/8/7.
 * Histogram
 * 作用：计算执行次数count、最小值min，最大值max，平均值mean，方差stddev，中位数median，75百分位, 90百分位, 95百分位, 98百分位, 99百分位, 和 99.9百分位的值
 */
public class HistogramTest {
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);

        Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());//95%
        registry.register(MetricRegistry.name(HistogramTest.class, "request", "histogram"), histogram);

        Random random = new Random();
        while (true) {
            Thread.sleep(1000);
            histogram.update(random.nextInt(10000));
        }
    }
}
