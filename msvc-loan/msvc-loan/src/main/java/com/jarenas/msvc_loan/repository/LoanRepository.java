package com.jarenas.msvc_loan.repository;

import com.jarenas.msvc_loan.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
