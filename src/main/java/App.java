import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {

    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  model.put("username", request.session().attribute("username")); //session recorder

  model.put("template", "templates/welcome.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

    post("/welcome", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();

  String inputtedUsername = request.queryParams("username");
  request.session().attribute("username", inputtedUsername);
  model.put("username", inputtedUsername);

  model.put("template", "templates/welcome.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

    get("/favorite_photos", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/favorite_photos.vtl" );
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/form", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/form.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/greeting_card", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    String recipient = request.queryParams("recipient"); //getting input
    String sender = request.queryParams("sender"); //getting input
    model.put("recipient", recipient); //displaying the data
    model.put("sender", sender); //displaying the data
    model.put("template", "templates/greeting_card.vtl");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

}