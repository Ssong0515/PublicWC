package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.Users; // 경로 수정
import bitc.fullstack405.publicwc.entity.WcInfo;
import bitc.fullstack405.publicwc.repository.UsersRepository;
import ch.qos.logback.core.joran.conditional.IfAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void writeWc(WcInfo wcInfo) {

    }

    @Override
    public Optional<Users> findById(String userId) {
        return usersRepository.findById(userId); // ID로 사용자 조회
    }

    @Override
    public void saveUser(Users user) {
        // 사용자 정보를 저장 (비밀번호 암호화 로직은 필요에 따라 추가)
        usersRepository.save(user);
    }

    @Override
    public boolean checkPassword(Users user, String rawPassword) {
        // 비밀번호 비교 로직
        return user.getPassword().equals(rawPassword); // 비밀번호 비교 (단순 비교)
    }

    @Override
    public void deleteUser(String userId) {
        usersRepository.deleteUsers(userId); // 사용자 삭제
    }
}
