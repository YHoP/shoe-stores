import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class BrandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Brand.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Brand firstBrand = new Brand("Asics");
    Brand secondBrand = new Brand("Asics");
    assertTrue(firstBrand.equals(secondBrand));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Brand myBrand = new Brand("Nike");
    myBrand.save();
    assertTrue(Brand.all().get(0).equals(myBrand));
  }

  @Test
  public void find_findBrandInDatabase_true() {
    Brand myBrand = new Brand("Skora");
    myBrand.save();
    Brand savedBrand = Brand.find(myBrand.getId());
    assertTrue(myBrand.equals(savedBrand));
  }

  @Test
  public void addStore_addsStoreToBrand() {
    Brand myBrand = new Brand("Brooks");
    myBrand.save();
    Store myStore = new Store("Foot Traffic");
    myStore.save();
    myBrand.addStore(myStore.getId());
    Store savedStore = myBrand.getStores().get(0);
    assertTrue(myStore.equals(savedStore));
  }

  @Test
  public void getStores_returnsAllStores_List() {
    Brand myBrand = new Brand("New Balance");
    myBrand.save();
    Store myStore = new Store("Fleet Feet Sports PDX");
    myStore.save();
    myBrand.addStore(myStore.getId());
    List<Store> savedStores = myBrand.getStores();
    assertEquals(savedStores.size(), 1);
  }

  @Test
  public void count_returnCorrectStoreCountFromDatabase_Int() {
    Brand myBrand = new Brand("Asics");
    myBrand.save();
    Store firstStore = new Store("Fit Right NW");
    firstStore.save();
    myBrand.addStore(firstStore.getId());
    Store secondStore = new Store("Fleet Feet");
    secondStore.save();
    myBrand.addStore(secondStore.getId());
    assertEquals(2, myBrand.count());
  }

  @Test
  public void delete_deleteSoldStoreFromDatabase() {
    Brand myBrand = new Brand("Asics");
    myBrand.save();
    Store myStore = new Store("Fit Right NW");
    myStore.save();
    myBrand.addStore(myStore.getId());
    myBrand.deleteSoldStore(myStore.getId());
    assertEquals(0, myBrand.count());
  }

}
