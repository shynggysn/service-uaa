package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.entity.asudkr.*;
import kz.ne.railways.tezcustoms.service.model.DataCaneVagInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PrevInfoBeanDAOLocal {

    public Integer existLikeInvoiceByNumAndSta(Long invoiceId);

    public Country getCountry(String countryCode);

    public Country getCountry(Long countryUn);

    public NeCustomsOrgs getCustomsOrgs(Long customOrgUn);

    public CurrencyCode getCurrencyCode(long curCodeUn);

    public List<CurrencyCode> getCurrencyCodeList(Date date);

    public Country getCountryByManagNo(int managNo);

    public Country getCountryByManagUn(Long managUn);

    public Country getCountryBycode(String countryCode);

    public Management getManagement(Long managUn);

    public Map<String, DataCaneVagInfo> getDatacaneVagInfo(List<String> dataCaneVagons);

    public List<TnVedRow> getGridDatas(Long invoiceUn);

    public List<NeVagonLists> getVagonList(Long invoiceUn);

    public NeInvoice getInvoice(Long invoiceUn);

    NeInvcRefPi getNeInvcRefPi(final Long invoiceId);

    public NeSmgsCargo getNeSmgsCargo(Long invoiceUn);

    public NeInvoicePrevInfo getInvoicePrevInfo(Long invoiceUn);

    public NeSmgsSenderInfo getSenderInfo(Long invoiceUn);

    public Sta getStation(String stationCode);

    public String getStationName(String stationCode, boolean onlyName);

    public List<NeSmgsTnVedDocuments> getSmgsTnVedDocuments(Long tnVedId);

    public List<NeCurrencyRates> getRates(Date date);

    public NeSmgsRecieverInfo getRecieverInfo(Long invoiceUn);

    public NeSmgsDestinationPlaceInfo getNeSmgsDestinationPlaceInfo(Long invoiceUn);

    public List<NeSmgsTnVed> getTnVedList(Long invoiceUn);

    public NeVagonGroup getVagonGroup(Long id);

    public NeSmgsTnVedDocuments getSmgsTnVedDocumentsById(Long id);

    public NeSmgsDeclarantInfo getDeclarantInfo(Long invoiceUn);

    public NeSmgsExpeditorInfo getExpeditorInfo(Long invoiceUn);

    public List<NeContainerLists> getContinerList(Long id);

    public NeSmgsShipList getNeSmgsShipList(Long invoiceUn);

    public List<NeUnitType> getUnitType();

    public NePersonCategoryType getPersonCategoryType(Long categoryType);

    public NeVessel getVessel(Long un);

    void updatePrevInfoStatus(NeInvoicePrevInfo invoicePrevInfo);

}
