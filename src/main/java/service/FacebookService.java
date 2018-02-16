package service;

import model.FacebookUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


@Service
public class FacebookService {

    private static final Logger logger = LoggerFactory.getLogger(FacebookService.class);

    private final WebTarget target = ClientBuilder.newClient()
            .target("http://graph.facebook.com/");

    public Future<FacebookUser> getUserAsync(String user) {
        logger.info("Looking up facebook user" + user);
        try {

            FacebookUser results = new FacebookUser("Flavio Luiz Mendes", "flmendes", "Brazil");
            Thread.sleep(2000L);
            return CompletableFuture.completedFuture(results);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return CompletableFuture.completedFuture(new FacebookUser("", "", ""));
        }

    }
}
