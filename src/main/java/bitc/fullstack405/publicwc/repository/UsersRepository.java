package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, String> {

    @Query(
            "DELETE FROM Users u WHERE u.id = :id")
    void deleteUsers(@Param("id") String id);
}
















