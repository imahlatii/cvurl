package coreserech.cvurl.io.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.client.WireMock;
import coreserech.cvurl.io.helper.ObjectGenerator;
import coreserech.cvurl.io.helper.model.User;
import coreserech.cvurl.io.model.Response;
import coreserech.cvurl.io.util.HttpHeader;
import coreserech.cvurl.io.util.HttpStatus;
import coreserech.cvurl.io.util.MIMEType;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CVurlPatchRequestTest extends AbstractRequestTest {

    @Test
    public void sendPATCHTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_URLTest() throws MalformedURLException {

        //given
        String strURL = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);
        URL url = new URL(strURL);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_QueryParamTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);
        String testParam = "param";

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT + "?param=param"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .queryParam(testParam, testParam)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT + "?param=param")));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_QueryParamsTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);
        String testParam = "param";
        String testParam2 = "param2";

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT + "?param=param&param2=param2"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .queryParam(testParam, testParam)
                .queryParam(testParam2, testParam2)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT + "?param=param&param2=param2")));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_HeaderRequiredTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withHeader(HttpHeader.AUTHORIZATION, WireMock.equalTo(TEST_TOKEN))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .header(HttpHeader.AUTHORIZATION, TEST_TOKEN)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT))
                        .withHeader(HttpHeader.AUTHORIZATION, WireMock.equalTo(TEST_TOKEN)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_HeadersRequiredTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeader.AUTHORIZATION, TEST_TOKEN);
        headers.put(HttpHeader.ACCEPT, "xml");

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withHeader(HttpHeader.AUTHORIZATION, WireMock.equalTo(TEST_TOKEN))
                .withHeader(HttpHeader.ACCEPT, WireMock.equalTo("xml"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .headers(headers)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);
        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_checkResponseHeaderTest() {

        //given
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withHeader(HttpHeader.AUTHORIZATION, TEST_TOKEN)
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
        Assert.assertTrue(response.headersNames().contains(HttpHeader.AUTHORIZATION));
        Assert.assertEquals(TEST_TOKEN, response.getHeaderValue(HttpHeader.AUTHORIZATION).get());
    }

    @Test
    public void sendPATCH_StringResponseTest() {

        //given
        String body = "Test body for test";
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)
                        .withBody(body)));

        Response<String> response = cvurl.PATCH(url)
                .body("")
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
        Assert.assertEquals(body, response.getBody());
    }


    @Test
    public void sendPATCH_StringRequestBodyTest() {

        //given
        String body = "Test body for test";
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(body))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(body)
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);
        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_StringWithMediaTypeRequestBodyTest() {

        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("status", 200);
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(jsonObject.toString()))
                .withHeader(HttpHeader.CONTENT_TYPE, WireMock.equalTo(MIMEType.APPLICATION_JSON))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(jsonObject)
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_BytesRequestBodyTest() {

        //given
        String body = "Test body for test";
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(body))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(body.getBytes())
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_CollectionsRequestBodyTest() throws JsonProcessingException {

        //given
        List<User> users = ObjectGenerator.generateListOfTestObjects();

        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(mapper.writeValueAsString(users)))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(users)
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_ObjectRequestBodyTest() throws JsonProcessingException {

        //given
        User user = ObjectGenerator.generateTestObject();
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(mapper.writeValueAsString(user)))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(user)
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void sendPATCH_JSONRequestBodyTest() {

        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("status", 200);
        String url = String.format(URL_PATTERN, PORT, TEST_ENDPOINT);

        //when
        wiremock.stubFor(WireMock.patch(WireMock.urlEqualTo(TEST_ENDPOINT))
                .withRequestBody(WireMock.equalTo(jsonObject.toString()))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK)));

        Response<String> response = cvurl.PATCH(url)
                .body(jsonObject)
                .build()
                .asString()
                .orElseThrow(RuntimeException::new);

        //then
        WireMock.verify(WireMock.exactly(1),
                WireMock.patchRequestedFor(WireMock.urlEqualTo(TEST_ENDPOINT)));

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(HttpStatus.OK, response.status());
    }
}
