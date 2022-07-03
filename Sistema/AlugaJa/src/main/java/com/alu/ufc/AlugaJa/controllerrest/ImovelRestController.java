package com.alu.ufc.AlugaJa.controllerrest;

import java.util.List;
import com.alu.ufc.AlugaJa.service.ImovelServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;
import com.alu.ufc.AlugaJa.models.Imovel;


@RestController
@RequestMapping(value = "/api/v1/")
public class ImovelRestController {
    @Autowired
    private ImovelServiceIMPL imovelServiceIMPL;

    @GetMapping(value = "imoveis/")
    public ResponseEntity<List<ImovelDTO>> getAll() throws Exception {
        return new ResponseEntity<List<ImovelDTO>>(imovelServiceIMPL.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "imoveis/{id_imovel}")
    public ResponseEntity<ImovelDTO> find(@PathVariable String id_imovel) throws Exception {
        return imovelServiceIMPL.get(id_imovel);
    }


    @PutMapping(value = "imoveis/{id_imovel}")
    public ResponseEntity<String> update(@RequestBody Imovel imovel, @PathVariable String id_imovel) throws Exception {
        return imovelServiceIMPL.update(imovel, id_imovel);
    }

    @PostMapping(value = "imoveis/")
    public ResponseEntity<String> save2(@RequestBody Imovel imovel) throws Exception {
        return imovelServiceIMPL.save(imovel);
    }

    @DeleteMapping(value = "imoveis/{id_imovel}")
    public ResponseEntity<String> delete(@PathVariable String id_imovel, @RequestBody String id_locador) throws Exception {
        return imovelServiceIMPL.delete(id_imovel, id_locador);
    }
}