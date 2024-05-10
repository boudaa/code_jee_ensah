package com.ensah.core.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ensah.core.bo.Role;

public interface IRepositoryDao extends JpaRepository<Role, Long> {

}
