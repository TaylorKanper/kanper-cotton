package com.kanper.repository;

import com.kanper.bean.FirstCategoryBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFirstCategoryRepository extends JpaRepository<FirstCategoryBean, Long> {
}
