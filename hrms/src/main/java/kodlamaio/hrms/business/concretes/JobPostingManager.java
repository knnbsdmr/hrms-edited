package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;

@Service
public class JobPostingManager implements JobPostingService {

	private JobPostingDao jobPostingDao;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao) {
		super();
		this.jobPostingDao = jobPostingDao;
	}

	@Override
	public DataResult<List<JobPosting>> getActivesByEmployers_EmployerId(int employerId) {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getActivesByEmployer_EmployerId(employerId),"Şirketlere göre iş ilanı getirildi");
	}

	@Override
	public DataResult<List<JobPosting>> getAllActives() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getAllActives(),"Aktif iş ilanları getirildi");
	}

	@Override
	public DataResult<List<JobPosting>> getAllActivesDateSorted() {
		Sort sort=Sort.by(Direction.ASC, "releaseDate");
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(sort),"Tarihe göre sıralandı");
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {
		return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(),"Aktif iş ilanları şirketlere göre getirildi");
	}

	@Override
	public Result add(JobPosting jobPosting) {
		this.jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı eklendi");
	}

	@Override
	public Result updateStatus(int jobPostingId) {
JobPosting result=this.jobPostingDao.getById(jobPostingId);
		
		if(result.isStatus()) {
			result.setStatus(false);
			this.jobPostingDao.save(result);
		}else {
			result.setStatus(true);
		 this.jobPostingDao.save(result);
		}
		return new SuccessResult("Aktiflik durumu değiştirildi");
	}

}
