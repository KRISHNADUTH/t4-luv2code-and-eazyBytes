package com.eazybytes.EazyBankBackEndApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eazybytes.EazyBankBackEndApplication.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	
	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();

}