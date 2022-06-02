package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.payload.response.CountryResponse;
import kz.ne.railways.tezcustoms.service.payload.response.CustomResponse;
import kz.ne.railways.tezcustoms.service.payload.response.SimpleResponse;
import kz.ne.railways.tezcustoms.service.payload.response.StationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DictionaryBean implements DictionaryBeanLocal {

    private final EntityManager entityManager;
    private final MessageSource messageSource;
    List<String> defaultStations = Arrays.asList("Достык (эксп.)", "Алтынколь", "Хоргос");

    private List<SimpleResponse> transitDirectionCodes;


    @Override
    public List<StationResponse> getStationList(String query) {
        query = query.toUpperCase();
        List<Object> result;
        StringBuilder builder = new StringBuilder(" select st_un, sta_no, sta_name from NSI.STA ");
        builder.append(" where ST_END > CURRENT_TIMESTAMP ");

        boolean existQuery = !StringUtils.isEmpty(query);
        if (existQuery) {
            if (NumberUtils.isCreatable(query)) {
                builder.append(" and STA_NO like :v  ");
            } else {
                builder.append(" and upper(STA_NAME) like :v");
            }
        } else {
            builder.append(" and sta_name in (");
            for (int i = 0; i < defaultStations.size(); i++) {
                builder.append("'").append(defaultStations.get(i)).append("'");
                if (i != defaultStations.size() - 1)
                    builder.append(",");
            }
            builder.append(")");
        }

        Query sql = getQuery(query, builder, existQuery);

        sql.setMaxResults(15);
        result = (List<Object>) sql.getResultList();
        List<StationResponse> stationList = null;

        if (result != null && !result.isEmpty()) {
            stationList = new ArrayList<>();

            for (Object o : result) {
                Object[] row = (Object[]) o;
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
    public List<CountryResponse> getCountryList(String query) {
        query = query.toUpperCase();
        List<Object> result;
        StringBuilder builder = new StringBuilder(" select country_no, country_name from NSI.COUNTRY ");
        builder.append(" where COU_END > CURRENT_TIMESTAMP ");

        boolean existQuery = !StringUtils.isEmpty(query);
        if (existQuery) {
            if (NumberUtils.isCreatable(query)) {
                builder.append(" and COUNTRY_NO like :v  ");
            } else {
                builder.append(" and upper(COUNTRY_NAME) like :v");
            }
        }

        Query sql = getQuery(query, builder, existQuery);

        result = (List<Object>) sql.getResultList();
        List<CountryResponse> countryList = null;

        if (result != null && !result.isEmpty()) {
            countryList = new ArrayList<>();

            for (Object o : result) {
                Object[] row = (Object[]) o;
                CountryResponse station = new CountryResponse();
                station.setCountryCode(String.valueOf(row[0]));
                station.setCountryName(String.valueOf(row[1]));
                countryList.add(station);
            }
        }

        return countryList;
    }

    @Override
    public List<CustomResponse> getCustomList(String query) {
        query = query.toUpperCase();
        List<Object> result;
        StringBuilder builder =
                        new StringBuilder(" select custom_code, custom_name, customs_org_un from NSI.ne_customs_orgs ");
        builder.append(" where custom_org_end > CURRENT_TIMESTAMP ");

        boolean existQuery = !StringUtils.isEmpty(query);
        if (existQuery) {
            if (NumberUtils.isCreatable(query)) {
                builder.append(" and custom_code like :v  ");
            } else {
                builder.append(" and upper(custom_name) like :v");
            }
        }

        Query sql = getQuery(query, builder, existQuery);

        result = (List<Object>) sql.getResultList();
        List<CustomResponse> customList = null;

        if (result != null && !result.isEmpty()) {
            customList = new ArrayList<>();

            for (Object o : result) {
                Object[] row = (Object[]) o;
                CustomResponse custom = new CustomResponse();
                custom.setCustomCode(String.valueOf(row[0]));
                custom.setCustomName(String.valueOf(row[1]));
                custom.setCustomId(String.valueOf(row[2]));
                customList.add(custom);
            }
        }

        return customList;
    }

    private Query getQuery(String query, StringBuilder builder, boolean existQuery) {
        Query sql = entityManager.createNativeQuery(builder.toString());

        if (existQuery) {
            if (NumberUtils.isCreatable(query)) {
                sql.setParameter("v", query + "%");
            } else {
                sql.setParameter("v", "%" + query.toUpperCase() + "%");
            }
        }
        return sql;
    }

    public List<SimpleResponse> getTransitDirectionCodes() {
        if (Objects.isNull(transitDirectionCodes)) {
            transitDirectionCodes = List.of(
                    new SimpleResponse(1, messageGetter("transit.direction.code1")),
                    new SimpleResponse(2, messageGetter("transit.direction.code2")),
                    new SimpleResponse(3, messageGetter("transit.direction.code3"))
            );
        }
        return transitDirectionCodes;
    }

    private String messageGetter(String name) {
        return messageSource.getMessage(name, null, Locale.getDefault());
    }

}
