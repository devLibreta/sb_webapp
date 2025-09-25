package com.dev.sbWebapp.lib.apicall.restcall;

import static org.assertj.core.api.Assertions.assertThat;

import com.dev.sbWebapp.global.config.ApiTestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

@Slf4j
public class RestAssuredTest extends ApiTestConfig {
    // rest api 테스트 전문 라이브러리

    /* path variables */
    @Test
    @DisplayName("single pathParam 테스트")
    void test1(){
        RestAssured
                .given().pathParam("userName", "홍길동")
                .and().log().all()
                .when().get("/apicall/pathParam/{userName}")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName(" multiple pathParams pathParam() 테스트")
    void test2(){
        RestAssured
                .given().pathParams("userName", "홍길동","userSex", "men")
                .and().log().all()
                .when().get("/apicall/pathParams/{userName}/{userSex}")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName(" multiple pathParams pathParams() 테스트")
    void test3(){
        // 방식1
//        Response res = RestAssured
//                .given()
//                    .pathParams("userName", "홍길동")
//                    .log().all()
//                .when()
//                    // get 메서드의 두번째 파라미터 부터는 비어있는 플레이스 홀더를 순서대로 채움.
//                    .get("/apicall/pathParams/{userName}/{userSex}", "userSex")
//                .andReturn();
//        assertEquals(200, res.getStatusCode());
//        assertEquals("홍길동, userSex", res.getBody().asString());
        // 방식2
        ExtractableResponse<Response> res =
        RestAssured
                .given()
                    .pathParams("userName", "홍길동")
                    .log().all()
                .when()
                    .get("/apicall/pathParams/{userName}/{userSex}", "userSex")
                .then()
                    .log().all()
                .extract();
        assertThat(res.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(ContentType.fromContentType(res.contentType())).isEqualTo(ContentType.TEXT);
        assertThat(res.body().asString()).isEqualTo("홍길동, userSex");
    }

    /* query parameters */

    @Test
    @DisplayName("single query parameter 테스트")
    void test4(){
        RestAssured
                .given().queryParam("userName", "홍길동")
                .and().log().all()
                .when().get("/apicall/queryParameter")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName("multiple query parameters queryParam() 테스트")
    void test5(){
        RestAssured
                .given().queryParam("userName", "홍길동").queryParam("userSex","men")
                .and().log().all()
                .when().get("/apicall/queryParameters")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName("multiple query parameters queryParams() 테스트")
    void test6(){
        RestAssured
                .given().queryParams("userName", "홍길동","userSex","men")
                .and().log().all()
                .when().get("/apicall/queryParameters")
                .then().statusCode(200)
                .and().log().all();
    }

    /* form parameters */

    @Test
    @DisplayName("multiple form parameters formParams() 테스트")
    void test7(){
        RestAssured
                .given().formParams("userName", "홍길동","userSex","men")
                // 기본 인코딩이 ISO 설정이기에 UTF-8로 설정해줘야 톰캣이 한글이 깨지지 않고 인식한다.
                .and().contentType("application/x-www-form-urlencoded; charset=UTF-8") // 기본 인코딩은 ISO
                .and().log().all()
                .when().post("/apicall/formParameters")
                .then().log().all();
    }

    @Test
    @DisplayName("multiple form parameters params() 테스트")
    void test8(){
        RestAssured
                .given().params("userName", "홍길동","userSex","men")
                // 기본 인코딩이 ISO 설정이기에 UTF-8로 설정해줘야 톰캣이 한글이 깨지지 않고 인식한다.
                .and().contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .and().log().all()
                .when().post("/apicall/formParameters")
                .then().log().all();
    }

    /* setting headers */

    @Test
    @DisplayName("single header value setting header() 테스트")
    void test9(){
        RestAssured
                .given().header("header1", "val1")
                .and().log().all()
                .when().get("/apicall/header")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName("multiple header values setting header() 테스트")
    void test10(){
        RestAssured
                .given().header("header1", "val1", "val2","val3")
                .and().log().all()
                .when().get("/apicall/headerValues")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName("multiple headers setting headers() 테스트")
    void test11(){
        RestAssured
                .given().headers("header1", "val1", "header2","val2")
                .and().log().all()
                .when().get("/apicall/headers")
                .then().statusCode(200)
                .and().log().all();
    }

    @Test
    @DisplayName("cookies cookie() 테스트")
    void test12(){
        Cookie cookie = new Cookie.Builder("ck1","ckValue")
                .setSecured(true)
                .setMaxAge(1000)
                .setComment("this is comment")
                .build();
        Cookie cookie2 = new Cookie.Builder("ck2","ckValue")
                .setSecured(true)
                .setMaxAge(1000)
                .setComment("this is comment")
                .build();

        RestAssured
                .given().cookie(cookie).cookie(cookie2)
                .and().log().all()
                .when().get("/apicall/cookies")
                .then().statusCode(200)
                .and().log().all();
    }

}
