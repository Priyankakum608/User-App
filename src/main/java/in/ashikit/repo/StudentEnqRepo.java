package in.ashikit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashikit.entity.StudentEnq;

public interface StudentEnqRepo extends JpaRepository<StudentEnq, Integer>{
public List<StudentEnq> findByCid(Integer cid);
}
