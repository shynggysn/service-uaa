package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.model.preliminary_information.FormData;

public interface ContractsService {

    FormData loadContract(String expCode, String invoiceNum, int year, int month);

}
