package com.deloitte.digital.librarymanagementsystem.repository;

import com.deloitte.digital.librarymanagementsystem.model.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
}
