package com.hexagon;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT;
import static java.util.function.Function.identity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.handler.logging.LogLevel;
import java.text.DateFormat;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
public class ApplicationConfiguration {
  /**
   * Returns the {@link Clock} used for time-dependent {@link
   * org.springframework.stereotype.Component Components}.
   *
   * @return The {@link Clock} used for time-dependent {@link
   *     org.springframework.stereotype.Component Components}.
   */
  @Bean
  public Clock clockBean() {
    return Clock.systemUTC();
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule())
        .setSerializationInclusion(NON_ABSENT)
        .setDateFormat(DateFormat.getDateTimeInstance());
  }

  @Bean
  public HttpClient httpClient() {
    return HttpClient.create()
        .metrics(true, identity())
        .wiretap(
            "reactor.netty.http.client.HttpClient", LogLevel.INFO, AdvancedByteBufFormat.SIMPLE);
  }
}
