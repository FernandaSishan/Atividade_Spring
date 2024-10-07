package com.example.atividade.controller;

import com.example.atividade.dto.PessoaInputDTO;
import com.example.atividade.dto.PessoaOutputDTO;
import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.CidadeRepository;
import com.example.atividade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaOutputDTO>> list(){

        List<Pessoa> pessoas = pessoaRepository .findAll();
        List<PessoaOutputDTO> pessoasOutputDTO = new ArrayList<>();

        for (Pessoa pessoa: pessoas){
            pessoasOutputDTO.add(new PessoaOutputDTO(pessoa));
        }

        if(!pessoasOutputDTO.isEmpty()){
            return new ResponseEntity<>(pessoasOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaOutputDTO> create(@RequestBody PessoaInputDTO pessoaInputDTO){

        try {
            Pessoa pessoa = pessoaInputDTO.build(cidadeRepository);

            Pessoa pessoaNoBD = pessoaRepository.save(pessoa);

            PessoaOutputDTO pessoaOutputDTO = new PessoaOutputDTO(pessoaNoBD);

            return new ResponseEntity<>(pessoaOutputDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
