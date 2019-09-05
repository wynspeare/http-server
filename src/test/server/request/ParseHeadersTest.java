package server.request;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class ParseHeadersTest {

  @Test
  public void testHeadersCanBeParsedFromARequest() {
    ParseHeaders parseHeaders = new ParseHeaders();
    String request = "GET /simple_get HTTP/1.1\n" +
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3\n" +
            "Accept: */*\n" +
            "User-Agent: Ruby\n" +
            "Connection: close\n" +
            "Host: 127.0.0.1:5000";

    ArrayList<String> expectedHeaders = new ArrayList<String>( Arrays.asList(
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3",
            "Accept: */*",
            "User-Agent: Ruby",
            "Connection: close",
            "Host: 127.0.0.1:5000"));

    assertEquals(expectedHeaders, parseHeaders.splitRequest(request));
  }

  @Test
  public void headersCanBePutIntoKeyValuePairs() {
    ParseHeaders parseHeaders = new ParseHeaders();

    ArrayList<String> splitRequest = new ArrayList<String>( Arrays.asList(
            "Accept-Encoding: gzip;q=1.0,deflate;q=0.6,identity;q=0.3",
            "Accept: */*",
            "User-Agent: Ruby",
            "Connection: close",
            "Host: 127.0.0.1:5000"));

    Map<String, String> expectedHeaders = Map.of(
            "Accept-Encoding", "gzip;q=1.0,deflate;q=0.6,identity;q=0.3",
            "Accept", "*/*",
            "User-Agent", "Ruby",
            "Connection", "close",
            "Host", "127.0.0.1:5000");

    assertEquals(expectedHeaders, parseHeaders.getHeaderKeyValuePairs(splitRequest));
  }
}