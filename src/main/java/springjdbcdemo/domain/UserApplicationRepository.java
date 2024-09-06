package springjdbcdemo.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserApplicationRepository extends CrudRepository<UserApplication, UUID> {

    @Query("select u from user_application u where city = :city")
    List<UserApplication> findAllByCity(@Param("city") String city);

}
