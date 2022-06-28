package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;
import java.util.ArrayList;

import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.ContratoServiceAPI;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ContratoServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import com.google.api.Http;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@CrossOrigin(origins="*")
@Api(value="API REST imoveis")
public class ImovelRestController {
    @Autowired
    private ImovelServiceAPI imovelServiceAPI;
    private ImovelServiceIMPL imovelServiceIMPL;
    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;
    private UsuarioServiceIMPL usuarioServiceIMPL;
    @Autowired
    private ContratoServiceAPI contratoServiceAPI;
    private ContratoServiceIMPL contratoServiceIMPL;


    @Bean
    CommandLineRunner inicializarImovel() {
        return args -> {
            usuarioServiceIMPL = new UsuarioServiceIMPL();
            imovelServiceIMPL = new ImovelServiceIMPL();
            contratoServiceIMPL = new ContratoServiceIMPL();
        };
    }

    @GetMapping(value = "imoveis/")
    @ApiOperation(value = "Retorna todos os imóveis da aplicação")
    public ResponseEntity<List<ImovelDTO>> getAll() throws Exception {

        return new ResponseEntity<List<ImovelDTO>>(imovelServiceAPI.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value = "Retorna um imovél específico")
    public ResponseEntity<ImovelDTO> find(@PathVariable String id_imovel) throws Exception {
        ImovelDTO dto = imovelServiceAPI.get(id_imovel);
        if (dto == null) {
            return new ResponseEntity<ImovelDTO>(HttpStatus.NO_CONTENT);
        }
        if (usuarioServiceAPI.get(dto.getId_usuario()) == null) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        return new ResponseEntity<ImovelDTO>(dto, HttpStatus.OK);
    }


    @PutMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value="Altera um imóvel que pertece ao usuário")
    public ResponseEntity<String> update(@RequestBody Imovel imovel, @PathVariable String id_imovel) throws Exception {
        if (id_imovel == null || id_imovel.length() == 0 || id_imovel.equals("null")) {
            return new ResponseEntity<String>("1",HttpStatus.NO_CONTENT);
        } else {
            ImovelDTO dto = imovelServiceAPI.get(id_imovel);
            if (dto == null) {
                return new ResponseEntity<String>("2",HttpStatus.NO_CONTENT);
            }
            if(usuarioServiceAPI.get(dto.getId_usuario()) == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            String res = imovelServiceIMPL.validarAtualizarImovel(imovel, dto);
            String res2 = imovelServiceIMPL.validarCriarImovel(imovel, imovel.getId_usuario());
            if (res2.equals("safe")) {
                if (res.equals("Não houveram alterações, mude algum campo e tente novamente")) {
                    return new ResponseEntity<String>(res, HttpStatus.NO_CONTENT);
                } else {
                    String id = imovelServiceAPI.update(imovel, id_imovel);
                    return new ResponseEntity<String>(id, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<String>(res2, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping(value = "imoveis/")
    @ApiOperation(value="Adiciona novo imóvel na aplicação")
    public ResponseEntity<String> save2(@RequestBody Imovel imovel) throws Exception {
        if(usuarioServiceAPI.get(imovel.getId_usuario()) != null){
            String res = imovelServiceIMPL.validarCriarImovel(imovel, imovel.getId_usuario());
            if(res.equals("safe")){
                String id = imovelServiceAPI.save(imovel);
                return new ResponseEntity<String>(id, HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<String>("Usuario inválido", HttpStatus.NO_CONTENT);
        }

    }

    @DeleteMapping(value = "imoveis/{id_imovel}")
    @ApiOperation(value="Exclui um imóvel do usuario")
    public ResponseEntity<String> delete(@PathVariable String id_imovel, @RequestBody String id_locador, String id_contrato) throws Exception {
        ImovelDTO imovel = imovelServiceAPI.get(id_imovel);
        if(imovel == null){
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        }
        if(usuarioServiceAPI.get(imovel.getId_usuario()) == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(imovel.getId_usuario().equals(id_locador)){
            imovelServiceAPI.delete(id_imovel);
            contratoServiceAPI.delete(id_contrato);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Você não pode excluir imóveis que não são seus", HttpStatus.FORBIDDEN);
    }
}
