package com.example.angul.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.angul.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByEmail(String email);

	@Modifying
	@Query("update User u set u.salary=?2 where u.id=?1")
	void updateById(int id, double salary);

}
