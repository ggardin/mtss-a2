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

    if(itemsOrdered.size() > 30) {
      throw new BillException("Non è possibile eseguire un ordinazione con più di 30 elementi.");
    }

    double orderPrice = 0.0;

    for (EItem item: itemsOrdered) {
      if (item == null) {
        throw new BillException("La lista contiene un oggetto nullo.");
      }
      orderPrice += item.getPrice();
    }
    return orderPrice;
  }
}

