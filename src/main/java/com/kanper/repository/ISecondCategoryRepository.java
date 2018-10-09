package com.kanper.repository;

import com.kanper.bean.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISecondCategoryRepository extends JpaRepository<SecondCategory, Long> {
    List<SecondCategory> getAllByFirstCategory_Id(Long firstCategory_id);
}
