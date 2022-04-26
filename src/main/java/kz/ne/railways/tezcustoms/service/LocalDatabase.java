package kz.ne.railways.tezcustoms.service;

import kz.ne.railways.tezcustoms.service.model.Contract;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocalDatabase {
    public List<Contract> contractList;
}
