package in.ashikit.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashikit.binding.Dashboard;
import in.ashikit.entity.Counsellor;
import in.ashikit.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorControl {
	@Autowired
	CounsellorService counsellorService;
	@GetMapping("/logout")
	 public String logout(HttpServletRequest req, Model model) {
		HttpSession session=req.getSession(false);
		session.invalidate();
		 model.addAttribute("counsellor", new Counsellor());
		 return "redirect:/";
	 }
	@GetMapping("/")
 public String index(Model model) {
	 model.addAttribute("counsellor", new Counsellor());
	 return "loginView";
 }
	@GetMapping("/register")
	public String getReg(Counsellor c, Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return"registerView";
	}
	@GetMapping("/forget-pwd")
	public String recoverPwdPage(Model model) {
		return "forgotPwdPage";
	}
	@PostMapping("/register")
	public String handleRegistration(Counsellor c, Model model) {
		
		String msg=counsellorService.saveCounsellor(c);
		model.addAttribute("msg", msg);
		return"registerView";	
	}
	@PostMapping("/login")
	public String handleLogin(Counsellor c,HttpServletRequest req, Model model) {
		Counsellor obj=counsellorService.checkLogin(c.getEmail(), c.getPwd());
		if(obj==null) {
			model.addAttribute("errMsg", "invalid Credentials");
			return "loginView";
		}
		HttpSession session=req.getSession(true);
		session.setAttribute("CID", obj.getCid());
			return"redirect:dashboard";
		}
	@GetMapping("/dashboard")
	public String buildDashboarrd(HttpServletRequest req, Model model) {
		HttpSession session=req.getSession(false);
		Object obj=session.getAttribute("CID");
		Integer cid=(Integer)obj;
		
		Dashboard dashboardInfo=counsellorService.getDashboardInfo(cid);
		model.addAttribute("dashboard" ,dashboardInfo );
		return "dashboardView";
		
	}
	@GetMapping("/recover-Pwd")
	public String recoverPwd(@RequestParam String email, Model model) {
		boolean status=counsellorService.recoverPwd(email);
		if(status) {
			model.addAttribute("smsg", "Pwd sent on your Email, pls check");
		}
		else {
			model.addAttribute("errmsg", "Invalid email");
		}
		return "forgotPwdPage";
	}
	
	}

