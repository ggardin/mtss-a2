////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;

import java.util.List;
import java.util.ArrayList;

public class Revenue implements Bill {

  @Override
  public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    if(itemsOrdered.size() == 0) {
      throw new BillException("La lista degli EItems non può essere vuota.");
    }

    double orderPrice = 0.0;
    double totalDiscount = 0.0;
    ArrayList<EItem> processorList = new ArrayList<>();
    ArrayList<EItem> mouseList = new ArrayList<>();
    ArrayList<EItem> keyboardList = new ArrayList<>();

    for (EItem item: itemsOrdered) {
      if (item == null) {
        throw new BillException("La lista contiene un oggetto nullo.");
      }
      switch (item.getItem()) {
        case Processor:
          processorList.add(item);
          break;
        case Keyboard:
          keyboardList.add(item);
          break;
        case Mouse:
          mouseList.add(item);
          break;
      }
      orderPrice += item.getPrice();
    }

    if (keyboardList.size() != 0 && keyboardList.size() == mouseList.size()) {
      totalDiscount += freeObject(keyboardList, mouseList);
    }

    totalDiscount += getSaleIf5Processor(itemsOrdered);
    totalDiscount += freeCheapestMouseIfMoreThan10Mice(itemsOrdered);
    totalDiscount += offerDiscountIfTotalOverThreshold(itemsOrdered);
    return orderPrice - totalDiscount;
  }

  double getSaleIf5Processor(List<EItem> itemsOrdered) {
    if (itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    if (itemsOrdered.size() == 0) {
      throw new BillException("La lista degli EItems non può essere vuota.");
    }

    double min = Double.MAX_VALUE;
    ArrayList<EItem> processors = new ArrayList<>();
    for (EItem item : itemsOrdered) {
      if(item == null) {
        throw new BillException("EItem non deve essere nullo.");
      }
      if (item.getItem().equals(itemType.Processor)) {
        if (item.getPrice() <= min) {
          min = item.getPrice();
        }
        processors.add(item);
      }
    }

    if (processors.size() > 5) {
      return min * 0.5;
    }

    return 0;
  }

  double freeCheapestMouseIfMoreThan10Mice(List<EItem> itemsOrdered) {
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    double minPrice;
    minPrice = 0.0;
    ArrayList<EItem> mice = new ArrayList<>();

    for (EItem item : itemsOrdered) {
      if (item.getItem().equals(itemType.Mouse)) {
        mice.add(item);
      }
    }

    if (mice.size() > 10) {
      minPrice = mice.get(0).getPrice();
      for (int i = 0; i < mice.size(); i++) {
        if (mice.get(i).getPrice() < minPrice) {
          minPrice = mice.get(i).getPrice();
        }
      }
    }

    return minPrice;

  }

  double freeObject(List<EItem> list1, List<EItem> list2) {
    double min1 = Double.MAX_VALUE;
    double min2 = Double.MAX_VALUE;

    for (EItem item : list1) {
      if (item.getPrice() <= min1) {
        min1 = item.getPrice();
      }
    }

    for (EItem item : list2) {
      if (item.getPrice() <= min1) {
        min2 = item.getPrice();
      }
    }

    return Math.min(min1, min2);
  }

  double offerDiscountIfTotalOverThreshold(List<EItem> itemsOrdered) {
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    double total;
    double threshold;
    double discount;

    total = 0.0;
    threshold = 1000.0;
    discount = 0.0;

    for (EItem item : itemsOrdered) {
      total += item.getPrice();
    }
    if (total > threshold) {
      discount = total * 0.1;
    }

    return discount;
  }


}

