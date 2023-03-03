package com.CCoABackendCalculate.CCoA.SpringRepo;

import com.CCoABackendCalculate.CCoA.SpringModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
