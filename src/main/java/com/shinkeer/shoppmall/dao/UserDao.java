package com.shinkeer.shoppmall.dao;

import com.shinkeer.shoppmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {

}
