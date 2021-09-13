package com.hexagon.model;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;

@Immutable
@Style(init = "set*")
public abstract class Order {

  public abstract String getProductId();

  public abstract double getPrice();
}
