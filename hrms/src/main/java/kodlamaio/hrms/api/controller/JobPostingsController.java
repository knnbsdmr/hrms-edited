package kodlamaio.hrms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/jobpostings")
public class JobPostingsController {
	private JobPostingService jobPostingService;

	@Autowired
	public JobPostingsController(JobPostingService jobPostingService) {
		super();
		this.jobPostingService = jobPostingService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobPosting>> getAll() {
		return this.jobPostingService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobPosting jobPosting) {
		return this.jobPostingService.add(jobPosting);
	}
	
	@GetMapping("/getactivesbyemployerid")
	public DataResult<List<JobPosting>> getActivesByEmployerId(int employerId) {

		return this.jobPostingService.getActivesByEmployers_EmployerId(employerId);

	}
	
	@GetMapping("/getactivejobpositions")
	public DataResult<List<JobPosting>> getActiveJobPositions() {

		return this.jobPostingService.getAllActives();

	}
	
	@GetMapping("/getsorteddate")
	public DataResult<List<JobPosting>> getSortedDate() {

		return this.jobPostingService.getAllActivesDateSorted();

	}
	
	@PostMapping("/chancestatus")
	public Result updateStatus(int jobPostingId) {

		return this.jobPostingService.updateStatus(jobPostingId);

	}
	
}
