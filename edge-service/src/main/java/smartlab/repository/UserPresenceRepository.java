package smartlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smartlab.model.UserPresence;

import java.util.List;

public interface UserPresenceRepository extends JpaRepository<UserPresence, Integer> {



    @Query(value = "select id_usuario from (select id_usuario, max(timestamp) \n" +
            "\tfrom user_presence up \n" +
            "    where up.timestamp between timestamp(date_sub(now(), interval 2 minute)) and timestamp(now())\n" +
            "    group by id_usuario) as online_users",nativeQuery = true)
    List<Integer> queryOnlineUsers();
//
}
