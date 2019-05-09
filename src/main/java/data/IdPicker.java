package data;

import java.security.SecureRandom;

public class IdPicker {

    private final int min = 10000;
    private final int max = 99999;
    private final SecureRandom randomId = new SecureRandom();

    IdPicker() {}

    int pick() {
        if (min >= max) {
            throw new IllegalArgumentException("Min is greater than max.");
        }
        return randomId.nextInt((max - min) + 1) + min;
    }
}
