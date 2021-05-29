package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import kodlamaio.hrms.business.abstracts.CandidateService;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.services.MernisCheckService;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;


@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private MernisCheckService<Candidate> mernisCheckService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, MernisCheckService<Candidate> mernisCheckService) {
		super();
		this.candidateDao = candidateDao;
		this.mernisCheckService = mernisCheckService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "İş arayanlar listelendi");
	}

	@Override
	public Result add(@RequestBody Candidate candidate) {
		if (!mernisCheckService.isMernis(candidate)) {
			return new ErrorResult("Kimlik numarası hatalı !");
		} else {
			this.candidateDao.save(candidate);
			return new SuccessResult(
					" İş arayan kullanıcı sisteme eklendi.");
		}
	}

}
