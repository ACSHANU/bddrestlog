package com.spring.framework.automation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Duvvuri on 15/01/2019.
 */
@Configuration
@ComponentScan("com.spring.framework.automation")
@PropertySource("classpath:env.properties")
public class SeleniumConfig {

}
