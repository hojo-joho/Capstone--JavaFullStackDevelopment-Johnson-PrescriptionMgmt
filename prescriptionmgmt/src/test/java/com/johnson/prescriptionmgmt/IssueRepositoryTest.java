package com.johnson.prescriptionmgmt;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.repository.IssueRepository;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IssueRepositoryTest {
	
	
	@Autowired
	IssueRepository issueRepo;
	
	@Test
	public void testFindAllUnresolvedIssues() {
		List<Issue> unresolved = new ArrayList<Issue>();
		
		Issue issue = new Issue();
		issue.setIssueTxt("test1");
		issue.setResolved(true);
		issueRepo.save(issue);
		
		Issue issue2 = new Issue();
		issue2.setIssueTxt("test2");
		issue2.setResolved(false);
		issueRepo.save(issue2);
		
		unresolved = issueRepo.findAllUnresolvedIssues();
		System.out.println(unresolved.toString());
		
		int lastidx = unresolved.size();
		
		Issue last = unresolved.get(lastidx-1);
		
		Assert.assertEquals(last, issue2);
		
	}

}
