package kz.ne.railways.tezcustoms.service.service.bean;


import kz.ne.railways.tezcustoms.service.payload.response.StationResponse;

import java.util.List;

public interface DictionaryBeanLocal {
    List<StationResponse> getStationList(String query);
    List getCountryList(String query);
}