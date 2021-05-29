package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingService {
	DataResult<List<JobPosting>> getActivesByEmployers_EmployerId(int employerId);

	DataResult<List<JobPosting>> getAllActives();

	DataResult<List<JobPosting>> getAllActivesDateSorted();

	DataResult<List<JobPosting>> getAll();

	Result add(JobPosting jobPosting);

	Result updateStatus(int jobPostingId);
}
