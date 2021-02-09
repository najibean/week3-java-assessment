package com.belajar.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajar.springboot.model.Member;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.payload.MemberPayload;
import com.belajar.springboot.repository.MemberRepo;

@RestController
@RequestMapping(path = "/member")
public class MemberController {
	@Autowired
	MemberRepo memberRepo;
	
	@GetMapping(path = "", produces = "application/json")
	public String home() {
		return "Hallo, welcome to /member path";
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Member member = memberRepo.findById(id).orElse(null);
		
		try {
			if(member == null) {
				return new ResponseEntity<ErrorResponse>(new ErrorResponse(
						"ID is null", 
						"ID yang dimasukkan tidak tersedia"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@GetMapping(path = "/getAll", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Member> members = memberRepo.findAll();
		
//		List<MemberPayload> response = new ArrayList<>();
//		for(Member member : members) {
//			response.add(new MemberPayload(
//					member.getId(),
//					member.getName(),
//					member.getAddress(),
//					member.getNik(),
//		}
		return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
	}
	
	@PostMapping(path = "/create", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> createMember(@RequestBody MemberPayload payload) {
		
		try {
			Member newMember = new Member(payload.getFirstName(), payload.getSurname(), payload.getAddress(), payload.getContact(), payload.getExpires());
			memberRepo.save(newMember);
		}catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getLocalizedMessage(), 
					"Your request can't be proceeded"), HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<MemberPayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateMember(@PathVariable("id") Integer id, @RequestBody MemberPayload payload) {
		
		Member existMember = memberRepo.findById(id).orElse(null);
		if(existMember == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existMember.setFirstName(payload.getFirstName());
		existMember.setSurname(payload.getSurname());
		existMember.setAddress(payload.getAddress());
		existMember.setContact(payload.getContact());
		existMember.setExpires(payload.getExpires());
		
		memberRepo.save(existMember);
		return new ResponseEntity<Member>(existMember, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteMember(@PathVariable("id") Integer id) {
		Member member = memberRepo.findById(id).orElse(null);

		if(member == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Member not found", 
					"ID tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		memberRepo.deleteById(id);
		return new ResponseEntity<String>("Member has been deleted", HttpStatus.OK);
	}
	
}
