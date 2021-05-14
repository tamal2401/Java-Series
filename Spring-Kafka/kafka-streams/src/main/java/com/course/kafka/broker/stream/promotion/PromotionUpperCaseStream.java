package com.course.kafka.broker.stream.promotion;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class PromotionUpperCaseStream {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Bean
    public KStream<String, String> kStreamPromotionUppercase(StreamsBuilder builder) {
        KStream<String, String> sourceStream = builder.stream("t.comodity.promotion", Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, String> upperCaseStream = sourceStream.mapValues(s -> s.toUpperCase());
        upperCaseStream.to("t.comodity.promotion-uppercase");

        if (log.isDebugEnabled()) {
            sourceStream.print(Printed.<String, String>toSysOut().withLabel("Original Stream"));
            upperCaseStream.print(Printed.<String, String>toSysOut().withLabel("Upper-Case Stream"));
        }

        return sourceStream;
    }
}
