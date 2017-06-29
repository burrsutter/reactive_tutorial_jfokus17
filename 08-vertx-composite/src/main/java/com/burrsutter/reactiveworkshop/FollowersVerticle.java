package com.burrsutter.reactiveworkshop;

import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;


/**
 * Created by burr on 2/8/17.
 */
public class FollowersVerticle extends AbstractVerticle {
    public void start() throws Exception {

        Router router = Router.router(vertx);
        router.get("/followers/:login").handler(this::getFollowers);
        vertx.createHttpServer().requestHandler(router::accept).listen(8082, ar -> {
            if(ar.succeeded()) {
                System.out.println("\n** FollowersVerticle started on 8082 ** \n");
            } else {
                System.out.println("Error: " + ar.cause());
            }
        });
    }
    public void getFollowers(RoutingContext rc) {
        HttpServerResponse response = rc.response();
        response.putHeader("content-type", "application/json");
        String login = rc.request().getParam("login");
        response.setChunked(true);
        // all users get the same followers for now!
        response.write("[ " +
                "{\n" +
                "    \"login\": \"defunkt\",\n" +
                "    \"id\": 2,\n" +
                "    \"avatar_url\": \"http://avatars.githubusercontent.com/u/2?v=3\",\n" +
                "    \"followers_url\": \"http://localhost:8081/users/defunkt/followers\",\n" +
                "    \"following_url\": \"http://localhost:8081/users/defunkt/following{/other_user}\",\n" +
                "    \"organizations_url\": \"http://localhost:8081/users/defunkt/orgs\",\n" +
                "    \"repos_url\": \"http://localhost:8081/users/defunkt/repos\",\n" +
                "    \"type\": \"User\",\n" +
                "    \"site_admin\": true\n" +
                "},\n" +
                "{ \n" +
                "    \"login\": \"ezmobius\",\n" +
                "    \"id\": 5,\n" +
                "    \"avatar_url\": \"http://avatars.githubusercontent.com/u/5?v=3\",\n" +
                "    \"followers_url\": \"http://localhost:8081/users/ezmobius/followers\",\n" +
                "    \"following_url\": \"http://localhost:8081/users/ezmobius/following{/other_user}\",\n" +
                "    \"organizations_url\": \"http://localhost:8081/users/ezmobius/orgs\",\n" +
                "    \"repos_url\": \"http://localhost:8081/users/ezmobius/repos\",\n" +
                "    \"type\": \"User\",\n"  +
                "    \"site_admin\": false\n" +
                "}\n" +
                "]\n");

        response.end();

    }
}
