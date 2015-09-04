import java.util.List;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.rules.ExternalResource;
import org.sql2o.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Shoes Stores");
  }

  @Test
  public void brandIsAddedCorrectly() {
    goTo("http://localhost:4567/brands");
    fill("#name").with("Asics");
    submit("#add_brand");
    assertThat(pageSource()).contains("Asics");
  }

  @Test
  public void storeIsAddedCorrectly() {
    goTo("http://localhost:4567/stores");
    fill("#name").with("Foot Traffic");
    submit("#add_store");
    assertThat(pageSource()).contains("Foot Traffic");
  }

  @Test
  public void brandsAndStoresDisplayedCorrectly() {
    Brand newBrand = new Brand("Nike");
    newBrand.save();
    Store newStore = new Store("Foot Traffic");
    newStore.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Nike");
    assertThat(pageSource()).contains("Foot Traffic");
  }

  @Test
  public void storeIsUpdated() {
    Store newStore = new Store("Foot Traffic");
    newStore.save();
    String storePath = String.format("http://localhost:4567/stores/%d/update", newStore.getId());
    goTo(storePath);
    fill("#name").with("Road Runner Sports");
    submit("#update_name");
    assertThat(pageSource()).contains("Road Runner Sports");
  }

  @Test
    public void storeIsDeleted() {
      Store newStore = new Store("Foot Traffic");
      newStore.save();
      goTo("http://localhost:4567/stores");
      find("a", withText("Delete")).click();
      assertThat(pageSource()).doesNotContain("Foot Traffic");
    }

  @Test
    public void storeIsAddedToBrand() {
      Brand newBrand = new Brand("Asics");
      newBrand.save();
      Store newStore = new Store("Foot Traffic");
      newStore.save();
      newBrand.addStore(newStore.getId());
      goTo("http://localhost:4567/brands/"+ newBrand.getId());
      assertThat(pageSource()).contains("Foot Traffic");
    }

  @Test
    public void brandIsAddedToStore() {
      Brand newBrand = new Brand("Asics");
      newBrand.save();
      Store newStore = new Store("Foot Traffic");
      newStore.save();
      newStore.addBrand(newBrand.getId());
      goTo("http://localhost:4567/stores/"+ newStore.getId());
      assertThat(pageSource()).contains("Asics");
    }

}
