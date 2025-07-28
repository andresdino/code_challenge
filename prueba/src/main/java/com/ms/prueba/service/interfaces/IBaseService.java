package com.ms.prueba.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T> {

    List<T> all() throws Exception;
    Optional<T> findById(Long id) throws Exception;
    T save(T entity) throws Exception;
    void update(Long id, T entity) throws Exception;
    void delete(Long id) throws Exception;
}
