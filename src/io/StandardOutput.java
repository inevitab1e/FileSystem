package io;

import java.io.Serializable;

public class StandardOutput implements Serializable {

    public final String source;
    private final StringBuilder result = new StringBuilder();

    public StandardOutput(String source) {
        this.source = source;
    }

    public StandardOutput write(String str) {
        result.append(str);
        return this;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
