package com.test.qa.steps;

import com.test.qa.config.Config;
import com.test.qa.service.CityBikeService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.Matchers.hasKey;

public class CityBikeApiSteps {

    private RequestSpecification requestSpec;
    private Response response;

    // Host details
    private String cityBikeApiUrl;

    @Before
    public void setUp() {

        final Config config = new Config();

        cityBikeApiUrl = config.init().getHostname();

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(cityBikeApiUrl)
                .addHeader("Content-Type", "application/json")
                .build();

        requestSpec.log().all();
    }

    @Given("developer request field filtering  with {string}")
    public void developerRequestFieldFilteringWith(final String filedFilterParameters) {

        response = new CityBikeService().getFieldFilter(requestSpec, filedFilterParameters);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getStatusLine()).contains("OK");
        assertThat(response.getContentType()).isEqualTo("application/json");
    }

    @Then("developer gets render response with just the name, id, href")
    public void developerGetsRenderResponseWithJustTheNameIdHref() {

        response.then().log().ifValidationFails()
                .assertThat().body(matchesJsonSchemaInClasspath("json-schema/field_filter_response.json"));

        assertThat(response.jsonPath().getList("networks").size()).isGreaterThan(0);
        assertThat(response.jsonPath().getString("networks[0].href")).isNotNull().isNotEmpty();
        assertThat(response.jsonPath().getString("networks[0].id")).isNotNull().isNotEmpty();
        assertThat(response.jsonPath().getString("networks[0].name")).isNotNull().isNotEmpty();
    }

    @Given("developers pass network id {string}")
    public void developersPassNetworkId(final String networkId) {

        response = new CityBikeService().getStationInfo(requestSpec, networkId);
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getStatusLine()).contains("OK");
        assertThat(response.getContentType()).isEqualTo("application/json");
    }

    @Then("network location corresponded {string} in {string}")
    public void networkLocationCorrespondedIn(final String city, final String country) {

        assertThat(response.jsonPath().getString("network.location.city")).isEqualTo(city);
        assertThat(response.jsonPath().getString("network.location.country")).isEqualTo(country);

    }

    @And("corresponded location {string}, {string} returned")
    public void correspondedLocationReturned(final String lat, final String lon) {

        assertThat(response.jsonPath().getString("network.location.latitude")).isNotNull();
        assertThat(response.jsonPath().getString("network.location.longitude")).isNotNull();

        final String latitude = response.jsonPath().getString("network.location.latitude");
        final String longitude = response.jsonPath().getString("network.location.longitude");

        assertThat(latitude).isEqualTo(lat);
        assertThat(longitude).isEqualTo(lon);
    }

    @Then("number of stations should be returned")
    public void numberOfStationsShouldBeReturned() {

        assertThat(response.jsonPath().getList("network.stations").size()).isGreaterThan(0);

    }

    @And("validate available bikes, timestamps")
    public void validateAvailableBikesTimestamps() {

        final int stationSize = response.jsonPath().getList("network.stations").size();

        System.out.printf("Size of stations: %s %n", stationSize);

        final String station_id = response.jsonPath().getString("network.stations[0].id");
        final String timestamp = response.jsonPath().getString("network.stations[0].timestamp");

        response.then().assertThat().body("network.stations[0]", hasKey("empty_slots"));
        assertThat(station_id).isNotNull().isNotEmpty();
        assertThat(timestamp).doesNotMatch("....-..-..T..:..:..Z");
    }

    @Given("developer request cityBik API Url")
    public void developerRequestCityBikAPIUrl() {

        response = new CityBikeService().getAll(requestSpec);

    }

    @Then("Verify if the status code is {int}")
    public void verifyIfTheStatusCodeIs(final int statusCode) {

        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @And("Verify if the response contains network info")
    public void verifyIfTheResponseContainsNetworkInfo() {

        assertThat(response.jsonPath().getList("networks").size()).isGreaterThan(0);
        assertThat(response.jsonPath().getString("networks[0].href")).isNotNull().isNotEmpty();
        assertThat(response.jsonPath().getString("networks[0].id")).isNotNull().isNotEmpty();
        assertThat(response.jsonPath().getString("networks[0].name")).isNotNull().isNotEmpty();
    }
}
