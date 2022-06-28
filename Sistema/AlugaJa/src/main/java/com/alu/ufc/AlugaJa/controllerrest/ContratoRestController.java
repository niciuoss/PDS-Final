package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;
import java.util.ArrayList;

import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.ContratoServiceAPI;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ContratoServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.service.api.ImovelServiceAPI;

@RestController
@RequestMapping(value = "/api/v1/")
@CrossOrigin(origins="*")
@Api(value="API REST contratos")
public class ContratoRestController {
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
    CommandLineRunner inicializarContrato(){
        return args -> {
            usuarioServiceIMPL = new UsuarioServiceIMPL();
            imovelServiceIMPL = new ImovelServiceIMPL();
            contratoServiceIMPL = new ContratoServiceIMPL();
        };
    }

    @GetMapping(value = "contratos/")
    @ApiOperation(value="Retorna todos os contratos de um usuario")
    //FALTA AS VALIDAÇÕES
    public ResponseEntity<List<ContratoDTO>> getAllContratos(@RequestBody String id_locador) throws Exception {
        List<ContratoDTO> all = contratoServiceAPI.getAll();
        List<ContratoDTO> saida = new ArrayList<>();
        //String cpf = usuarioServiceAPI.get(id_locador).getId();
        for(int i=0; i<all.size(); i++){
            if(all.get(i).getId_usuario().equals(id_locador)){
                saida.add(all.get(i));
            }
        }
        if(saida == null){
            return new ResponseEntity<List<ContratoDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ContratoDTO>>(saida, HttpStatus.OK);
    }

    @PostMapping(value="contratos/")
    public ResponseEntity<String> aluga(@RequestBody Contrato contrato) throws Exception{
        String res = contratoServiceIMPL.validarCriarContrato(contrato);
        ImovelDTO imovel = imovelServiceAPI.get(contrato.getId_imovel());
        imovel.setAlugado(true);
        if(res.equals("safe")) {
            String idContrato = contratoServiceAPI.save(contrato);
            imovel.setAlugado(true);
            return new ResponseEntity<String>(idContrato, HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("id do imovel ou do usuario incorreto", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value="contratos/{id_contrato}")
    public ResponseEntity<String> deleteContrato(@PathVariable String id_contrato, @RequestBody String id_imovel) throws Exception{
        ImovelDTO imovel = imovelServiceAPI.get(id_imovel);
        if(imovel.getAlugado()){
            imovel.setAlugado(false);
        }
        contratoServiceAPI.delete(id_contrato);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}