package com.gestionCobros.PruebaIOT.controller;

import com.gestionCobros.PruebaIOT.business.DocumentoServices;
import com.gestionCobros.PruebaIOT.entity.DocumentoCobro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/documento")
public class DocumentoController
{

    private final DocumentoServices documentoServices;

    @Autowired
    public DocumentoController(DocumentoServices documentoServices) {
        this.documentoServices = documentoServices;
    }

    @GetMapping
    public List<DocumentoCobro> GetDocumentos()
    {
        return documentoServices.GetDocumentos();
    }

    @GetMapping("/pendientes")
    public List<DocumentoCobro> GetDocumentosPendientes()
    {
      return documentoServices.GetDocumentosPendientes();
    }
    @PostMapping()
    public ResponseEntity<Object> AddDocumento(@RequestBody DocumentoCobro documento) throws IllegalAccessException {
      return   this.documentoServices.NewDocumento(documento);
    }

    @PostMapping("/updateEstado")
    public ResponseEntity<Object>  actualizarEstadoACedido(@RequestBody Map<String, Long> body) throws IllegalAccessException {
        Long id = body.get("id");
        return this.documentoServices.updateEstadoACedido(id);

    }
}
