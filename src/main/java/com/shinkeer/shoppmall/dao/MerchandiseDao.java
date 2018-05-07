package com.shinkeer.shoppmall.dao;

import com.shinkeer.shoppmall.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MerchandiseDao extends JpaRepository<Merchandise,String>,JpaSpecificationExecutor<Merchandise>{
}
