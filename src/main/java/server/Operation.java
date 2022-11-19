package server;

import java.util.Arrays;
import java.util.Optional;

public enum Operation {
    print(1),
    queue(2),
    topQueue(3),
    start(4),
    stop(5),
    restart(6),
    status(7),
    readConfig(8),
    setConfig(9);

    Operation(int value) {
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




