package com.patient.qa.Testcases;

import org.testng.annotations.Test;

import com.patient.qa.base.TestBase;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class patientAPITest extends TestBase {

	@Test
	public void getResponse() {
		List<Integer> allYearPop = new ArrayList<Integer>();
		List<Double> populationRisePercent = new ArrayList<Double>();
		Response res = given().get(getURL()).then().statusCode(200).log().all().extract().response();
		JSONObject root = new JSONObject(res.getBody().asString());
		JSONArray allYearDataArray = root.getJSONArray("data");
		JSONObject lastYearDatas = allYearDataArray.getJSONObject(0);
		int noOfYears = allYearDataArray.length();
		JSONObject firstYearDatas = allYearDataArray.getJSONObject(noOfYears - 1);
		String IDNation = lastYearDatas.getString("ID Nation");
		int lastIDYear = lastYearDatas.getInt("ID Year");
		int firstIDYear = firstYearDatas.getInt("ID Year");
		for (int i = noOfYears - 1; i >= 0; i--) {
			allYearPop.add(allYearDataArray.getJSONObject(i).getInt("Population"));
		}
		for (int i = 1; i < allYearPop.size(); i++) {
			int rise = (allYearPop.get(i) - allYearPop.get(i - 1));

			populationRisePercent.add((double) (rise * 100) / allYearPop.get(i - 1));
		}
		double maxPopPercentRise = Collections.max(populationRisePercent);
		double minPopPercentRise = Collections.min(populationRisePercent);
		int maxYearIndex = noOfYears - (populationRisePercent.indexOf(maxPopPercentRise) + 2);
		int minYearIndex = noOfYears - (populationRisePercent.indexOf(minPopPercentRise) + 2);

		JSONObject yearDataOfMaxRise = allYearDataArray.getJSONObject(maxYearIndex);
		JSONObject yearDataOfMinRise = allYearDataArray.getJSONObject(minYearIndex);
		int yearOfMaxRise = yearDataOfMaxRise.getInt("ID Year");
		int yearOfMinRise = yearDataOfMinRise.getInt("ID Year");
		System.out.println("According to " + IDNation + ", in " + noOfYears + " years from " + firstIDYear + " to "
				+ lastIDYear + ", peak population was " + maxPopPercentRise + "% in " + yearOfMaxRise
				+ " and lowest population increase was " + minPopPercentRise + "% in " + yearOfMinRise + ".");
	}
}