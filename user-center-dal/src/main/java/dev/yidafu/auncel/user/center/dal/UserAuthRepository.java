package dev.yidafu.auncel.user.center.dal;

import dev.yidafu.auncel.user.center.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    @Override
    UserAuth getOne(Long aLong);

    UserAuth findByIdentityTypeAndIdentifier(String indentityType, String indentifier);
}
