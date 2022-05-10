package kz.ne.railways.tezcustoms.service;

import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.entity.asudkr.NeSmgsAdditionDocuments;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocalDatabase {
    public List<Contract> contractList = new ArrayList<Contract>();
    public List<NeSmgsAdditionDocuments> documents = new ArrayList<NeSmgsAdditionDocuments>();
}
