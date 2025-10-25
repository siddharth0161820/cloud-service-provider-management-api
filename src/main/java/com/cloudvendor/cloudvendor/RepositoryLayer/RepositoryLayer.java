package com.cloudvendor.cloudvendor.RepositoryLayer;

import com.cloudvendor.cloudvendor.ModelEntity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLayer extends JpaRepository<Vendor,Integer> {
}
