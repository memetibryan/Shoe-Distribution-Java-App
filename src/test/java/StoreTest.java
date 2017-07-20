import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StoreTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/shoe_java_test", null, null);
  } //testing if the database is connected

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {   //clear the database
      String sql = "DELETE FROM stores *;";
      con.createQuery(sql).executeUpdate();
    }
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
  Store firstStore = new Store("Vila Rosa");
  Store secondStore = new Store("Vila Rosa");
  assertTrue(firstStore.equals(secondStore));
  }

  @Test
  public void save_returnsTrueIfNamesAretheSame() {
  Store myStore = new Store("Vila Rosa");
  myStore.save();
  assertTrue(Store.all().get(0).equals(myStore));
  }

  @Test
  public void all_returnsAllInstancesOfStore_true() {
    Store firstStore = new Store("Kempinski");
    firstStore.save();
    Store secondStore = new Store("Vila Rosa");
    secondStore.save();
    assertEquals(true, Store.all().get(0).equals(firstStore));
    assertEquals(true, Store.all().get(1).equals(secondStore));
  }

  @Test
  public void save_assignsIdToObject() {
  Store myStore = new Store("Vila Rosa");
  myStore.save();
  Store savedStore = Store.all().get(0);
  assertEquals(myStore.getId(), savedStore.getId());
  }

  @Test
  public void getId_tasksInstantiateWithAnID() {
    Store myStore = new Store("Vila Rosa");
    myStore.save();
    assertTrue(myStore.getId() > 0);
  }

  @Test
  public void find_returnsStoreWithSameId_secondStore() {
    Store firstStore = new Store("Mow the lawn");
    firstStore.save();
    Store secondStore = new Store("Buy groceries");
    secondStore.save();
    assertEquals(Store.find(secondStore.getId()), secondStore);
  }
}