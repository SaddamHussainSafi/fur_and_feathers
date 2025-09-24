package com.furandfeathers.repository;

import com.furandfeathers.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    
    Optional<T> findByIdAndIsDeletedFalse(ID id);
    
    List<T> findAllByIsDeletedFalse();
    
    default void softDelete(T entity) {
        entity.setDeleted(true);
        save(entity);
    }
}
