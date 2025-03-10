package com.gestionCobros.PruebaIOT.business;

import com.gestionCobros.PruebaIOT.dto.EstadoDocumento;
import com.gestionCobros.PruebaIOT.entity.DocumentoCobro;
import com.gestionCobros.PruebaIOT.repository.DocumentoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DocumentoServices {

    private final DocumentoRepository documentoRepository;

    @Autowired
    public DocumentoServices(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<DocumentoCobro> GetDocumentos()
    {
        return documentoRepository.findAll();
    }
    public List<DocumentoCobro> GetDocumentosPendientes()
    {
        return documentoRepository.findByEstadoIn(List.of(EstadoDocumento.PENDIENTE, EstadoDocumento.VENCIDO));

    }

  /*  public DocumentoCobro updateEstadoACedido(Long id)
    {
        return documentoRepository.findById(id).map(doc -> {
            doc.setEstado(EstadoDocumento.CEDIDO);

            return documentoRepository.save(doc);
        }).orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }*/
  public ResponseEntity<Object> updateEstadoACedido(Long id) {
      // Buscar el documento por ID
      Optional<DocumentoCobro> documentoOpt = documentoRepository.findById(id);
      HashMap<String, Object> datos = new HashMap<>();

      if (!documentoOpt.isPresent()) {
          datos.put("error", true);
          datos.put("message", "Documento no encontrado!!");
          return  new ResponseEntity<>(
                  datos,
                  HttpStatus.NOT_FOUND);
      }

      DocumentoCobro documento = documentoOpt.get();

      // Verificar si ya está en estado CEDIDO
      if (documento.getEstado() == EstadoDocumento.CEDIDO) {
          datos.put("error", true);
          datos.put("message", "El documento ya está en estado CEDIDO y no puede ser actualizado!!");
          return  new ResponseEntity<>(
                  datos,
                  HttpStatus.CONFLICT);
      }

      // Actualizar estado a CEDIDO y guardar cambios
      documento.setEstado(EstadoDocumento.CEDIDO);
      documentoRepository.save(documento);

      datos.put("data", documento);
      datos.put("message", " Estado actualizado a CEDIDO correctamente!!");
      return  new ResponseEntity<>(
              datos,
              HttpStatus.ACCEPTED
      );
  }

    public ResponseEntity<Object> NewDocumento(DocumentoCobro documento) throws IllegalAccessException {
     Optional<DocumentoCobro> respuesta = documentoRepository.findNumerodocumento(documento.getNumerodocumento());
        HashMap<String, Object> datos = new HashMap<>();

     if(respuesta.isPresent())
     {
         datos.put("error", true);
         datos.put("message", "Documento ya existe!!");
         return  new ResponseEntity<>(
                 datos,
                 HttpStatus.CONFLICT
         );
     }

    // Validar si el estado es inválido y asignar valor por defecto
    if (documento.getEstado() == null || !esEstadoValido(documento.getEstado())) {
        documento.setEstado(EstadoDocumento.PENDIENTE);
    }
     documentoRepository.save(documento);

        datos.put("data", documento);
        datos.put("message", " Se guardo correctamente!!");
        return  new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    /**
     * Método auxiliar para validar si un estado es válido dentro de la enumeración.
     */
    private boolean esEstadoValido(EstadoDocumento estado) {
        for (EstadoDocumento e : EstadoDocumento.values()) {
            if (e == estado) {
                return true;
            }
        }
        return false;
    }
}
