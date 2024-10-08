package com.example.atividade.controller;

import com.example.atividade.dto.EstadoInputDTO;
import com.example.atividade.dto.EstadoOutputDTO;
import com.example.atividade.model.Estado;
import com.example.atividade.repository.EstadoRepository;
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
@RequestMapping("api/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoOutputDTO>> list(){

        List<Estado> estados = estadoRepository .findAll();
        List<EstadoOutputDTO> estadosOutputDTO = new ArrayList<>();

        for(Estado estado: estados){
            estadosOutputDTO.add(new EstadoOutputDTO(estado));
        }

        if (!estadosOutputDTO.isEmpty()) {
            return new ResponseEntity<>(estadosOutputDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> create (@RequestBody EstadoInputDTO estadoInputDTO){


        try {
            Estado estado = estadoInputDTO.build(paisRepository);

            Estado estadoNoBD = estadoRepository.save(estado);

            EstadoOutputDTO estadoOutputDTO = new EstadoOutputDTO(estadoNoBD);

            return new ResponseEntity<>(estadoOutputDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoOutputDTO> update(@PathVariable Long id, @RequestBody EstadoInputDTO estadoInputDTO) {

        try {
            Optional<Estado> possivelEstado = estadoRepository.findById(id);

            if (possivelEstado.isPresent()) {
                Estado estadoEncontrado = possivelEstado.get();

                estadoEncontrado.setNome(estadoInputDTO.getNome());
                estadoEncontrado.setSigla(estadoInputDTO.getSigla());
                estadoEncontrado.setPais(paisRepository.findByNome(estadoInputDTO.getPais()));

                Estado estadoAtualizado = estadoRepository.save(estadoEncontrado);

                return new ResponseEntity<>(new EstadoOutputDTO(estadoAtualizado), HttpStatus.OK);
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
            estadoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
