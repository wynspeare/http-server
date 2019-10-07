package server;
import HTTPcomponents.Methods;
import server.handlers.IHandler;
import server.request.Request;

import java.util.HashMap;
import java.util.List;

public interface IController {
  Response handle(Request request, HashMap<String, List<HashMap<Methods, IHandler>>> routes);
}
