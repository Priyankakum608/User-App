package in.ashikit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashikit.binding.SearchCriteria;
import in.ashikit.entity.StudentEnq;
import in.ashikit.repo.StudentEnqRepo;
import in.ashikit.service.EnqueryService;
@Service
public class EnqueryServiceImpl implements EnqueryService {
	
	@Autowired
	private StudentEnqRepo srepo;

	@Override
	public boolean addEnq(StudentEnq se) {
		StudentEnq savedEnq=srepo.save(se);
		return	savedEnq.getEnqId()!=null;
	
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria sc) {
		StudentEnq enq=new StudentEnq();
		enq.setCid(cid);
		if(sc.getClassMode()!=null && !sc.getClassMode().equals("")) {
			enq.setClassMode(sc.getClassMode());
		}
		if(sc.getCourseName()!=null && !sc.getClassMode().equals("")){
			enq.setCourseName(sc.getCourseName());
		}
		if(sc.getEnqStatus()!=null && !sc.getEnqStatus().equals("")){
			enq.setEnqStatus(sc.getEnqStatus());
		}
		Example<StudentEnq> of=Example.of(enq);
	List<StudentEnq>  enquiries=srepo.findAll(of);
	
		return enquiries;
	}

}




