import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Store.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Store firstStore = new Store("Foot Traffic");
    Store secondStore = new Store("Foot Traffic");
    assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Store myStore = new Store("Road Runner Sports");
    myStore.save();
    Store savedStore = Store.all().get(0);
    assertTrue(savedStore.equals(myStore));
  }

  @Test
  public void save_assignsIdToObject() {
    Store myStore = new Store("Fleet Feet");
    myStore.save();
    Store savedStore = Store.all().get(0);
    assertEquals(myStore.getId(), savedStore.getId());
  }

  @Test
  public void find_findsStoreInDatabase_true() {
    Store myStore = new Store("Foot Traffic");
    myStore.save();
    Store savedStore = Store.find(myStore.getId());
    assertTrue(myStore.equals(savedStore));
  }

  @Test
  public void update_updateStoreCorrectly_true(){
    Store myStore = new Store("Fit Right NW");
    myStore.save();
    myStore.update("Fleet Feet");
    assertTrue(Store.all().get(0).getName().equals("Fleet Feet"));
  }

  @Test
  public void delete_deletesAllStoresAndListsAssoicationes() {
    Brand myBrand = new Brand("Saucony");
    myBrand.save();
    Store myStore = new Store("Pace Setter Athletic");
    myStore.save();
    myStore.addBrand(myBrand.getId());
    myStore.delete();
    assertEquals(myBrand.getStores().size(), 0);
  }

}
