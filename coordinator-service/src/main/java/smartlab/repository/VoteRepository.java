package smartlab.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, String> {


    @Query("SELECT v FROM Vote v \n" +
            "WHERE v.id in (\n" +
            "    SELECT MAX (v.id) \n" +
            "    FROM Vote v WHERE v.userId = ?1 \n" +
            "    GROUP BY v.externalTemperature, v.internalTemperature, v.hour, v.onlineUsers)")
    List<Vote> queryTemperatureProfile(Integer userId);
//
//    @Query
//    User findByEmail(String email);

}