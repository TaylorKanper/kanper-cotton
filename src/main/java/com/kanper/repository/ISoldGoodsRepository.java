package com.kanper.repository;

import com.kanper.bean.SoldGoodBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ISoldGoodsRepository extends JpaRepository<SoldGoodBean, Long> {
    @Query("from SoldGoodBean e where e.buyDate>current_date order by e.buyDate")
    List<SoldGoodBean> queryAllToday();

    List<SoldGoodBean> queryAllByMemberBeanId(Long memberId);

    List<SoldGoodBean> findAllByBuyDateBetween(Date start, Date end);

}
