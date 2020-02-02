package it.healthnet.staff.data;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public final class Database {
    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getJWT() {
        return JWT;
    }

    public String getScopes() {
        return scopes;
    }

    public int getExpiration() {
        return expiration;
    }

    private final String clientId = System.getenv("CLIENT_ID");
    private final String clientSecret = System.getenv("CLIENT_SECRET");
    private String JWT;
    private String scopes;
    private int expiration;

    public Database(){
        JWTRefresh();
    }

    private void JWTRefresh(){
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(() -> {
            try{
                HttpResponse<JsonNode> response = Unirest.post("https://healthnet.eu.auth0.com/oauth/token")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .body("grant_type=client_credentials&client_id="+clientId+"&client_secret="+clientSecret+"&audience=https%3A%2F%2Fhealthnet.eu.auth0.com%2Fapi%2Fv2%2F").asJson();
                System.out.println(response.getBody().toString());
                JWT = response.getBody().getObject().getString("access_token");
                scopes = response.getBody().getObject().getString("scope");
                expiration = response.getBody().getObject().getInt("expires_in");
            } catch (UnirestException e) {
                System.out.println(e.getStackTrace());
            }


        }, 0, 12, TimeUnit.HOURS);

    }
}
