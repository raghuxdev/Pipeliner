package org.furin.spis.action;

import java.util.List;

public interface Actionable {
    List<Actionable> getActions();
}
