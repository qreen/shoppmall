package com.shinkeer.shoppmall.dao;

import com.shinkeer.shoppmall.base.rep.JapRep;
import com.shinkeer.shoppmall.base.rep.JpaSpec;
import com.shinkeer.shoppmall.base.service.Ser;
import com.shinkeer.shoppmall.dto.MerchandiseDTO;
import com.shinkeer.shoppmall.dto.UserDTO;
import com.shinkeer.shoppmall.entity.Merchandise;
import com.shinkeer.shoppmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MerchandiseDao extends JapRep<Merchandise,MerchandiseDTO> {
}
