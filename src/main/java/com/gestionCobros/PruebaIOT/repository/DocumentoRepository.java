package com.gestionCobros.PruebaIOT.repository;

import com.gestionCobros.PruebaIOT.dto.EstadoDocumento;
import com.gestionCobros.PruebaIOT.entity.DocumentoCobro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<DocumentoCobro, Long> {
    List<DocumentoCobro> findByEstadoIn(List<EstadoDocumento> estados);

    //@Query("Select * from DocumentoCobro  d where d.Numerodocumento = ?")
   //Optional <DocumentoCobro> findNumerodocumento(String Numerodocumento);
    @Query("SELECT d FROM DocumentoCobro d WHERE d.Numerodocumento = :documento")
    Optional<DocumentoCobro> findNumerodocumento(@Param("documento") String documento);
}
