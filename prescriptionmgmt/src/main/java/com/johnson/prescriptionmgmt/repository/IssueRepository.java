package com.johnson.prescriptionmgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.Issue;

/**
 * IssueRepository
 * extends JpaRepository for CRUD operations
 * defines custom query for finding unresolved issues
 * @author Hillary
 *
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{

	@Query("select i from Issue i where i.resolved=0" )
	List<Issue> findAllUnresolvedIssues();
	
}
