package kz.ne.railways.tezcustoms.service.service.transitdeclaration;

import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;
import ru.customs.information.customsdocuments.esadout_cu._5_11.ESADoutCUType;
import ru.customs.information.customsdocuments.pirwinformationcu._5_11.PIRWInformationCUType;

public interface ICustomsReqcuestDecarartion {

    public SaveDeclarationResponseType send(String user, String password, String customsCode, String request);

    public SaveDeclarationResponseType send(String user, String password, String customsCode,
                    PIRWInformationCUType request) throws Exception;

    public String objectToString(PIRWInformationCUType request);

    public SaveDeclarationResponseType sendTD(String user, String password, String customsCode, String request);

    public SaveDeclarationResponseType sendTD(String user, String password, String customsCode, ESADoutCUType request)
                    throws Exception;

    public String objectToStringTD(ESADoutCUType request);

    public void setProxyHost(String proxyHost);

    public void setProxyPort(String proxyPort);

}
