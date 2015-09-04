import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/brands", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Brand newBrand = new Brand(name);
      newBrand.save();
      response.redirect("/brands");
      return null;
    });

    get("/brands/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.params("id"));
      Brand brand = Brand.find(brand_id);
      model.put("brand", brand);
      model.put("stores", Store.all());
      model.put("template", "templates/brand.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/brands/addStore", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.queryParams("brand_id"));
      int store_id = Integer.parseInt(request.queryParams("store_id"));
      Brand brand = Brand.find(brand_id);
      brand.addStore(store_id);
      model.put("stores", Store.all());
      response.redirect("/brands/" + brand_id);
      return null;
    });

    get("/brands/:brand_id/stores/:store_id/delete", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.params("brand_id"));
      int store_id = Integer.parseInt(request.params("store_id"));
      Brand brand = Brand.find(brand_id);
      brand.deleteSoldStore(store_id);
      response.redirect("/brands/" + brand_id);
      return null;
    });

    get("/stores", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("brands", Brand.all());
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Store newStore = new Store(name);
      newStore.save();
      response.redirect("/stores");
      return null;
    });

    get("/stores/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      model.put("store", store);
      model.put("brands", Brand.all());
      model.put("template", "templates/store.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/addBrand", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int brand_id = Integer.parseInt(request.queryParams("brand_id"));
      int store_id = Integer.parseInt(request.queryParams("store_id"));
      Store store = Store.find(store_id);
      store.addBrand(brand_id);
      response.redirect("/stores/" + store_id);
      return null;
    });

    get("/stores/:store_id/brands/:brand_id/delete", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("store_id"));
      int brand_id = Integer.parseInt(request.params("brand_id"));
      Store store = Store.find(store_id);
      store.deleteCarryBrand(brand_id);
      response.redirect("/stores/" + store_id);
      return null;
    });

    get("/stores/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      model.put("store", store);
      model.put("template", "templates/store_update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stores/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      String name = request.queryParams("name");
      store.update(name);
      response.redirect("/stores/" + store_id);
      return null;
    });

    get("/stores/:id/delete", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int store_id = Integer.parseInt(request.params("id"));
      Store store = Store.find(store_id);
      store.delete();
      response.redirect("/stores");
      return null;
    });

  }
}
