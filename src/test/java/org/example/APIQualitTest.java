package org.example;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import org.example.pojos.ProductPOJO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APIQualitTest {
    private Cookies cookies;
    @BeforeEach
    public void preCondition() {
        cookies = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/food")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getDetailedCookies();
    }

    @ParameterizedTest
    @CsvSource({"Картофель,false","Melotria,true"})
    void testAddVegetable(String name, boolean exotic){

        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/api/"),
                Specifications.responseSpecification(200));

        ProductPOJO example = new ProductPOJO(name, "VEGETABLE",exotic);

        List<ProductPOJO> list = given()
                .cookies(cookies)
                .when()
                .get("food")
                .then()
                .extract()
                .jsonPath()
                .getList("",ProductPOJO.class);

        Assertions.assertFalse(list.contains(example), "Строка уже существует");

        given()
                .cookies(cookies)
                .body("{\n" +
                        "  \"name\": \"" + name +"\",\n" +
                        "  \"type\": \"VEGETABLE\",\n" +
                        "  \"exotic\": "+exotic+"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("food")
                .then().log().all();

        List<ProductPOJO> list_ = given()
                .cookies(cookies)
                .when()
                .get("food")
                .then()
                .extract()
                .jsonPath()
                .getList("",ProductPOJO.class);

        Assertions.assertTrue(list_.contains(example),"Строка не добавилась");
    }

    @ParameterizedTest
    @CsvSource({"Клубника,false","Mangosteen,true"})
    void testAddFruit(String name, boolean exotic){
        Specifications.installSpecification(Specifications.requestSpecification("http://localhost:8080/api/"),
                Specifications.responseSpecification(200));

        ProductPOJO example = new ProductPOJO(name,"FRUIT",exotic);

        List<ProductPOJO> list = given()
                .cookies(cookies)
                .when()
                .get("food")
                .then()
                .extract()
                .jsonPath()
                .getList("",ProductPOJO.class);

        Assertions.assertFalse(list.contains(example), "Строка уже существует");

        given()
                .cookies(cookies)
                .body("{\n" +
                        "  \"name\": \"" + name +"\",\n" +
                        "  \"type\": \"FRUIT\",\n" +
                        "  \"exotic\": "+exotic+"\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("food")
                .then().log().all();

        List<ProductPOJO> list_ = given()
                .cookies(cookies)
                .when()
                .get("food")
                .then()
                .extract()
                .jsonPath()
                .getList("",ProductPOJO.class);

        Assertions.assertTrue(list_.contains(example),"Строка не добавилась");
    }

    @AfterEach
    void postCondition(){
        given()
                .cookies(cookies)
                .when()
                .post("data/reset")
                .then().log().all()
                .statusCode(200);
    }
}
