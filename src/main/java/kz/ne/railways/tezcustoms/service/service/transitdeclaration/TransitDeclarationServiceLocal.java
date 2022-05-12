package kz.ne.railways.tezcustoms.service.service.transitdeclaration;

import ru.customs.information.customsdocuments.esadout_cu._5_11.ESADoutCUType;

public interface TransitDeclarationServiceLocal {
	public ESADoutCUType build(long invoiceId);
	public String getXml(ESADoutCUType value);
	public void send(String invoiceId, String name, String surname);
}
