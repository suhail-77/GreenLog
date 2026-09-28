package com.greenlog.greenlog.repository;

import com.greenlog.greenlog.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}