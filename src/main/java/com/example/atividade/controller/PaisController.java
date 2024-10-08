package com.example.atividade.controller;

import com.example.atividade.dto.CidadeOutputDTO;
import com.example.atividade.dto.PaisInputDTO;
import com.example.atividade.dto.PaisOutputDTO;
import com.example.atividade.model.Cidade;
import com.example.atividade.model.Pais;
import com.example.atividade.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PaisOutputDTO> read (@PathVariable Long id){
        Optional<Pais> pais = paisRepository.findById(id);
        if(pais.isPresent()){
            return new ResponseEntity<>(new PaisOutputDTO(pais.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaisOutputDTO>> list(){

        List<Pais> paises = paisRepository .findAll();
        List<PaisOutputDTO> paisesOutputDTO = new ArrayList<>();

        for(Pais pais: paises){
            paisesOutputDTO.add(new PaisOutputDTO(pais));
        }

        if (!paisesOutputDTO.isEmpty()) {
            return new ResponseEntity<>(paisesOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping
    public ResponseEntity<Pais> create(@RequestBody PaisInputDTO paisInputDTO) {

        Pais pais = new Pais();
        pais.setNome(paisInputDTO.getNome());
        pais.setSigla(paisInputDTO.getSigla());

        Pais novoPais = paisRepository.save(pais);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoPais);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pais> update(@PathVariable Long id, @RequestBody PaisInputDTO paisInputDTO) {

        try {
            Optional<Pais> possivelPais = paisRepository.findById(id);

            if (possivelPais.isPresent()) {
                Pais paisEncontrado = possivelPais.get();

                paisEncontrado.setNome(paisInputDTO.getNome());
                paisEncontrado.setSigla(paisInputDTO.getSigla());

                Pais paisAtualizado = paisRepository.save(paisEncontrado);

                return new ResponseEntity<>(paisAtualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            paisRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
