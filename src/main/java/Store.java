import java.util.List;
import org.sql2o.*;

public class Store {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Store(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object otherStore){
    if (!(otherStore instanceof Store)) {
      return false;
    } else {
      Store newStore = (Store) otherStore;
      return this.getName().equals(newStore.getName()) &&
             this.getId() == newStore.getId();
    }
  }

  public static List<Store> all() {
    String sql = "SELECT * FROM stores ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Store.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stores (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Store find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stores where id =:id";
      Store store = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Store.class);
      return store;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stores SET name =:name WHERE id =:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addBrand(int brand_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO brands_stores (brand_id, store_id) VALUES (:brand_id, :store_id)";
      con.createQuery(sql)
        .addParameter("brand_id", brand_id)
        .addParameter("store_id", id)
        .executeUpdate();
    }
  }

  public List<Brand> getBrands() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT brands.* FROM stores JOIN brands_stores ON (stores.id = brands_stores.store_id) JOIN brands ON (brands_stores.brand_id = brands.id) WHERE stores.id = :id ORDER BY name";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Brand.class);
    }
  }

  public int count() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(*) FROM brands_stores where store_id =:id";
      return (int) con.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(Integer.class);
    }
 }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stores where id =:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      String joinsql = "DELETE FROM brands_stores where store_id =:id";
      con.createQuery(joinsql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void deleteCarryBrand(int brand_id) {
    try(Connection con = DB.sql2o.open()) {
      String joinsql = "DELETE FROM brands_stores where brand_id =:brand_id";
      con.createQuery(joinsql)
        .addParameter("brand_id", brand_id)
        .executeUpdate();
    }
  }

}
