package com.videogame.api.videogame;

import com.videogame.api.model.VideoGamePojo;
import com.videogame.api.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.Random;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends TestBase {


    public Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt(1000);

    @Test
    public void getAllTheVideosGamesInTheDB() {
        Response response =
                given()
                        .when()
                        .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void createANewVideoGame() {

        VideoGamePojo videoGamePojo = new VideoGamePojo();

        videoGamePojo.setId(randomInt);
        videoGamePojo.setName("SpiderMan");
        videoGamePojo.setReleaseDate("2021-07-15T19:08:27.6092");
        videoGamePojo.setReviewScore(98);
        videoGamePojo.setCategory("Entertainment");
        videoGamePojo.setRating("Universal");

        Response response = given()

                .header("Content-Type", "application/json")
                .body(videoGamePojo).accept("application/json")
                .when()
                .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteRecord() {

        Response response = given()
                .accept("application/json")
                .pathParam("id", "11")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void getProductById() {
        Response response =
                given()
                        .accept("application/json")
                        .pathParam("id", 2)
                        .when()
                        .get("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

    }

    @Test
    public void updateExistingVideoGame() {
        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(3);
        videoGamePojo.setName("Potato");
        videoGamePojo.setReleaseDate("2021-06-12T19:08:27.6092");
        videoGamePojo.setReviewScore(100);
        videoGamePojo.setCategory("Entertainment Word");
        videoGamePojo.setRating("Universal");

        Response response = given()
                .accept("application/json")
                .header("Content-Type", "application/json")
                .body(videoGamePojo)
                .when()
                .put("/3");
        response.then().statusCode(200);
        response.prettyPrint();

    }
}
