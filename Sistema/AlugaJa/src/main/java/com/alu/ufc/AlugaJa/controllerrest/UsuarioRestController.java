package com.alu.ufc.AlugaJa.controllerrest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.ImovelServiceAPI;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import com.google.api.client.util.ArrayMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins="*")
@Api(value="API REST usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;
    private UsuarioServiceIMPL usuarioServiceIMPL;
    @Autowired
    private ImovelServiceAPI imovelServiceAPI;
    private ImovelServiceIMPL imovelServiceIMPL;


    @Bean
    CommandLineRunner inicializarUsuario(){
        return args -> {
            usuarioServiceIMPL = new UsuarioServiceIMPL();
            imovelServiceIMPL = new ImovelServiceIMPL();
        };
    }

    @GetMapping(value = "usuarios/")
    @ApiOperation(value="Retornar todos os usuarios da aplicação")
    public List<UsuarioDTO> getAll() throws Exception {
        return usuarioServiceAPI.getAll();
    }

    @GetMapping(value = "usuarios/{id_usuario}")
    @ApiOperation(value="Retornar apenas um usuário por ID")
    public ResponseEntity<UsuarioDTO> find(@PathVariable String id_usuario) throws Exception {
        UsuarioDTO dto = usuarioServiceAPI.get(id_usuario);
        if(dto == null){
            return new ResponseEntity<UsuarioDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UsuarioDTO>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "usuarios/{id_usuario}/imoveis/")
    @ApiOperation(value="Retornar todos os imóveis de um usuário")
    public List<ImovelDTO> imoveisUsuario(@PathVariable String id_usuario) throws Exception {
        List<ImovelDTO> dto = imovelServiceAPI.getAll();
        List<ImovelDTO> saida = new ArrayList<>();
        for(int i = 0; i<dto.size(); i++){
            if(dto.get(i).getId_usuario().equals(id_usuario)){
                saida.add(dto.get(i));
            }
        }
        return saida;
    }

    @PutMapping(value = "usuarios/{id_usuario}/")
    @ApiOperation(value="Alterar um usuario")
    public ResponseEntity<String> update(@RequestBody Usuario usuario, @PathVariable String id_usuario) throws Exception {
        if (id_usuario == null || id_usuario.length() == 0 || id_usuario.equals("null")) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        } else {
            UsuarioDTO dto = usuarioServiceAPI.get(id_usuario);
            if(dto==null){
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
            String res = usuarioServiceIMPL.validarAtualizarUsuario(usuario, dto);
            String res2 = usuarioServiceIMPL.validarCriarUsuario(usuario);
            if(res2.equals("safe")) {
                if (res.equals("Não houveram alterações, mude algum campo e tente novamente")) {
                    return new ResponseEntity<String>(res, HttpStatus.NO_CONTENT);
                } else {
                    usuarioServiceAPI.update(usuario, id_usuario);
                    return new ResponseEntity<String>(id_usuario, HttpStatus.OK);
                }
            }
            else{
                return new ResponseEntity<String>(res2, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "usuarios/")
    @ApiOperation(value="adicionar um usuário")
    public ResponseEntity<String> save(@RequestBody Usuario usuario) throws Exception {
        String res = usuarioServiceIMPL.validarCriarUsuario(usuario);
        if(res.equals("safe")){
            String id = usuarioServiceAPI.save(usuario);
            return new ResponseEntity<String>(id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
        }

    }





    @DeleteMapping(value = "usuarios/{id}")
    @ApiOperation(value="Excluir um usuário por ID")
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