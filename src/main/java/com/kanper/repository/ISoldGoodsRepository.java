package com.kanper.repository;

import com.kanper.bean.SoldGoodBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ISoldGoodsRepository extends JpaRepository<SoldGoodBean, Long> {
    List<SoldGoodBean> queryAllByBuyDateBefore(@Param("date") Date date);

    List<SoldGoodBean> queryAllByMemberBeanId(Long memberId);

    List<SoldGoodBean> findAllByBuyDateContains(java.util.Date date);
}
