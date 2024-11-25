package bo.edu.ucb.TECWEB_G1_Admin_backend.bl;

import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.DocumentoLeidoDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.MedallaGanadaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.MedallaDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dao.PokemonDao;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.DocumentoLeidoDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.dto.MedallaGanadaDto;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.DocumentoLeido;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Medalla;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.MedallaGanada;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Pokemon;
import bo.edu.ucb.TECWEB_G1_Admin_backend.entity.Alumno;
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
public class DocumentoMedallaBl {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoMedallaBl.class);
    private final DocumentoLeidoDao documentoLeidoDao;
    private final MedallaGanadaDao medallaGanadaDao;
    private final MedallaDao medallaDao;
    private final PokemonDao pokemonDao;

    @Autowired
    public DocumentoMedallaBl(DocumentoLeidoDao documentoLeidoDao, MedallaGanadaDao medallaGanadaDao, MedallaDao medallaDao, PokemonDao pokemonDao) {
        this.documentoLeidoDao = documentoLeidoDao;
        this.medallaGanadaDao = medallaGanadaDao;
        this.medallaDao = medallaDao;
        this.pokemonDao = pokemonDao;
    }

    // registrar un documento leído y otorgar medalla
    public DocumentoLeidoDto createDocumentoLeido(DocumentoLeidoDto documentoLeidoDto) {
        try {
            DocumentoLeido documentoLeido = new DocumentoLeido();
            documentoLeido.setFecha(documentoLeidoDto.getFecha());
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(documentoLeidoDto.getIdAlumno());
            documentoLeido.setAlumno(alumno);
            Documento documento = new Documento();
            documento.setIdDocumento(documentoLeidoDto.getIdDocumento());
            documentoLeido.setDocumento(documento);

            DocumentoLeido savedDocumentoLeido = documentoLeidoDao.save(documentoLeido);
            LOG.info("Documento leído registrado correctamente");

            // obtener las medallas correspondientes al documento
            List<Medalla> medallas = medallaDao.findByDocumentoIdDocumentoAndIsDeletedFalse(documentoLeidoDto.getIdDocumento());
            for (Medalla medalla : medallas) {
                // crear registro de medalla ganada
                MedallaGanada medallaGanada = new MedallaGanada();
                medallaGanada.setFecha(documentoLeidoDto.getFecha());
                medallaGanada.setAlumno(alumno);
                medallaGanada.setMedalla(medalla);
                medallaGanadaDao.save(medallaGanada);
                LOG.info("Medalla otorgada correctamente");

                // sumar puntaje de la medalla al nivel del Pokemon del alumno
                Optional<Pokemon> pokemonOpt = pokemonDao.findByAlumnoIdAlumnoAndIsDeletedFalse(documentoLeidoDto.getIdAlumno());
                if (pokemonOpt.isPresent()) {
                    Pokemon pokemon = pokemonOpt.get();
                    pokemon.setNivel(pokemon.getNivel() + medalla.getPuntaje().intValue());
                    pokemonDao.save(pokemon);
                    LOG.info("Nivel del Pokemon actualizado correctamente");
                }
            }
            return new DocumentoLeidoDto(savedDocumentoLeido.getIdDocumentoLeido(), savedDocumentoLeido.getFecha(), savedDocumentoLeido.getDocumento().getIdDocumento(), savedDocumentoLeido.getAlumno().getIdAlumno());
        } catch (Exception ex) {
            LOG.error("Error al registrar el documento leído: {}", ex.getMessage());
            return null;
        }
    }

    // obtener todos los documentos leídos de un alumno
    public List<DocumentoLeidoDto> findAllDocumentosLeidosByAlumno(Long idAlumno) {
        try {
            List<DocumentoLeido> documentosLeidos = documentoLeidoDao.findByAlumnoIdAlumnoAndIsDeletedFalse(idAlumno);
            List<DocumentoLeidoDto> documentoLeidoDtos = new ArrayList<>();
            for (DocumentoLeido documentoLeido : documentosLeidos) {
                documentoLeidoDtos.add(new DocumentoLeidoDto(documentoLeido.getIdDocumentoLeido(), documentoLeido.getFecha(), documentoLeido.getDocumento().getIdDocumento(), documentoLeido.getAlumno().getIdAlumno()));
            }
            LOG.info("Documentos leídos obtenidos correctamente para el alumno con ID: {}", idAlumno);
            return documentoLeidoDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener los documentos leídos del alumno con ID {}: {}", idAlumno, ex.getMessage());
            return Collections.emptyList();
        }
    }

    // obtener todas las medallas ganadas de un alumno
    public List<MedallaGanadaDto> findAllMedallasGanadasByAlumno(Long idAlumno) {
        try {
            List<MedallaGanada> medallasGanadas = medallaGanadaDao.findByAlumnoIdAlumnoAndIsDeletedFalse(idAlumno);
            List<MedallaGanadaDto> medallaGanadaDtos = new ArrayList<>();
            for (MedallaGanada medallaGanada : medallasGanadas) {
                medallaGanadaDtos.add(new MedallaGanadaDto(medallaGanada.getIdMedallaGanada(), medallaGanada.getFecha(), medallaGanada.getAlumno().getIdAlumno(), medallaGanada.getMedalla().getIdMedalla()));
            }
            LOG.info("Medallas ganadas obtenidas correctamente para el alumno con ID: {}", idAlumno);
            return medallaGanadaDtos;
        } catch (Exception ex) {
            LOG.error("Error al obtener las medallas ganadas del alumno con ID {}: {}", idAlumno, ex.getMessage());
            return Collections.emptyList();
        }
    }
}
