package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.payload.response.BinResponse;
import kz.ne.railways.tezcustoms.service.payload.response.StationResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class DictionaryBean implements DictionaryBeanLocal{

    List<String> defaultStations = Arrays.asList("Достык (эксп.)", "Алтынколь", "Хоргос");

    @PersistenceContext
    EntityManager em;

    @Override
    public List<StationResponse> getStationList(String query) {
        query = query.toUpperCase();
        List<Object> result;
        StringBuilder builder = new StringBuilder(
                " select st_un, sta_no, sta_name from NSI.STA ");
        builder.append(" where ST_END > CURRENT_TIMESTAMP ");

        boolean existQuery = !StringUtils.isEmpty(query);
        if (existQuery) {
            if (NumberUtils.isNumber(query)) {
                builder.append(" and STA_NO like :v  ");
            } else {
                builder.append(" and upper(STA_NAME) like :v");
            }
        } else {
            builder.append(" and sta_name in (");
            for (int i = 0; i < defaultStations.size(); i++){
                builder.append("'" + defaultStations.get(i) + "'");
                if(i != defaultStations.size()-1)
                    builder.append(",");
            }
            builder.append(")");
        }

        Query sql = em.createNativeQuery(builder.toString());

        if (existQuery) {
            if (NumberUtils.isNumber(query)) {
                sql.setParameter("v", query+ "%");
            } else {
                sql.setParameter("v", "%" + query.toUpperCase() + "%");
            }
        }

        sql.setMaxResults(15);
        result = (List<Object>) sql.getResultList();
        List<StationResponse> stationList = null;
        
        if(result != null & !result.isEmpty()){
            stationList = new ArrayList<>();
            Iterator it = result.iterator();

            while (it.hasNext()) {
                Object[] row = (Object[]) it.next();
                StationResponse station = new StationResponse();
                station.setStationId(String.valueOf(row[0]));
                station.setStationNumber(String.valueOf(row[1]));
                station.setStationName(String.valueOf(row[2]));
                stationList.add(station);
            }
        }

        return stationList;
    }

    @Override
    public List getCountryList(String query) {
        return null;
    }
}