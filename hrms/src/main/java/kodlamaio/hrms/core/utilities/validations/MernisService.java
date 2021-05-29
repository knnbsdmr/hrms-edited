package kodlamaio.hrms.core.utilities.validations;

import kodlamaio.hrms.core.utilities.services.MernisCheckService;
import kodlamaio.hrms.entities.concretes.Candidate;

public class MernisService implements MernisCheckService<Candidate> {

	@Override
	public boolean isMernis(Candidate candidate) {
		return candidate.getIdentificationNumber().length()==11;
	}

}
