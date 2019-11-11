package com.nixstack.service.logger.mock.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomOptGrp<T> {
    int totalWeight = 0;
    List<RandomOpt> optList = new ArrayList();

    public RandomOptGrp(RandomOpt<T>... opts) {
        for (RandomOpt opt: opts) {
            totalWeight += opt.getWeight();
            for (int i = 0; i < opt.getWeight(); i++) {
                optList.add(opt);
            }
        }
    }

    public RandomOpt<T> getRandomOpt() {
        int i = new Random().nextInt(totalWeight);

        return optList.get(i);
    }
}
