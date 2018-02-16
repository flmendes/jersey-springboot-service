package service;

import model.GitHubContributor;
import model.GitHubRepo;
import model.GitHubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class GitHubService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubService.class);

    private final WebTarget target = ClientBuilder.newClient().target("https://api.github.com/");

    public Future<GitHubUser> userAsync(String user) {
        logger.info("GitHub user look up");
        try {

            GitHubUser results = new GitHubUser("flmendes", "User", "Flávio Luiz Mendes");
            Thread.sleep(1000L);
            return CompletableFuture.completedFuture(results);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return CompletableFuture.completedFuture(new GitHubUser("", "", ""));
        }
//        return target
//                .path("/users/{user}")
//                .resolveTemplate("user", user)
//                .request(MediaType.APPLICATION_JSON_TYPE)
//                .async()
//                .get(GitHubUser.class);
    }

    public Future<List<GitHubRepo>> reposAsync(String user) {
        logger.info("GitHub repo look up");
        return target
                .path("users/{user}/repos")
                .resolveTemplate("user", user)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .async()
                .get(new GenericType<List<GitHubRepo>>() { });
    }

    public Future<List<GitHubContributor>> contributorsAsync(String owner, String repo) {
        logger.info("GitHub contributor look up");
        return target
                .path("/repos/{owner}/{repo}/contributors")
                .resolveTemplate("owner", owner)
                .resolveTemplate("repo", repo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .async()
                .get(new GenericType<List<GitHubContributor>>() { });
    }
}