package in.ashikit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashikit.binding.Dashboard;
import in.ashikit.entity.Counsellor;
import in.ashikit.entity.StudentEnq;
import in.ashikit.repo.ConsellorRepo;
import in.ashikit.repo.StudentEnqRepo;
import in.ashikit.service.CounsellorService;
import in.ashikit.util.EmailUtil;
@Service
public class CounsellorServiceImpl implements CounsellorService {
	@Autowired
	private ConsellorRepo crepo;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private StudentEnqRepo studentEnqRepo;
	
	@Override
	public String saveCounsellor(Counsellor c) {
		Counsellor obj=crepo.findByEmail(c.getEmail());
		if(obj!=null) {
			return "Duplicate email";
		}
		Counsellor saveObj=crepo.save(c);
		if(saveObj.getCid()!=null) {
			return "Registration Success";
		}
		return "Registraion failed";
	}

	@Override
	public Counsellor checkLogin(String email, String pwd) {
		return crepo.findByEmailAndPwd(email, pwd);
		 
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor c=crepo.findByEmail(email);
		if(c==null) {
			return false;
		}
		String subject="Recover Password-Priyanka IT";
		String body="<h1>Your Password:" +c.getPwd()+"</h1>";
		return emailUtil.sendEmail(subject, body, email);  
	}

	@Override
	public Dashboard getDashboardInfo(Integer cid) {
		List<StudentEnq> allEnqs=studentEnqRepo.findByCid(cid);
		int enrolledEnqs=allEnqs.stream().filter(e->e.getEnqStatus().equals("Enrolled"))
				.collect(Collectors.toList()).size();
		
		Dashboard resp=new Dashboard();
		resp.setTotalEnq(allEnqs.size());
		resp.setEnrollerdEnq(enrolledEnqs);
		resp.setLostEnq(allEnqs.size()- enrolledEnqs);
		
		return resp;
	}

}
