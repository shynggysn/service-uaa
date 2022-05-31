package kz.ne.railways.tezcustoms.service.util;

import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;
import ru.customs.information.customsdocuments.pirwinformationcu._5_11.PIRWInformationCUType;

public interface PredInfoSenderLocal {
    public SaveDeclarationResponseType send(String user, String password, String customsCode,
                    PIRWInformationCUType data);

    public SaveDeclarationResponseType send(PIRWInformationCUType data);
}
