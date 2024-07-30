package com.jarenas.msvc_loan.mapper;


import com.jarenas.msvc_loan.dto.LoanCreationDTO;
import com.jarenas.msvc_loan.dto.LoanDTO;
import com.jarenas.msvc_loan.model.entity.Loan;
import com.jarenas.msvc_loan.model.entity.LoanStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(target = "loanStatus", expression = "java(loan.calculateStatus())")
    Loan toLoan(LoanCreationDTO loanCreationDTO);

    @Mapping(target = "loanStatus", expression = "java(loan.calculateStatus())")
    LoanDTO toLoanDTO(Loan loan);


}
