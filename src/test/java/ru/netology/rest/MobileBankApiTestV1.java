package ru.netology.rest;

import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;


class MobileBankApiTestV1 {
    @Test
    void shouldReturnDemoAccounts() {
        // Given - When - Then
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1")
                // Выполняемые действия
                .when()
                .get("/demo/accounts")
                // Проверки
                .then()
                .statusCode(200);
    }

    @Test
    void ContentType() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .contentType(ContentType.JSON);
    }

    @Test
    void body() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("", hasSize(3));
    }

    @Test
    void body1() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("[1].currency", equalTo("USD"));
    }

    @Test
    void balans() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body("every{ it.balance >= 0 }", (Matcher<?>) is(true));
    }

    @Test
    void shema() {
        given()
                .baseUri("http://localhost:9999/api/v1")
                .when()
                .get("/demo/accounts")
                .then()
                .body(matchesJsonSchemaInClasspath("accounts.schema.json"));
    }


}
