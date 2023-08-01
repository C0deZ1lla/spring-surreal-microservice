package net.irules.productservice.utils;

import com.surrealdb.connection.SurrealConnection;
import com.surrealdb.connection.SurrealWebSocketConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.surrealdb.driver.SyncSurrealDriver;

@Slf4j
@Configuration
public class SurrealDbConfig {

    @Bean
    public SyncSurrealDriver newSyncSurrealDriverBean(
            @Value("${surrealdb.host}") String host,
            @Value("${surrealdb.port}") int port,
            @Value("${surrealdb.use-tls}") boolean useTls,
            @Value("${surrealdb.username}") String username,
            @Value("${surrealdb.password}") String password,
            @Value("${surrealdb.namespace}") String namespace,
            @Value("${surrealdb.database}") String database
    ){
        log.info("Trying to connect to SurrealDB server ...");

        SurrealConnection connection = new SurrealWebSocketConnection(host, port, useTls);
        connection.connect(10);

        SyncSurrealDriver driver = new SyncSurrealDriver(connection);
        driver.signIn(username, password);
        driver.use(namespace, database);

        log.info("Connected Successfully");

        return driver;
    }

}
