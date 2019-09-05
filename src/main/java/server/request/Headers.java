package server.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Headers {

  public List<String> splitHeadersFromRequest(String incomingRequest) {
    String[] request = incomingRequest.split("\n");
    ArrayList<String> headers = new ArrayList<String>(Arrays.asList(request));
    headers.remove(0);
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
