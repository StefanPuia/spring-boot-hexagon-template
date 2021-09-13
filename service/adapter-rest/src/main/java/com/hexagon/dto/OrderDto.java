package com.hexagon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;

@Immutable
@Style(init = "set*")
@JsonDeserialize(as = ImmutableOrderDto.class)
public abstract class OrderDto {

  @JsonProperty("product-id")
  public abstract String productId();

  public abstract double getTotal();
}
