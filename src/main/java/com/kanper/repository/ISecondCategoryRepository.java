package com.kanper.repository;

import com.kanper.bean.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISecondCategoryRepository extends JpaRepository<SecondCategory, Long> {
}
