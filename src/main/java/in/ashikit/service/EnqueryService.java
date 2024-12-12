package in.ashikit.service;

import java.util.List;

import in.ashikit.binding.SearchCriteria;
import in.ashikit.entity.StudentEnq;

public interface EnqueryService {
public boolean addEnq(StudentEnq se);
public List<StudentEnq>getEnquiries(Integer cid, SearchCriteria s);
}
