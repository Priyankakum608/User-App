package in.ashikit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashikit.entity.Counsellor;

public interface ConsellorRepo extends JpaRepository<Counsellor, Integer>{
	public Counsellor findByEmail(String email);
	public Counsellor findByEmailAndPwd(String email, String pwd);

}
