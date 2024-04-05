package factory;

import models.herb.Herb;

import java.util.HashSet;
import java.util.Set;

public class HerbFactory {
    public static Set<Herb> createHerbs(int herbAmount) {
        Set<Herb> herbSet = new HashSet<>();
        for (int i = 0; i < herbAmount; i++) {

        }
        return herbSet;
    }
}
