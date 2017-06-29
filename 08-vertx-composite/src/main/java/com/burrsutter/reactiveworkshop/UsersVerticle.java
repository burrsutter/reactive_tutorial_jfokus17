package com.burrsutter.reactiveworkshop;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;


/**
 * Created by burr on 2/1/17.
 */
public class UsersVerticle extends AbstractVerticle{
    public void start() throws Exception {
        System.out.println("\n**UsersVerticle start**\n");
        Router router = Router.router(vertx);
        router.get("/users").handler(this::getUsers);
        router.get("/").handler(this::getRoot);
        router.get("/users/:login").handler(this::getUser);
        router.get("/users/:login/followers").handler(this::getFollowers);

        vertx.createHttpServer().requestHandler(router::accept).listen(8081, ar -> {
            if (ar.succeeded()) {
                System.out.println("\n** UsersVerticle started on 8081 ** \n");
            } else {
                System.out.println("Error: " + ar.cause());
            }
        });
    } // start

    private void getFollowers(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json");
        String login = routingContext.request().getParam("login");
        response.setChunked(true);
        // all users get the same followers
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

    private void getUsers(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json");
        response.setChunked(true);
        response.write(
        "[{\n" +
        "    \"login\": \"mojombo\",\n" +
        "    \"id\": 1,\n" +
        "    \"avatar_url\": \"http://avatars.githubusercontent.com/u/1?v=3\",\n" +
        "    \"followers_url\": \"http://localhost:8081/users/mojombo/followers\", \n" +
        "    \"following_url\": \"http://localhost:8081/users/mojombo/following{/other_user}\",\n" +
        "    \"organizations_url\": \"http://localhost:8081/users/mojombo/orgs\", \n" +
        "    \"repos_url\": \"http://localhost:8081/users/mojombo/repos\", \n" +
        "    \"type\": \"User\",\n" +
        "    \"site_admin\": false \n" +
        "}, { \n" +
        "    \"login\": \"brynary\", \n" +
        "    \"id\": 19,\n" +
        "    \"avatar_url\": \"http://avatars.githubusercontent.com/u/1?v=3\",\n" +
        "    \"followers_url\": \"http://localhost:8081/users/brynary/followers\", \n" +
        "    \"following_url\": \"http://localhost:8081/users/brynary/following{/other_user}\",\n" +
        "    \"organizations_url\": \"http://localhost:8081/users/brynary/orgs\", \n" +
        "    \"repos_url\": \"http://localhost:8081/users/brynary/repos\",\n" +
        "    \"type\": \"User\", \n" +
        "    \"site_admin\": false \n" +
        "}]\n");

        response.end();
    }

    private void getUser(RoutingContext routingContext) {
        String login = routingContext.request().getParam("login");
        //  System.out.println("getUser for " + login);

        final String brynary =
                "{\n" +
                        "  \"login\": \"brynary\",\n" +
                        "  \"id\": 19,\n" +
                        "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/19?v=3\",\n" +
                        "  \"gravatar_id\": \"\",\n" +
                        "  \"url\": \"https://api.github.com/users/brynary\",\n" +
                        "  \"html_url\": \"https://github.com/brynary\",\n" +
                        "  \"followers_url\": \"https://api.github.com/users/brynary/followers\",\n" +
                        "  \"following_url\": \"https://api.github.com/users/brynary/following{/other_user}\",\n" +
                        "  \"gists_url\": \"https://api.github.com/users/brynary/gists{/gist_id}\",\n" +
                        "  \"starred_url\": \"https://api.github.com/users/brynary/starred{/owner}{/repo}\",\n" +
                        "  \"subscriptions_url\": \"https://api.github.com/users/brynary/subscriptions\",\n" +
                        "  \"organizations_url\": \"https://api.github.com/users/brynary/orgs\",\n" +
                        "  \"repos_url\": \"https://api.github.com/users/brynary/repos\",\n" +
                        "  \"events_url\": \"https://api.github.com/users/brynary/events{/privacy}\",\n" +
                        "  \"received_events_url\": \"https://api.github.com/users/brynary/received_events\",\n" +
                        "  \"type\": \"User\",\n" +
                        "  \"site_admin\": false,\n" +
                        "  \"name\": \"Bryan Helmkamp\",\n" +
                        "  \"company\": \"Code Climate\",\n" +
                        "  \"blog\": \"http://codeclimate.com\",\n" +
                        "  \"location\": \"New York City\",\n" +
                        "  \"email\": \"bryan@brynary.com\",\n" +
                        "  \"hireable\": null,\n" +
                        "  \"bio\": \"Co-founder and CEO, Code Climate\",\n" +
                        "  \"public_repos\": 161,\n" +
                        "  \"public_gists\": 65,\n" +
                        "  \"followers\": 560,\n" +
                        "  \"following\": 27,\n" +
                        "  \"created_at\": \"2008-01-13T10:19:47Z\",\n" +
                        "  \"updated_at\": \"2017-01-17T11:06:22Z\"\n" +
                        "}";

        final String mojombo = "{\n" +
                "  \"login\": \"mojombo\",\n" +
                "  \"id\": 1,\n" +
                "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/1?v=3\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/mojombo\",\n" +
                "  \"html_url\": \"https://github.com/mojombo\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": \"Tom Preston-Werner\",\n" +
                "  \"company\": null,\n" +
                "  \"blog\": \"http://tom.preston-werner.com\",\n" +
                "  \"location\": \"San Francisco\",\n" +
                "  \"email\": \"tom@mojombo.com\",\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": null,\n" +
                "  \"public_repos\": 61,\n" +
                "  \"public_gists\": 62,\n" +
                "  \"followers\": 19929,\n" +
                "  \"following\": 11,\n" +
                "  \"created_at\": \"2007-10-20T05:24:19Z\",\n" +
                "  \"updated_at\": \"2017-01-29T10:10:50Z\"\n" +
                "}";

        final String defunkt = "{\n" +
                "  \"login\": \"defunkt\",\n" +
                "  \"id\": 2,\n" +
                "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/2?v=3\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/defunkt\",\n" +
                "  \"html_url\": \"https://github.com/defunkt\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": true,\n" +
                "  \"name\": \"Chris Wanstrath\",\n" +
                "  \"company\": \"@github \",\n" +
                "  \"blog\": \"http://chriswanstrath.com/\",\n" +
                "  \"location\": \"San Francisco\",\n" +
                "  \"email\": \"chris@github.com\",\n" +
                "  \"hireable\": true,\n" +
                "  \"bio\": \"\uD83C\uDF54 \",\n" +
                "  \"public_repos\": 107,\n" +
                "  \"public_gists\": 273,\n" +
                "  \"followers\": 15930,\n" +
                "  \"following\": 208,\n" +
                "  \"created_at\": \"2007-10-20T05:24:19Z\",\n" +
                "  \"updated_at\": \"2017-01-28T21:34:49Z\"\n" +
                "}";

        final String ezmobius = "{\n" +
                "  \"login\": \"ezmobius\",\n" +
                "  \"id\": 5,\n" +
                "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/5?v=3\",\n" +
                "  \"gravatar_id\": \"\",\n" +
                "  \"url\": \"https://api.github.com/users/ezmobius\",\n" +
                "  \"html_url\": \"https://github.com/ezmobius\",\n" +
                "  \"followers_url\": \"https://api.github.com/users/ezmobius/followers\",\n" +
                "  \"following_url\": \"https://api.github.com/users/ezmobius/following{/other_user}\",\n" +
                "  \"gists_url\": \"https://api.github.com/users/ezmobius/gists{/gist_id}\",\n" +
                "  \"starred_url\": \"https://api.github.com/users/ezmobius/starred{/owner}{/repo}\",\n" +
                "  \"subscriptions_url\": \"https://api.github.com/users/ezmobius/subscriptions\",\n" +
                "  \"organizations_url\": \"https://api.github.com/users/ezmobius/orgs\",\n" +
                "  \"repos_url\": \"https://api.github.com/users/ezmobius/repos\",\n" +
                "  \"events_url\": \"https://api.github.com/users/ezmobius/events{/privacy}\",\n" +
                "  \"received_events_url\": \"https://api.github.com/users/ezmobius/received_events\",\n" +
                "  \"type\": \"User\",\n" +
                "  \"site_admin\": false,\n" +
                "  \"name\": \"Ezra Zygmuntowicz\",\n" +
                "  \"company\": \"Stuffstr PBC\",\n" +
                "  \"blog\": \"http://stuffstr.com\",\n" +
                "  \"location\": \"In the NW\",\n" +
                "  \"email\": \"ez@stuffstr.com\",\n" +
                "  \"hireable\": null,\n" +
                "  \"bio\": null,\n" +
                "  \"public_repos\": 22,\n" +
                "  \"public_gists\": 106,\n" +
                "  \"followers\": 444,\n" +
                "  \"following\": 13,\n" +
                "  \"created_at\": \"2008-01-12T07:51:46Z\",\n" +
                "  \"updated_at\": \"2017-01-23T05:43:25Z\"\n" +
                "}";

        if (login.equals("brynary"))
            routingContext.response().end(brynary);
        else if (login.equals("mojombo"))
            routingContext.response().end(mojombo);
        else if (login.equals("defunkt"))
            routingContext.response().end(defunkt);
        else if (login.equals("ezmobius"))
            routingContext.response().end(ezmobius);
        else
            routingContext.response().end("Unknown login: " + login);


    }  // getUser



    private void getRoot(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "text/html").end("/api/users</br>");
    }


}
