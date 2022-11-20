package server;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    manager(1),
    technician(2),
    power(3),
    ordinary(4);

    Role(int value) {
        this.value = value;
    }

    private final int value;

    public int getValue() {
        return value;
    }

    public static Operation getFromValue(int value) {
        Optional<Operation> res = Arrays.stream(Operation.values()).filter(o -> o.getValue() == value).findFirst();
        return res.orElse(null);
    }

}
