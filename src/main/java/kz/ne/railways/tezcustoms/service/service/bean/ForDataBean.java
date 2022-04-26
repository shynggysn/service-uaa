package kz.ne.railways.tezcustoms.service.service.bean;

import com.google.gson.Gson;
import kz.ne.railways.tezcustoms.service.LocalDatabase;
import kz.ne.railways.tezcustoms.service.model.Contract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ForDataBean implements ForDataBeanLocal {

    private final LocalDatabase localDatabase;

    private Gson gson = new Gson();

    @Override
    public String getContracts(String username) {
        List<Contract> list = localDatabase.contractList;
        List<Contract> result = new ArrayList<Contract>();
        for (Contract contract : list) {
            if (contract.getUser().equals(username))
                result.add(contract);
        }
        return gson.toJson(result);
    }
}
