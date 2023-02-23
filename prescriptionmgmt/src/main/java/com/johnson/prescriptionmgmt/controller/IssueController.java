package com.johnson.prescriptionmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.service.IssueService;

/**
 * Controller for endpoints related to issues reported by users
 * @author Hillary
 *
 */
@Controller
public class IssueController {

	@Autowired
	IssueService issueService;
	
	@RequestMapping("/viewissues")
	public String viewUnresolvedIssues(Model model) {
		List<Issue> unresolved = issueService.findAllUnresolvedIssues();
		if (unresolved.isEmpty()) {
			//model.addAttribute("No Issues Reported", "There are currently no unresolved issues to report.");
			return "issues";
		} else {
			model.addAttribute("problems", unresolved);
			return "issues";
		}
 
	}
	
	@GetMapping("/issues/delete/{id}")
	public String deleteIssue(@PathVariable("id") Integer id) {
		issueService.deleteIssue(id);
		return "redirect:/viewissues";
	}
	
	
	@PostMapping("/issues/update/{id}")
	public RedirectView updateIssue(@PathVariable("id") Integer id) {
		Issue issue = issueService.findById(id);
		issue.setResolved(true);
		issueService.saveIssue(issue);
		return new RedirectView("/viewissues");
	}
	
	@RequestMapping("/viewissues/all")
	public String viewAllIssues(Model model) {
		List<Issue> allIssues = issueService.findAllIssues();
		if (allIssues.isEmpty()) {
			//model.addAttribute("No Issues Reported", "There are currently no issues to report.");
			return "issues";
		} else {
			model.addAttribute("problems", allIssues);
			return "issues";
		}
	}
		
}
