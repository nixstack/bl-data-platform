package com.nixstack.service.logger.mock.util;

import java.util.Random;

public class RandomNum {
    public static final int getRandomNum(int from, int to) {
        return from + new Random().nextInt(to - from + 1);
    }
}
