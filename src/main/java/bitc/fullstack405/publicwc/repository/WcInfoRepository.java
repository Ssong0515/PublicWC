package bitc.fullstack405.publicwc.repository;

import bitc.fullstack405.publicwc.entity.WcInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WcInfoRepository extends JpaRepository<WcInfo, Integer> {
}
