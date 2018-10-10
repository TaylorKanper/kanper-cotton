package com.kanper.repository;

import com.kanper.bean.MemberBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<MemberBean, Long> {
}
