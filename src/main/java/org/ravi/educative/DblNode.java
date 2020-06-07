package org.ravi.educative;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {"value"})
public final class DblNode<T> {
    private DblNode<T> next;
    private DblNode<T> prev;
    private T value;

    public DblNode(T value) {
        this.value = value;
    }
}
