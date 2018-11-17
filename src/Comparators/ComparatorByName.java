package Comparators;

import Byeke.Tags.Nameable;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Nameable> {
    @Override
    public int compare(Nameable o1, Nameable o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
