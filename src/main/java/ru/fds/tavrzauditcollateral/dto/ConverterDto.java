package ru.fds.tavrzauditcollateral.dto;

import java.util.Collection;
import java.util.stream.Collectors;

public interface ConverterDto<E, D> {
    E toEntity(D dto);
    D toDto(E entity);

    default Collection<E> toEntity(Collection<D> dtoCollection){
        return dtoCollection.stream().map(this::toEntity).collect(Collectors.toList());
    }

    default Collection<D> toDto(Collection<E> entityCollection){
        return entityCollection.stream().map(this::toDto).collect(Collectors.toList());
    }
}
