package es.caib.utilitatsfirma.back.controller;

import es.caib.utilitatsfirma.persistence.FitxerJPA;
import es.caib.utilitatsfirma.model.entity.Fitxer;

import org.fundaciobit.genapp.common.filesystem.IFileManager;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;


/**
 * Gestiona Multiples Fitxers d'un Form
 * 
 * @author anadal
 * 
 */
public class UtilitatsFirmaFilesFormManager extends FilesFormManager<Fitxer> {

  public UtilitatsFirmaFilesFormManager(IFileManager<Fitxer> fitxerEjb) {
    super(fitxerEjb);
  }

  @Override
  public FitxerJPA createEmptyFile() {    
    return new FitxerJPA();
  }

}
