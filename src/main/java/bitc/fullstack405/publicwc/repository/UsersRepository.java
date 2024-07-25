package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends JpaRepository<Users, String> {

//    로그인 시 아이디 비번 중복 확인 쿼리문
    @Query("SELECT count(u) FROM Users u " +
            "WHERE u.id = :id AND u.password = :password")
    int correctLogin(@Param("id") String id, @Param("password") String password);
    
//    회원가입 시 아이디 중복 확인 쿼리문
    @Query("SELECT count(u) FROM Users u WHERE u.id = :id")
    int loginIdCheck(@Param("id") String id);

//    회원가입 시 이메일 중복 확인
    @Query("SELECT count(u) FROM Users u WHERE u.email = :email")
    int emailCheck(@Param("email") String email);

//    비번 수정 쿼리문
    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.password = :password WHERE u.id = :id")
    int updatePassword(@Param("id") String id, @Param("password") String password);

//    이메일 수정 쿼리문
    @Modifying
    @Query("UPDATE Users u SET u.email = :email WHERE u.id = :id")
    int updateEmail(@Param("id") String id, @Param("email") String email);

//    회원 삭제 쿼리문
    @Query(
            "DELETE FROM Users u WHERE u.id = :id")
    void deleteUsers(@Param("id") String id);




////    유저에 담에서 쿼리문 보내는거
//    @Query("SELECT count(u) AS id FROM Users u " +
//            "WHERE u.id = :id")
//    int loginIdCheck(@Param("id") Users user);

}
















