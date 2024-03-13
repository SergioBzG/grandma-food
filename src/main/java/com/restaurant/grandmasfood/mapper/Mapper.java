package com.restaurant.grandmasfood.mapper;


public interface Mapper<X, Y> {
    Y mapToDto(X x);

    X mapFromDto(Y y);
}
