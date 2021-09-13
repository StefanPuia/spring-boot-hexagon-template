package com.hexagon.mapper;

import com.hexagon.domain.common.MapstructConfig;
import com.hexagon.dto.OrderDto;
import com.hexagon.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(config = MapstructConfig.class)
public interface OrderDtoMapper extends Converter<Order, OrderDto> {
  @Override
  @Mapping(target = "total", source = "price")
  OrderDto convert(Order order);
}
