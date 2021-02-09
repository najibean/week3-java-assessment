package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Member;

public interface MemberRepo extends JpaRepository<Member, Integer>{
	public Member findById(int id);
}
