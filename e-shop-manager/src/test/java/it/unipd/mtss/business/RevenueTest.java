////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;

import it.unipd.mtss.model.Order;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RevenueTest {
  private EItem keyboard;
  private EItem motherboard;
  private EItem mouse;
  private EItem processor;

  private Revenue revenue;
  private User user;

  private ArrayList<EItem> emptyList;
  private ArrayList<EItem> nullList;
  private ArrayList<EItem> itemsList;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);

    emptyList = new ArrayList<EItem>();
    itemsList = new ArrayList<EItem>();
    miceList = new ArrayList<EItem>();
    nullList = new ArrayList<EItem>();

    itemsList.add(keyboard);
    itemsList.add(motherboard);
    itemsList.add(mouse);
    itemsList.add(processor);

    miceList.add(anotherMouse);
    for (int i = 0; i < 10 - 1; i++) {
      miceList.add(mouse);
    }

    nullList.add(keyboard);
    nullList.add(null);

    user = new User("MarioRossi", "Mario", "Rossi", 45);
    revenue = new Revenue();
  }


  @Test(expected = BillException.class)
  public void getTotalNullTest() {
    revenue.getOrderPrice(null, user);
  }

  @Test(expected = BillException.class)
  public void getTotalEmptyArrayListTest() {
    revenue.getOrderPrice(emptyList, user);
  }

  @Test(expected = BillException.class)
  public void getTotalNullInsideArrayListTest() {
    revenue.getOrderPrice(nullList, user);
  }

  @Test(expected = BillException.class)
  public void getSaleIf5ProcessorNullTest() {
    revenue.getSaleIf5Processor(null);
  }

  @Test(expected = BillException.class)
  public void getSaleIf5ProcessorEmptyListTest() {
    revenue.getSaleIf5Processor(emptyList);
  }

  @Test(expected = BillException.class)
  public void getSaleIf5ProcessorListContainsNullObjectTest() {
    revenue.getSaleIf5Processor(nullList);
  }

  @Test
  public void getTotalTest() {
    assertEquals(1205.57, revenue.getOrderPrice(itemsList, user), 0.01);
  }

  @Test
  public void getTotalWithProcessorsSaleTest() {
    ArrayList<EItem> processors6List = new ArrayList<>();
    processors6List.add(new EItem("Intel i1", 49.99, itemType.Processor));
    processors6List.add(new EItem("Intel i2", 79.99, itemType.Processor));
    processors6List.add(new EItem("Intel i3", 19.99, itemType.Processor));
    processors6List.add(new EItem("Intel i4", 29.99, itemType.Processor));
    processors6List.add(new EItem("Intel i5", 109.99, itemType.Processor));
    processors6List.add(new EItem("Intel i6", 89.99, itemType.Processor));
    assertEquals(369.95, revenue.getOrderPrice(processors6List, user), 0.1);
  }

  @Test
  public void getTotalWithNoProcessorSaleTest() {
    ArrayList<EItem> processor4List = new ArrayList<>();
    processor4List.add(new EItem("Intel i7", 99.99, itemType.Processor));
    processor4List.add(new EItem("Intel i8", 29.99, itemType.Processor));
    processor4List.add(new EItem("Intel i9", 69.99, itemType.Processor));
    processor4List.add(new EItem("Intel i10", 19.99, itemType.Processor));
    assertEquals(219.96, revenue.getOrderPrice(processor4List, user), 0.1);
  }

  @Test(expected = BillException.class)
  public void freeCheapestMouseIf10MiceNullTest() {
    revenue.freeItemIf10Mice(null);
  }

  @Test
  public void freeCheapestMouseIf10MiceOrLessTest() {
      assertEquals(109.66, revenue.getOrderPrice(miceList, user), 0.01);
  }

  @Test
  public void freeCheapestMouseIfMoreThan10MiceTest() {
      miceList.add(anotherMouse);
      assertEquals(119.42, revenue.getOrderPrice(miceList, user), 0.1);
  }

}
