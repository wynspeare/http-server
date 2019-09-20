package server.handlers;

import server.Response;
import server.request.Request;

public interface IHandler {
  Response buildResponse(Request request);
}
