package com.kanper.repository;

import com.kanper.bean.GoodsBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGoodsRepository extends JpaRepository<GoodsBean, Long> {
}
