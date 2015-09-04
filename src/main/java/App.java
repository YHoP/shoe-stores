import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // get("/", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("stores", Store.all());
    //   model.put("brands", Brand.all());
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/brands", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("brands", Brand.all());
    //   model.put("template", "templates/brands.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/brands/new", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String name = request.queryParams("name");
    //   Brand newBrand = new Brand(name);
    //   newBrand.save();
    //   model.put("brands", Brand.all());
    //   model.put("template", "templates/brands.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/brands/:id", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.params("id"));
    //   Brand brand = Brand.find(brand_id);
    //   model.put("brand", brand);
    //   model.put("allStores", brand.getUnassignedStores());
    //   model.put("stores", brand.getStores());
    //   model.put("template", "templates/brand.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/brands/addStore", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.queryParams("autorId"));
    //   int store_id = Integer.parseInt(request.queryParams("storeId"));
    //   Brand brand = Brand.find(brand_id);
    //   brand.addStore(store_id);
    //   model.put("allStores", Store.all());
    //   response.redirect("/brands/" + brand_id);
    //   return null;
    // });
    //
    // get("/brands/:id/delete", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.params("id"));
    //   Brand brand = Brand.find(brand_id);
    //   brand.delete();
    //   response.redirect("/brands");
    //   return null;
    // });
    //
    // get("/brands/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.params("id"));
    //   Brand brand = Brand.find(brand_id);
    //   model.put("brand", brand);
    //   model.put("template", "templates/updatebrand.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/brands/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.params("id"));
    //   Brand brand = Brand.find(brand_id);
    //   String name = request.queryParams("name");
    //   brand.update(name);
    //   response.redirect("/brands/" + brand_id);
    //   return null;
    // });
    //
    // post("/stores/new", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String title = request.queryParams("title");
    //   Integer brandId = Integer.parseInt(request.queryParams("brandId"));
    //   Store newStore = new Store(title);
    //   newStore.save();
    //   newStore.addBrand(brandId);
    //   response.redirect("/stores/" + newStore.getId());
    //   return null;
    // });
    //
    // get("/stores", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("stores", Store.all());
    //   model.put("brands", Brand.all());
    //   model.put("template", "templates/stores.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    //
    // get("/stores/:id", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int store_id = Integer.parseInt(request.params("id"));
    //   Store store = Store.find(store_id);
    //   model.put("store", store);
    //   model.put("brands", Brand.all());
    //   model.put("template", "templates/store.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/stores/addBrands", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int brand_id = Integer.parseInt(request.queryParams("brand_id"));
    //   int store_id = Integer.parseInt(request.queryParams("store_id"));
    //   Store store = Store.find(store_id);
    //   store.addBrand(brand_id);
    //   model.put("brands", Brand.all());
    //   response.redirect("/stores/" + store_id);
    //   return null;
    // });
    //
    // get("/stores/:id/delete", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int store_id = Integer.parseInt(request.params("id"));
    //   Store store = Store.find(store_id);
    //   store.delete();
    //   response.redirect("/stores");
    //   return null;
    // });
    //
    // get("/stores/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int store_id = Integer.parseInt(request.params("id"));
    //   Store store = Store.find(store_id);
    //   model.put("store", store);
    //   model.put("template", "templates/updatestore.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/stores/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int store_id = Integer.parseInt(request.params("id"));
    //   Store store = Store.find(store_id);
    //   String title = request.queryParams("title");
    //   store.update(title);
    //   response.redirect("/stores/" + store_id);
    //   return null;
    // });
    //
    // post("/stores/addCopies", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int store_id = Integer.parseInt(request.queryParams("store_id"));
    //   int copies = Integer.parseInt(request.queryParams("copies"));
    //   for(int i=1; i<= copies; i++){
    //     Copy newCopy = new Copy(store_id);
    //     newCopy.save();
    //   }
    //   response.redirect("/stores/" + store_id);
    //   return null;
    // });
    //
    // get("/patrons", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("patrons", Patron.all());
    //   model.put("template", "templates/patrons.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/patrons/new", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String name = request.queryParams("name");
    //   Patron newPatron = new Patron(name);
    //   newPatron.save();
    //   response.redirect("/patrons");
    //   return null;
    // });
    //
    // get("/patrons/:id", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int patron_id = Integer.parseInt(request.params("id"));
    //   Patron patron = Patron.find(patron_id);
    //   model.put("patron", patron);
    //   model.put("template", "templates/patron.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/patrons/:id/delete", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int patron_id = Integer.parseInt(request.params("id"));
    //   Patron patron = Patron.find(patron_id);
    //   patron.delete();
    //   response.redirect("/patrons");
    //   return null;
    // });
    //
    // get("/patrons/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int patron_id = Integer.parseInt(request.params("id"));
    //   Patron patron = Patron.find(patron_id);
    //   model.put("patron", patron);
    //   model.put("template", "templates/updatepatron.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/patrons/:id/update", (request,response) ->{
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int patron_id = Integer.parseInt(request.params("id"));
    //   Patron patron = Patron.find(patron_id);
    //   String name = request.queryParams("name");
    //   patron.update(name);
    //   response.redirect("/patrons/" + patron_id);
    //   return null;
    // });

  }
}
