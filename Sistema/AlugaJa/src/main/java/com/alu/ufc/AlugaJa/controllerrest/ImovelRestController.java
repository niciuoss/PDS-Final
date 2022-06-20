package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;

import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.service.api.ImovelServiceAPI;

@RestController
@RequestMapping(value = "/api/v1/imoveis")
@CrossOrigin("*")
public class ImovelRestController {
    @Autowired
    private ImovelServiceAPI imovelServiceAPI;
    private ImovelServiceIMPL imovelServiceIMPL;


    @Bean
    CommandLineRunner inicializar(){
        return args -> {
            imovelServiceIMPL = new ImovelServiceIMPL();
        };
    }

    @GetMapping(value = "/")
    public List<ImovelDTO> getAll() throws Exception {
        return imovelServiceAPI.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImovelDTO> find(@PathVariable String id) throws Exception {
        ImovelDTO dto = imovelServiceAPI.get(id);
        if(dto == null){
            return new ResponseEntity<ImovelDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ImovelDTO>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Imovel imovel, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            ImovelDTO dto = imovelServiceAPI.get(id);
            if(dto==null){
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
            String res = imovelServiceIMPL.validarAtualizarImovel(imovel, dto);
            String res2 = imovelServiceIMPL.validarCriarImovel(imovel);
            if(res2.equals("safe")) {
                if (res.equals("Não houveram alterações, mude algum campo e tente novamente")) {
                    return new ResponseEntity<String>(res, HttpStatus.NO_CONTENT);
                } else {
                    imovelServiceAPI.update(imovel, id);
                    return new ResponseEntity<String>(id, HttpStatus.OK);
                }
            }
            else{
                return new ResponseEntity<String>(res2, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> save(@RequestBody Imovel imovel) throws Exception {
        String res = imovelServiceIMPL.validarCriarImovel(imovel);
        if(res.equals("safe")){
            String id = imovelServiceAPI.save(imovel);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        ImovelDTO imovel = imovelServiceAPI.get(id);
        if(imovel == null){
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        }
        else{
            imovelServiceAPI.delete(id);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
}