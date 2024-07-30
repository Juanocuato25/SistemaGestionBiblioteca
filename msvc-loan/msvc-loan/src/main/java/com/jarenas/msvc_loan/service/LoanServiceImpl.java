package com.jarenas.msvc_loan.service;

import com.jarenas.msvc_loan.clients.BookClientRest;
import com.jarenas.msvc_loan.clients.UserClientRest;
import com.jarenas.msvc_loan.dto.LoanCreationDTO;
import com.jarenas.msvc_loan.dto.LoanDTO;
import com.jarenas.msvc_loan.exceptions.LoanExceptions;
import com.jarenas.msvc_loan.mapper.LoanMapper;
import com.jarenas.msvc_loan.model.BookDTO;
import com.jarenas.msvc_loan.model.UserDTO;
import com.jarenas.msvc_loan.model.entity.Loan;
import com.jarenas.msvc_loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired(required = true)
    UserClientRest userClientRest;

    @Autowired
    BookClientRest bookClientRest;


    private final LoanMapper loanMapper = LoanMapper.INSTANCE;

    @Override
    @Transactional
    public List<LoanDTO> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(loanMapper::toLoanDTO).collect(Collectors.toList());
    }

    @Override
    public LoanDTO createLoan(LoanCreationDTO loanCreationDTO) {
        UserDTO userDTO = userClientRest.userById(loanCreationDTO.getUser_id());
        if (userDTO == null) {
            throw new LoanExceptions("User not found", HttpStatus.NOT_FOUND);
        }

        BookDTO bookDTO = bookClientRest.bookById(loanCreationDTO.getBook_id());
        if (bookDTO == null) {
            throw new LoanExceptions("Book not found", HttpStatus.NOT_FOUND);
        }

        Loan loan = new Loan();
        loan.setUser_id(userDTO.getId());
        loan.setBook_id(bookDTO.getId());
        loan.setLoanDate(loanCreationDTO.getLoanDate());
        loan.setReturnDate(loanCreationDTO.getReturndDate());
        loan.setLoanStatus(loan.calculateStatus());

        return LoanMapper.INSTANCE.toLoanDTO(loanRepository.save(loan));
    }

    @Override
    public LoanDTO findLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanExceptions("Loan does not exists", HttpStatus.NOT_FOUND));
        return LoanMapper.INSTANCE.toLoanDTO(loan);
    }

    @Override
    public void deleteLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanExceptions("Loan does not exists", HttpStatus.NOT_FOUND));
        loanRepository.deleteById(id);
    }

    @Override
    public LoanDTO updateLoan(LoanCreationDTO loanCreationDTO, Long id) {

        Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanExceptions("Loan does not exists", HttpStatus.NOT_FOUND));

        UserDTO userDTO = userClientRest.userById(loanCreationDTO.getUser_id());
        if (userDTO == null) {
            throw new LoanExceptions("User not found", HttpStatus.NOT_FOUND);
        }

        BookDTO bookDTO = bookClientRest.bookById(loanCreationDTO.getBook_id());
        if (bookDTO == null) {
            throw new LoanExceptions("Book not found", HttpStatus.NOT_FOUND);
        }

        loan.setUser_id(userDTO.getId());
        loan.setBook_id(bookDTO.getId());
        loan.setLoanDate(loanCreationDTO.getLoanDate());
        loan.setReturnDate(loanCreationDTO.getReturndDate());
        loan.setLoanStatus(loan.calculateStatus());

        loan.setUserDTO(userDTO);
        loan.setBookDTO(bookDTO);

        return LoanMapper.INSTANCE.toLoanDTO(loan);
    }
}
