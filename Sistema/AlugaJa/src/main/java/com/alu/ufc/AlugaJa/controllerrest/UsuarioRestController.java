package com.alu.ufc.AlugaJa.controllerrest;


import java.util.List;
import com.alu.ufc.AlugaJa.models.Usuario;
import com.alu.ufc.AlugaJa.modelsDTO.UsuarioDTO;
import com.alu.ufc.AlugaJa.service.ImovelServiceIMPL;
import com.alu.ufc.AlugaJa.service.UsuarioServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alu.ufc.AlugaJa.modelsDTO.ImovelDTO;

@RestController
@RequestMapping(value = "/api/v1/")
public class UsuarioRestController {
    @Autowired
    private UsuarioServiceIMPL usuarioServiceIMPL;
    @Autowired
    private ImovelServiceIMPL imovelServiceIMPL;

    @GetMapping(value = "usuarios/")
    public List<UsuarioDTO> getAll() throws Exception {
        return usuarioServiceIMPL.getAll();
    }

    @GetMapping(value = "usuarios/{id_usuario}")
    public ResponseEntity<UsuarioDTO> find(@PathVariable String id_usuario) throws Exception {
        return usuarioServiceIMPL.get(id_usuario);
    }

    @GetMapping(value = "usuarios/{id_usuario}/imoveis/")
    public ResponseEntity<List<ImovelDTO>> imoveisUsuario(@PathVariable String id_usuario) throws Exception {
        return imovelServiceIMPL.imoveisDoUsuario(id_usuario);
    }

    @PutMapping(value = "usuarios/{id_usuario}")
    public ResponseEntity<String> update(@RequestBody Usuario usuario, @PathVariable String id_usuario) throws Exception {
        return usuarioServiceIMPL.update(usuario, id_usuario);
    }

    @PostMapping(value = "usuarios/")
    public ResponseEntity<String> save(@RequestBody Usuario usuario) throws Exception {
        return usuarioServiceIMPL.save(usuario);
    }

    @DeleteMapping(value = "usuarios/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception {
        return usuarioServiceIMPL.delete(id);
    }
}