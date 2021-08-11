package in.nit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType,Integer> {

	@Query("SELECT COUNT(wh.userMail) FROM WhUserType wh WHERE wh.userMail=:mail")
	public Integer getWhUserTypeMailCount(String mail);

	@Query("SELECT WH.id, WH.userCode FROM WhUserType WH WHERE  WH.userType=:userType")
	public List<Object[]> getWhUserTypeIdAndCode(String userType);
	
}
