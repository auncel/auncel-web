package dev.yidafu.auncel.web.dal;

import dev.yidafu.auncel.web.domain.Contest;
import dev.yidafu.auncel.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestRepository extends JpaRepository<Contest, Long> {
    @Override
    public List<Contest> findAll();

    public List<Contest> findAllByMaker(User maker);

    public Contest findOneByInvitaionCode(String code);
}
