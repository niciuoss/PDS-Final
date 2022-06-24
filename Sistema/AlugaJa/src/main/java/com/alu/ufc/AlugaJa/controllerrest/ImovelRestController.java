package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;

import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import com.google.api.Http;
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
@RequestMapping(value = "/api/v1/")
@CrossOrigin("*")
public class ImovelRestController {
    @Autowired
    private ImovelServiceAPI imovelServiceAPI;
    private ImovelServiceIMPL imovelServiceIMPL;
    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;
    private UsuarioServiceIMPL usuarioServiceIMPL;


    @Bean
    CommandLineRunner inicializarImovel(){
        return args -> {
            usuarioServiceIMPL = new UsuarioServiceIMPL();
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



    @PutMapping(value = "usuarios/{id_usuario}/imoveis/{id}")
    public ResponseEntity<String> update(@RequestBody Imovel imovel, @PathVariable String id_usuario, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            ImovelDTO dto = imovelServiceAPI.get(id);
            if(dto==null){
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
            String res = imovelServiceIMPL.validarAtualizarImovel(imovel, dto);
            String res2 = imovelServiceIMPL.validarCriarImovel(imovel, id_usuario);
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

    @PostMapping(value = "usuarios/{id_usuario}/imoveis/")
    public ResponseEntity<String> save2(@RequestBody Imovel imovel, @PathVariable String id_usuario) throws Exception {
        if(usuarioServiceAPI.get(id_usuario) != null){
            String res = imovelServiceIMPL.validarCriarImovel(imovel, id_usuario);
            if(res.equals("safe")){
                String id = imovelServiceAPI.save(imovel);
                usuarioServiceAPI.get(id_usuario).setId_imovel(id);
                return new ResponseEntity<String>(id, HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<String>("Usuario inválido", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "imovel/{id}")
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