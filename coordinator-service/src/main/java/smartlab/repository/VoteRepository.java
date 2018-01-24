package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.UserPreference;

import java.util.List;

public interface VoteRepository extends JpaRepository<UserPreference, String> {


    @Query("SELECT v FROM UserPreference v \n" +
            "WHERE v.id in (\n" +
            "    SELECT MAX (v.id) \n" +
            "    FROM UserPreference v WHERE v.userId = ?1 \n" +
            "    GROUP BY v.externalTemperature, v.internalTemperature, v.hour, v.onlineUsers)")
    List<UserPreference> queryTemperatureProfile(Integer userId);
//
//    @Query
//    User findByEmail(String email);

}