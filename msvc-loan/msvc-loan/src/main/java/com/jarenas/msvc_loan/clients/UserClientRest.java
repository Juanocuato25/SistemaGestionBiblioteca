package com.jarenas.msvc_loan.clients;

import com.jarenas.msvc_loan.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-user", url = "localhost:8001/users")
public interface UserClientRest {

    @GetMapping("/{id}")
    UserDTO userById(@PathVariable Long id);

}
