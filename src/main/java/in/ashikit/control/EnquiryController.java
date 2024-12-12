package in.ashikit.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashikit.binding.SearchCriteria;
import in.ashikit.entity.StudentEnq;
import in.ashikit.service.EnqueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Controller
public class EnquiryController {
@Autowired
private EnqueryService enqueryService;

@GetMapping("/enquiry")
public String enqPage(Model model) {
	model.addAttribute("enq", new StudentEnq() );
	return "addEnqView";
	
}
@PostMapping("/enquiry")
public String addEnquery(@ModelAttribute("enq")StudentEnq enq,HttpServletRequest req , Model model) {
	HttpSession session=req.getSession();
	Integer cid=(Integer) session.getAttribute("CID");
	if(cid==null) {
		return "redirect:/logout";
	}
	enq.setCid(cid);
	
	boolean addEnq =enqueryService.addEnq(enq);
	if(addEnq) {
		model.addAttribute("succMsg", "Enquiry Added");
	}
	else {
	model.addAttribute("errMsg", "Enquiry failed to add");
	
}
	return "addEnqView";
}
@GetMapping("/enquiries")
public String viewEnq(HttpServletRequest req, Model model) {
	HttpSession session=req.getSession();
	Integer cid=(Integer) session.getAttribute("CID");
	if(cid==null) {
		return "redirect/logout"; 
	}
	model.addAttribute("sc", new SearchCriteria());
	 List<StudentEnq> enquiryList=enqueryService.getEnquiries(cid, new SearchCriteria());
	 model.addAttribute("enquiries", enquiryList);
	 return "displayEnqView";
	
}
@PostMapping("/filter-enquiries")
public String filterCriteria(@ModelAttribute("sc") SearchCriteria sc, HttpServletRequest req, Model model) {
	HttpSession session=req.getSession();
	Integer cid=(Integer) session.getAttribute("CID");
	if(cid==null) {
		return "redirect/"; 
	}
	 List<StudentEnq> enquiryList=enqueryService.getEnquiries(cid, sc);
	 model.addAttribute("enquiries", enquiryList);

	 return "filterEnqView";
}
}
