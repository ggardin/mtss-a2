////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EItemTest {

  private EItem keyboard;
  private EItem motherboard;
  private  EItem mouse;
  private EItem processor;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);
  }

  @Test
  public void constructorEItemNotNullTest() {
    assertNotNull(keyboard);
    assertNotNull(motherboard);
    assertNotNull(mouse);
    assertNotNull(processor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nomeEItemNullTest() {
    new EItem(null, 5f, itemType.Keyboard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nomeEItemEmptyTest() {
    new EItem("", 5f, itemType.Keyboard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void priceEItemIsNegativeTest() {
    new EItem("Logitech", -1f, itemType.Keyboard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void priceEItemIsZeroTest() {
    new EItem("Logitech", 0f, itemType.Keyboard);
  }

  @Test(expected = IllegalArgumentException.class)
  public void itemTypeEItemIsNullTest() {
    new EItem("Logitech", 1f, null);
  }

  @Test
  public void itemGetNameTest() {
    assertEquals("Logitech x 300", keyboard.getName());
    assertEquals("RoG zy 1080", motherboard.getName());
    assertEquals("Asus L3", mouse.getName());
    assertEquals("Intel i5", processor.getName());
  }

  @Test
  public void itemGetPriceTest() {
    assertEquals(45.6, keyboard.getPrice(), 0.0);
    assertEquals(1099.99, motherboard.getPrice(), 0.0);
    assertEquals(9.99, mouse.getPrice(), 0.0);
    assertEquals(49.99, processor.getPrice(), 0.0);
  }

  @Test
  public void itemGetItemTest() {
    assertEquals(itemType.Keyboard, keyboard.getItem());
    assertEquals(itemType.Motherboard, motherboard.getItem());
    assertEquals(itemType.Mouse, mouse.getItem());
    assertEquals(itemType.Processor, processor.getItem());
  }
}