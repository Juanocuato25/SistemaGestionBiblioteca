package com.jarenas.msvc_loan.service;

import com.jarenas.msvc_loan.dto.LoanCreationDTO;
import com.jarenas.msvc_loan.dto.LoanDTO;

import java.util.List;

public interface LoanService {

    List<LoanDTO> getAllLoans();

    LoanDTO createLoan(LoanCreationDTO loanCreationDTO);

    LoanDTO findLoanById(Long id);

    void deleteLoan(Long id);

    LoanDTO updateLoan(LoanCreationDTO loanCreationDTO, Long id);

}
