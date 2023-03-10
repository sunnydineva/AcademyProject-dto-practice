package com.example.demo.convertor;

import com.example.demo.dto.ClientRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.entity.Client;
import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;


import java.util.UUID;

@Component
public class ClientConvertor {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StatusRepository statusRepository;


    public Client toClient(ClientRequest clientRequest){
        UUID uuid = UUID.randomUUID();
        Optional<Status> status = statusRepository.findByStatusName(clientRequest.getStatus());
        Status statusClient = null;
        if(status.isPresent()) {
            statusClient = status.get();
        }
        return Client.builder().firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .address(clientRequest.getAddress())
                .email(clientRequest.getEmail())
                .statuses(Set.of(statusClient))
                .iban(uuid.toString()) //sets the iban
                .password(bCryptPasswordEncoder.encode( clientRequest.getPassword()))
                .balance(new BigDecimal("0"))    //default = 0
                .build();
    }

    public ClientResponse toResponse (Client client){
        return ClientResponse.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .email(client.getEmail())
                .statuses(client.getStatuses())
                .build();
    }
}
