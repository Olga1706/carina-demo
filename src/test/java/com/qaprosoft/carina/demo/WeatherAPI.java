package com.qaprosoft.carina.demo;
import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;

import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import weatherApi.*;

import java.lang.invoke.MethodHandles;

public class WeatherAPI implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Test()
    @MethodOwner(owner = "ol")
    public void testGetWeather() {
        GetWeatherMethods getWeatherMethods = new GetWeatherMethods();
        getWeatherMethods.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherMethods.callAPI();
        getWeatherMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherMethods.validateResponseAgainstSchema("apiWeather/getWeatherMethods/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "ol")
    public void testWeatherByCity() {
        GetWeatherByCity getWeatherByCity = new GetWeatherByCity();
        getWeatherByCity.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCity.callAPI();
        getWeatherByCity.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByCity.validateResponseAgainstSchema("apiWeather/getWeatherByCity/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "ol")
    public void testWeatherById() {
        GetWeatherById getWeatherById = new GetWeatherById();
        getWeatherById.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherById.callAPI();
        getWeatherById.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherById.validateResponseAgainstSchema("apiWeather/getWeatherById/rs.schema");//
    }

    @Test()
    @MethodOwner(owner = "ol")
    public void testWeatherByCoordinates() {
        GetWeatherByCoordinates getWeatherByCoordinates = new GetWeatherByCoordinates();
        getWeatherByCoordinates.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByCoordinates.callAPI();
        getWeatherByCoordinates.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByCoordinates.validateResponseAgainstSchema("apiWeather/getWeatherByCoordinates/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "ol")
    public void testWeatherByFiveDays() {
        GetWeatherByFiveDays getWeatherByFiveDays = new GetWeatherByFiveDays();
        getWeatherByFiveDays.expectResponseStatus(HttpResponseStatusType.OK_200);
        getWeatherByFiveDays.callAPI();
        getWeatherByFiveDays.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getWeatherByFiveDays.validateResponseAgainstSchema("apiWeather/getWeatherByFiveDays/rs.schema");
    }

}
