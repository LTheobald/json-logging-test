package uk.co.ltheobald.jsonloggingtest;

import static net.logstash.logback.argument.StructuredArguments.kv;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * A simple class to log a few example lines as if people are calling endpoints on an API & business logic is firing
 */
@Component
public class LoggingExamples {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingExamples.class);

  @Scheduled(fixedRate = 2500)
  public void createLogs() {
    int randomEndpoint = ThreadLocalRandom.current().nextInt(0, 3);
    String clientName = "client" + ThreadLocalRandom.current().nextInt(1, 6);
    String correlationId = UUID.randomUUID().toString();
    switch (randomEndpoint) {
      case 0 -> logPayEndpoint(clientName, correlationId);
      case 1 -> logBalanceEndpoint(clientName, correlationId);
      case 2 -> logTransactionsEndpoint(clientName, correlationId);
    }
  }

  public void logPayEndpoint(String clientName, String correlationId) {
    LOGGER.info("Authenticating", kv("endpoint", "pay"), kv("correlationId", correlationId),
        kv("client", clientName));
    LOGGER.info("Calling 3rd party", kv("correlationId", correlationId), kv("client", clientName));
    LOGGER.info("Payment complete", kv("correlationId", correlationId), kv("client", clientName));
  }

  public void logBalanceEndpoint(String clientName, String correlationId) {
    LOGGER.info("Authenticating", kv("endpoint", "balance"), kv("correlationId", correlationId),
        kv("client", clientName));
    LOGGER.info("Retrieving balance", kv("correlationId", correlationId), kv("client", clientName));
  }

  public void logTransactionsEndpoint(String clientName, String correlationId) {
    LOGGER.info("Authenticating", kv("endpoint", "transactions"), kv("correlationId", correlationId), kv("client", clientName));
    LOGGER.info("Retrieving Transactions history from DB", kv("correlationId", correlationId),
        kv("client", clientName));
    LOGGER.info("Listing transactions", kv("correlationId", correlationId),
        kv("client", clientName));
  }
}
