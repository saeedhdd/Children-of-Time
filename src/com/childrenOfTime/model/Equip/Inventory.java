package com.childrenOfTime.model.Equip;

import com.childrenOfTime.exceptions.NotEnoughInventorySpaceException;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.sun.org.apache.xml.internal.utils.SerializableLocatorImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mohammadmahdi on 5/8/16.
 */
public class Inventory implements Serializable {

    private int maxCapacity;
    private ArrayList<Item> items;

    public Inventory(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        items = new ArrayList<>(maxCapacity);
    }

    public int getAvailableCapacity(){
        int sum=0;
        for (int i = 0; i < items.size(); i++) {
            if (this.items.get(i) != null) sum++;
        }
        return maxCapacity-sum;
    }

    public void addToInventoryIfYouCan(Item item) {
        if (getAvailableCapacity() == 0) throw new NotEnoughInventorySpaceException("Not Enough Inventory Space !!!");
        for (Item i : this.items) {
            if (i.getName().equals(item.getName())) item.setId(i.getId() + 1);
        }
        this.items.add(item);
    }


    public void removeFromInventoryIfYouCan(Item item) {
        Iterator<Item> itr = this.items.iterator();
        while (itr.hasNext()) {
            Item nextItem = itr.next();
            if (nextItem.getName().equals(item.getName()) && nextItem.getId().equals(item.getId())) ;
            itr.remove();

        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

}
