package in.ashikit.service;

import in.ashikit.binding.Dashboard;
import in.ashikit.entity.Counsellor;

public interface CounsellorService {
 public String saveCounsellor(Counsellor c );
 public Counsellor checkLogin(String email, String pwd);
 public boolean recoverPwd(String email);
 public Dashboard getDashboardInfo(Integer cid);
 
}
