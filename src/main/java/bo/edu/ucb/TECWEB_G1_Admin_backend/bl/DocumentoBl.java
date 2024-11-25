package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.DocumentoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocumentoDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Documento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoBl {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentoBl.class);
    private final DocumentoDao documentoDao;

    @Autowired
    public DocumentoBl(DocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    // obtener todos los documentos que no están eliminados
    public List<DocumentoDto> findAll() {
        try {
            List<Documento> documentos = documentoDao.findByIsDeletedFalse();
            List<DocumentoDto> documentoDtos = new ArrayList<>();
            for (Documento d : documentos) {
                documentoDtos.add(new DocumentoDto(d.getIdDocumento(), d.getTitulo(), d.getDireccion(), d.getAutores(), d.getDescripcion()));
            }
            LOG.info("Documentos obtenidos correctamente");
            return documentoDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los documentos: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener un documento por ID
    public DocumentoDto findById(Long idDocumento) {
        try {
            Optional<Documento> documentoOpt = documentoDao.findById(idDocumento);
            if (documentoOpt.isPresent() && !documentoOpt.get().getIsDeleted()) {
                Documento documento = documentoOpt.get();
                LOG.info("Documento obtenido correctamente");
                return new DocumentoDto(documento.getIdDocumento(), documento.getTitulo(), documento.getDireccion(), documento.getAutores(), documento.getDescripcion());
            }
            LOG.error("Documento con ID {} no encontrado", idDocumento);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al obtener el documento por ID: {}", ex.getMessage());
            return null;
        }
    }

    // crear un nuevo documento
    public DocumentoDto createDocumento(DocumentoDto documentoDto) {
        try {
            Documento documento = new Documento();
            documento.setTitulo(documentoDto.getTitulo());
            documento.setDireccion(documentoDto.getDireccion());
            documento.setAutores(documentoDto.getAutores());
            documento.setDescripcion(documentoDto.getDescripcion());

            Documento savedDocumento = documentoDao.save(documento);
            LOG.info("Documento creado correctamente");
            return new DocumentoDto(savedDocumento.getIdDocumento(), savedDocumento.getTitulo(), savedDocumento.getDireccion(), savedDocumento.getAutores(), savedDocumento.getDescripcion());
        } catch (Exception ex) {
            LOG.error("Error al crear el documento: {}", ex.getMessage());
            return null;
        }
    }

    // actualizar un documento existente por ID
    public DocumentoDto updateDocumento(Long idDocumento, DocumentoDto documentoDto) {
        try {
            Optional<Documento> documentoOpt = documentoDao.findById(idDocumento);
            if (documentoOpt.isPresent() && !documentoOpt.get().getIsDeleted()) {
                Documento documento = documentoOpt.get();
                documento.setTitulo(documentoDto.getTitulo());
                documento.setDireccion(documentoDto.getDireccion());
                documento.setAutores(documentoDto.getAutores());
                documento.setDescripcion(documentoDto.getDescripcion());

                Documento updatedDocumento = documentoDao.save(documento);
                LOG.info("Documento actualizado correctamente");
                return new DocumentoDto(updatedDocumento.getIdDocumento(), updatedDocumento.getTitulo(), updatedDocumento.getDireccion(), updatedDocumento.getAutores(), updatedDocumento.getDescripcion());
            }
            LOG.error("Documento con ID {} no encontrado", idDocumento);
            return null;
        } catch (Exception ex) {
            LOG.error("Error al actualizar el documento: {}", ex.getMessage());
            return null;
        }
    }

    // eliminar un documento (borrado lógico)
    public void deleteDocumento(Long idDocumento) {
        try {
            Optional<Documento> documentoOpt = documentoDao.findById(idDocumento);
            if (documentoOpt.isPresent() && !documentoOpt.get().getIsDeleted()) {
                Documento documento = documentoOpt.get();
                documento.setIsDeleted(true);
                documentoDao.save(documento);
                LOG.info("Documento eliminado correctamente");
            }
        } catch (Exception ex) {
            LOG.error("Error al eliminar el documento: {}", ex.getMessage());
        }
    }
}
