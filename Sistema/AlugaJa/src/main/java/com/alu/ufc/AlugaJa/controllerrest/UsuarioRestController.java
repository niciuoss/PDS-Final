package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;

import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;

@RestController
@RequestMapping(value = "/api/v1/usuarios")
@CrossOrigin("*")
public class UsuarioRestController {
    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;
    private UsuarioServiceIMPL usuarioServiceIMPL;


    @Bean
    CommandLineRunner inicializarUsuario(){
        return args -> {
            usuarioServiceIMPL = new UsuarioServiceIMPL();
        };
    }

    @GetMapping(value = "/")
    public List<UsuarioDTO> getAll() throws Exception {
        return usuarioServiceAPI.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> find(@PathVariable String id) throws Exception {
        UsuarioDTO dto = usuarioServiceAPI.get(id);
        if(dto == null){
            return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<UsuarioDTO>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Usuario usuario, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            UsuarioDTO dto = usuarioServiceAPI.get(id);
            if(dto==null){
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
            String res = usuarioServiceIMPL.validarAtualizarImovel(usuario, dto);
            String res2 = usuarioServiceIMPL.validarCriarImovel(usuario);
            if(res2.equals("safe")) {
                if (res.equals("Não houveram alterações, mude algum campo e tente novamente")) {
                    return new ResponseEntity<String>(res, HttpStatus.NO_CONTENT);
                } else {
                    usuarioServiceAPI.update(usuario, id);
                    return new ResponseEntity<String>(id, HttpStatus.OK);
                }
            }
            else{
                return new ResponseEntity<String>(res2, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> save(@RequestBody Usuario usuario) throws Exception {
        String res = usuarioServiceIMPL.validarCriarImovel(usuario);
        if(res.equals("safe")){
            String id = usuarioServiceAPI.save(usuario);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        UsuarioDTO usuario = usuarioServiceAPI.get(id);
        if(usuario == null){
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        }
        else{
            usuarioServiceAPI.delete(id);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
}