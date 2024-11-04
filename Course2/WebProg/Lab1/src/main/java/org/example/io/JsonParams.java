package org.example.io;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record JsonParams(int x, int y, int r) {
    @JsonCreator
    public JsonParams(@JsonProperty("x") int x,
                      @JsonProperty("y") int y,
                      @JsonProperty("r") int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}
