package com.uzunkonak.sparkapp.spark;

import com.uzunkonak.sparkapp.spark.SparkController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caner Uzunkonak on 09.01.2017.
 */
@Configuration
public class SparkConfiguration {

    @Autowired(required = false)
    private List<SparkController> sparks = new ArrayList<>();

    @Bean
    CommandLineRunner sparkRunner() {
        return args -> sparks.stream().forEach( spark -> spark.register());
    }

}
