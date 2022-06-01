package kz.ne.railways.tezcustoms.service.service;

import kz.ne.railways.tezcustoms.service.model.FormData;

import java.io.IOException;

public interface ContractsService {
    public FormData loadContract(String expCode, String invoiceNum) throws IOException;

}
