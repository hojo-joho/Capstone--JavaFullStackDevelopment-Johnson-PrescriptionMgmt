package com.johnson.prescriptionmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.repository.IssueRepository;

/**
 * Service class for Issues
 * defines JpaRepository methods needed
 * @author Hillary
 *
 */
@Service
public class IssueService {
	
	private final IssueRepository issueRepo;
	
	public IssueService(IssueRepository issueRepo) {
		this.issueRepo=issueRepo;
	}

	
	public void saveIssue(Issue issue) {
		issueRepo.save(issue);
	}
	
	public List<Issue> findAllIssues(){
		return issueRepo.findAll();
	}
	
	public Issue findById(int id) {
		return issueRepo.getReferenceById(id);
	}
	
	public void deleteIssue(int id) {
		issueRepo.deleteById(id);
	}
	
	public List<Issue> findAllUnresolvedIssues(){
		return issueRepo.findAllUnresolvedIssues();
	}
	
}
