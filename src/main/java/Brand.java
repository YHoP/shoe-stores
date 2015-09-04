import java.util.List;
import org.sql2o.*;

public class Brand {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Brand(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherStore){
    if (!(otherStore instanceof Brand)) {
      return false;
    } else {
      Brand newBrand = (Brand) otherBrand;
      return this.getName().equals(newBrand.getName()) &&
             this.getId() == newBrand.getId();
    }
  }

  public static List<Brand> all() {
    String sql = "SELECT * FROM brands ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Brand.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Brand find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM brands where id=:id";
      Brand brand = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Brand.class);
      return brand;
    }
  }

  public void addStore(int store_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands_stores (brand_id, store_id) VALUES (:id, :store_id)";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("store_id", store_id)
        .executeUpdate();
    }
  }

  public List<Store> getStores() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT store.* FROM brands JOIN brands_stores ON (brands.id = brands_stores.brand_id) JOIN stores ON (brands_stores.store_id = stores.id) WHERE brands.id =:id ORDER BY name";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Store.class);
    }
  }

  public int count() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM brands_stores where brand_id =:id";
      return (int) con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Integer.class);
    }
 }

}
