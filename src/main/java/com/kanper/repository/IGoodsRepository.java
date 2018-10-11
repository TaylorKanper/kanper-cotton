package com.kanper.repository;

import com.kanper.bean.GoodsBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGoodsRepository extends JpaRepository<GoodsBean, Long> {
    List<GoodsBean> findAllBySecondCategory_Id(Long secondCategory_id);
    List<GoodsBean> findAllBySecondCategory_FirstCategory_Id(Long secondCategory_firstCategory_id);
}
