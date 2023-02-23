package com.johnson.prescriptionmgmt;

import com.johnson.prescriptionmgmt.repository.IssueRepository;
import com.johnson.prescriptionmgmt.service.IssueService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class IssueServiceTest {
	
	@Mock
	private IssueRepository issueRepo;
	private IssueService issueService;
	
	@BeforeEach
	void setUpService() {
		issueService = new IssueService(issueRepo);
	}
	
	
	@Test
	public void testFindAllUnresolvedIssues() {
		
		issueService.findAllUnresolvedIssues();
		
		Mockito.verify(issueRepo).findAllUnresolvedIssues();
	}
}