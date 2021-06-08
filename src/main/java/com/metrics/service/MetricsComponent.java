package com.metrics.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component
public class MetricsComponent {

    @Autowired
    private MeterRegistry meterRegistry;

    @PostConstruct
    public void checkMetrics() {
        Map<String, Integer> integerMap = new HashMap<>();
        String customData = "customData-mappings";
        Integer data = (Integer)integerMap.get(customData);

        Tags tag = Tags.of(new String[] { "handlerMethod", "DemoHandler", "requestMappingConditions", "consumes GET"});

        if (data == null) {
            System.out.println("in if loop of metrics");
            integerMap.put(customData, (Integer)this.meterRegistry.gauge("customData", tag, 123));
        }
    }
}
