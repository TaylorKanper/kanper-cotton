package com.kanper.repository;

import com.kanper.bean.SupplierBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<SupplierBean, Long> {
}
