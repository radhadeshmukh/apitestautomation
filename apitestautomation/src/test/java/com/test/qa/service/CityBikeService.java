package com.test.qa.service;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CityBikeService {

    /**
     * Get City bike Network object.
     *
     * @param requestSpec - Rest assured RequestSpecification
     * @return - Return CityBikeService Response Object.
     */
    public Response getAll(final RequestSpecification requestSpec) {

        return given().spec(requestSpec)
                .get("")
                .thenReturn();
    }

    /**
     * Get Stations information, bike network and slots.
     *
     * @param requestSpec - Rest assured RequestSpecification.
     * @param networkId   - Network ID.
     * @return - Return CityBikeService Station Response Object.
     */
    public Response getStationInfo(final RequestSpecification requestSpec, final String networkId) {

        return given().spec(requestSpec)
                .get("/{network_id}", networkId)
                .thenReturn();
    }

    /**
     * Get Fields filtering search.
     *
     * @param requestSpec  - Rest assured RequestSpecification.
     * @param fieldsFilter - fileds filter parameters.
     * @return - Returns networks array object with href,id and name.
     */
    public Response getFieldFilter(final RequestSpecification requestSpec, final String fieldsFilter) {

        return given().spec(requestSpec)
                .queryParam("fields", fieldsFilter)
                .get("")
                .thenReturn();
    }
}
