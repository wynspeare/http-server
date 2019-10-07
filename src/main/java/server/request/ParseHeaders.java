package server.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ParseHeaders {

  public ArrayList<String> splitRequest(String incomingRequest) {
    String[] requestHeaders = incomingRequest.split("\n");
    ArrayList<String> headers = new ArrayList<String>(Arrays.asList(requestHeaders));
    return headers;
  }

  public HashMap<String, String> getHeaderKeyValuePairs(ArrayList<String> headers) {
    HashMap<String, String> splitHeaders = new HashMap<String, String>();
    for (String header : headers) {
      splitHeaders.put(header.split(": ")[0], header.split(": ")[1]);
    }
    return splitHeaders;
  }
}