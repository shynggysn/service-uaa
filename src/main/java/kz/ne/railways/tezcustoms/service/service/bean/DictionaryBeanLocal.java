package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.payload.response.SimpleResponse;

import java.util.List;

public interface DictionaryBeanLocal {
    List<SimpleResponse> getStationList(String query);

    List<SimpleResponse> getCountryList(String query);

    List<SimpleResponse> getCustomList(String query);

    List<SimpleResponse> getTransitDirectionCodes();
}
