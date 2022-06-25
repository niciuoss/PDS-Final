package com.alu.ufc.AlugaJa.controllerrest;

import java.util.ArrayList;
import java.util.List;

import com.alu.ufc.AlugaJa.models.Contrato;
import com.alu.ufc.AlugaJa.modelsDTO.ContratoDTO;
import com.alu.ufc.AlugaJa.service.api.ContratoServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ContratoServiceIMPL;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.api.UsuarioServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.UsuarioServiceIMPL;
import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;
import com.alu.ufc.AlugaJa.service.api.ImovelServiceAPI;
import com.alu.ufc.AlugaJa.service.impl.ImovelServiceIMPL;
import com.google.api.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/")
@CrossOrigin("*")

public class ContratoRestController {

    @Autowired
    private ContratoServiceAPI contratoServiceAPI;
    private ContratoServiceIMPL contratoServiceIMPL;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;
    private UsuarioServiceIMPL usuarioServiceIMPL;

    @Autowired
    private ImovelServiceAPI imovelServiceAPI;
    private ImovelServiceIMPL imovelServiceIMPL;

    @Bean
    CommandLineRunner inicializarContrato(){
        return args -> {
            contratoServiceIMPL = new ContratoServiceIMPL();
            usuarioServiceIMPL = new UsuarioServiceIMPL();
            imovelServiceIMPL = new ImovelServiceIMPL();
        };
    }

    @GetMapping(value = "usuarios/{id_usuario}/contratos/")
    public List<ContratoDTO> getAll(@PathVariable String id_usuario) throws Exception {
        ArrayList<String> lista = (usuarioServiceAPI.get(id_usuario).getId_imovel());
        ArrayList<ContratoDTO> contratos = new ArrayList<>();

        for (String s : lista) {
            contratos.add(contratoServiceAPI.get(s));
        }

        return contratos;
    }

    @GetMapping(value = "usuarios/{id_usuario}/contratos/{id}")
    public ResponseEntity<ContratoDTO> find (@PathVariable String id_usuario, @PathVariable String id) throws Exception {
        ContratoDTO dto = contratoServiceAPI.get(id);
        if (dto == null){
            return new ResponseEntity<ContratoDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ContratoDTO>(dto, HttpStatus.OK);
    }

    /*
    @PutMapping(value = "usuarios/imoveis/contratos/{id}")
    public ResponseEntity<String> update (@RequestBody Contrato contrato, @PathVariable String id_usuario, @PathVariable String id_imovel, @PathVariable String id) throws Exception {
        if (id == null || id.length() == 0 || id.equals("null")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            ContratoDTO dto = contratoServiceAPI.get(id);
            if (dto == null) {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
            String res = contratoServiceIMPL.validarAtualizarContrato(contrato, dto);
            String res2 = contratoServiceIMPL.validarCriarContrato(contrato, id_imovel);

            if (res2.equals("safe")) {
                if (res.equals("Não houveram alterações, mude algum campo e tente novamente")) {
                    return new ResponseEntity<String>(res, HttpStatus.NO_CONTENT);
                } else {
                    contratoServiceAPI.update(contrato, id);
                    return new ResponseEntity<String>(id, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<String>(res2, HttpStatus.NO_CONTENT);
            }
        }
    }
    */

    /*
    @PostMapping(value = "usuarios/{id_usuario}/contratos/")
    public ResponseEntity<String> save2 (@RequestBody Contrato contrato, @PathVariable String id_imovel, @PathVariable String id_usuario) throws Exception {
        if ((imovelServiceAPI.get(id_imovel) != null) && (usuarioServiceAPI.get(id_usuario) != null)) {
            String res = contratoServiceIMPL.validarCriarContrato(contrato, id_imovel);
            if (res.equals("safe")){
                String id = contratoServiceAPI.save(contrato);
                return new ResponseEntity<String>(id, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("Contrato inválido", HttpStatus.BAD_REQUEST);
        }
    }
    */

    /*
    @DeleteMapping(value = "usuarios/contratos/{id}")
    public ResponseEntity<String> delete (@PathVariable String id) throws Exception {
        ContratoDTO contrato = contratoServiceAPI.get(id);
        if (contrato == null) {
            return new ResponseEntity<String>("ID inválido, tente novamente", HttpStatus.BAD_REQUEST);
        } else {
            contratoServiceAPI.delete(id);
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }
    }
    */
}
