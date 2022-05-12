package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.entity.asudkr.*;
import kz.ne.railways.tezcustoms.service.model.DataCaneVagInfo;
import kz.ne.railways.tezcustoms.service.model.VagonItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class PrevInfoBeanDAO implements PrevInfoBeanDAOLocal {

    @PersistenceContext
    private EntityManager em;

    public Integer existLikeInvoiceByNumAndSta(Long invoiceId) {
        Integer count = 0; //Если 0 даем отправить!

		/*-->TDG-5086 Ввиду совершенствования логики определения дублированных из ПИ и IFTMIN, приостановить публикацию на промышленный сервер задачи 4763
		NeInvoice invoicePI = getInvoice(invoiceId);
		if (invoicePI!=null && invoicePI.getInvcNum()!=null && invoicePI.getReciveStationCode()!=null && invoicePI.getDestStationCode()!=null) {
			String sqlText = "";

			sqlText = "SELECT count(*) AS cnt FROM KTZ.NE_INVOICE ni " +
					"INNER JOIN ktz.NE_INVOICE_INFO nii ON nii.INVOICE_UN = ni.INVC_UN " +
					"WHERE ni.INVC_NUM = '" + invoicePI.getInvcNum() + "' " +
					"AND ni.DEST_STATION_CODE = '" + invoicePI.getDestStationCode() + "' " +
					"AND ni.RECIVE_STATION_CODE = '" + invoicePI.getReciveStationCode() + "' " +
					"AND nii.INVOICE_CREATED_BY = 'IFTMIN' " +
					"AND ni.INVOICE_DATETIME > CURRENT_TIMESTAMP - 1 MONTH ";

			try {
				System.out.println("* * * existLikeInvoiceByNumAndSta * * *  " + sqlText);
				count = (Integer) em.createNativeQuery(sqlText).getSingleResult();
			} catch (NoResultException e) {
			}
			System.out.println("* * * existLikeInvoiceByNumAndSta * * *  count = " + count);
			count = (count==null ? 0 : count);
		}
		System.out.println("* * * existLikeInvoiceByNumAndSta * * *  count = " + count);
		*/

        return count;
    }

    public List<TnVedRow> getGridDatas(Long invoiceUn) {
        List<TnVedRow> result = null;
        String sql = "select \n" + " a.SMGS_TN_VED_UN as id, \n" + " a.INVOICE_UN as invoiceUn,\n"
                        + " a.TN_VED_CODE as tnVedCode,\n" + " a.TN_VED_NAME as tnVedName,\n"
                        + " a.PRODUCT_DESCRIPTION as description,\n"
                        + " a.PRODUCT_DESCRIPTION_ADD as descriptionAdditionaly,\n"
                        + " a.COUNT_BY_UNIT as countByUnit,\n" + " a.UNIT_TYPE_UN as unitTypeUn,\n"
                        + " c.UNIT_NAME as unitTypeName,\n" + " a.PLACE_CARGO_COUNT as placeCargoCount,\n"
                        + " a.PACKING_COUNT as packingCount,\n" + " a.PAKAGE_PART_QUANTITY as pakagePartQuantity,\n"
                        + " a.PACKING_TYPE_UN as pakingTypeUn,\n" + " d.PACKING_NAME as packingTypeName,\n"
                        + " d.PACKING_CODE as packingCode, \n" + " a.PLACE_CARGO_MARK as placeCargoMark,\n"
                        + " a.NETTO_WEIGHT as netto,\n" + " a.BRUTTO_WEIGHT as brutto,\n"
                        + " a.PRICE_BY_ONE as priceByOne,\n" + " a.PRICE_BY_FULL as priceByTotal,\n"
                        + " a.CURRENCY_CODE_UN as currencyUn,\n" + " b.CUR_CODE as currencyName,\n"
                        + " b.CUR_CODE_LET as currencyCode, \n" + " a.CONTAINER as container, \n"
                        + " 'TEST!!!' as documents, \n" + " a.VAGON_ACCESSORY_COU_NO as vagonAccessoryCouNo, \n"
                        + "a.TN_VED_COUNTRY as tnVedCountry, \n" + "a.TN_VED_IS_ARMY as tnVedIsArmy \n"
                        + "from KTZ.NE_SMGS_TN_VED a\n"
                        + "left join NSI.CURRENCY_CODE b on a.CURRENCY_CODE_UN = b.CUR_CODE_UN AND b.CUR_CODE_END > CURRENT_TIMESTAMP\n"
                        + "left join NSI.NE_UNIT_TYPE c ON a.UNIT_TYPE_UN = c.UNIT_TYPE_UN AND c.UNIT_END > CURRENT_TIMESTAMP\n"
                        + "left join NSI.NE_PACKING_TYPE d on a.PACKING_TYPE_UN = d.PACKING_TYPE_UN AND d.PACKING_END > CURRENT_TIMESTAMP\n"
                        + "where a.INVOICE_UN = ?1 " + "order by a.SMGS_TN_VED_UN";
        result = em.createNativeQuery(sql, TnVedRow.class).setParameter(1, invoiceUn).getResultList();

        // System.out.println(sql + invoiceUn);

        Map<Long, String> docs = getTnVedDocuments(invoiceUn);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setDocuments(docs.get(result.get(i).getId()));
        }
        return result;
    }

    public Map<String, DataCaneVagInfo> getDatacaneVagInfo(List<String> dataCaneVagons) {
        Map<String, DataCaneVagInfo> map = new HashMap<String, DataCaneVagInfo>();
        if (dataCaneVagons.isEmpty())
            return map;
        Map<String, String> cacheMap = new HashMap<String, String>();
        StringBuffer buffer = new StringBuffer("select " + "NUM_VAG/*Номер вагона*/," + "VZ_D/*Собственник*/,"
                        + "KOD_S/*Код собственника*/," + "VCHD_PRIPIS/*Депо*/," + "NAME_SOB/*Имя собственника*/,"
                        + "TIP/*Тип Вагона*/," + "TARA/*Тара TARA/10*/," + "KOD_SOB/*Администрация*/,"
                        + "GRUZ/*Груз GRUZ/10*/," + "KOD_AR," + "KOD_AR_MG," + "PRIZ_AR," + "PRIZ_AR_MG," + "KOL_OSEY,"
                        + "KV2_6,/*желтый пробег*/" + "KV2_5/*красный пробег*/");
        buffer.append(" from ASOUP.AKPV_VAGON_DOR ");
        buffer.append(" where NUM_VAG in (");
        for (int i = 0; i < dataCaneVagons.size(); i++) {
            String toDataCane = StringUtils.leftPad(dataCaneVagons.get(i), 12, "0");
            cacheMap.put(toDataCane, dataCaneVagons.get(i));
            buffer.append(i == 0 ? "" : ",").append("'").append(toDataCane).append("'");
        }
        buffer.append(")");
        List<Object[]> result = new ArrayList<Object[]>();
        try {
            System.out.println(buffer.toString());
            result = em.createNativeQuery(buffer.toString()).getResultList();
        } catch (RuntimeException e) {
        }
        for (Object[] row : result) {
            System.out.println("row: " + row[0]);
            DataCaneVagInfo info = new DataCaneVagInfo();
            String vagNo = (String) row[0];
            info.setVagNoDC(vagNo.trim());
            Short prop = (Short) row[1];
            info.setPropertyDC(Integer.valueOf(prop));
            info.setOwnerCodeDC((Integer) row[2]);
            Short depo = (Short) row[3];
            info.setDepoDC(Integer.valueOf(depo));
            info.setOwnerNameDC((String) row[4]);
            Short type = (Short) row[5];
            info.setTypeVagDC(Integer.valueOf(type));
            Short tara = (Short) row[6];
            info.setTaraDC(Integer.valueOf(tara));
            Short rail = (Short) row[7];
            info.setRailwayNoDC(Integer.valueOf(rail));
            Short gp = (Short) row[8];
            info.setGpDC(Integer.valueOf(gp));

            info.setAxisCount(Integer.valueOf((Short) row[13]));
            // info.setYellowMileage((String) row[14]);
            // info.setRedMileage((String) row[15]);
            if (info.getDepoDC() != null && info.getPropertyDC() != null) {
                if (info.getDepoDC() == 6010 && info.getPropertyDC() == 0
                                && StringUtils.isBlank(info.getOwnerNameDC())) {
                    info.setOwnerCodeDC(680524);
                    info.setOwnerNameDC("АО \"Казтемиртранс\"");
                }
            }
            int prizAr = (Short) (row[11] == null ? 0 : row[11]);
            int prizArMg = (Short) (row[12] == null ? 0 : row[12]);
            if (prizAr > 0 || prizArMg > 0) {
                int ownerCode = (Integer) (row[9] == null ? 0 : row[9]);
                info.setOwnerCodeDC(ownerCode);
                info.setPropertyDC(3);
            }
            if (info.getDepoDC() != null && info.getPropertyDC() != null && info.getOwnerCodeDC() != null
                            && info.getRailwayNoDC() != null) {
                if (info.getDepoDC() == 0 && info.getPropertyDC() == 0 && info.getOwnerCodeDC() == 0
                                && (info.getRailwayNoDC() == 27 || info.getRailwayNoDC() == 0)) {
                    info.setOwnerCodeDC(680524);
                    info.setOwnerNameDC("АО \"Казтемиртранс\"");
                }
            }
            String key = cacheMap.get(info.getVagNoDC().trim());
            info.setVagNo(key);
            map.put(key, info);

        }
        cacheMap = null;
        buffer = null;
        result = null;
        return map;
    }

    public NeCustomsOrgs getCustomsOrgs(Long customOrgUn) {
        List<NeCustomsOrgs> list = em.createQuery("select a from NeCustomsOrgs a where a.customsOrgUn = ?1")
                .setParameter(1,customOrgUn)
                .getResultList();
        if(list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public CurrencyCode getCurrencyCode(long curCodeUn) {
        return em.find(CurrencyCode.class,curCodeUn);
    }

    public List<CurrencyCode> getCurrencyCodeList(Date date) {
        String sql = "select a from CurrencyCode a where (?1 between a.curCodeBgn and a.curCodeEnd)";
        return em.createQuery(sql).setParameter(1, date).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Map<Long, String> getTnVedDocuments(Long invoiceUn) {
        Map<Long, String> result = new HashMap<Long, String>();
        String sqlText = "select d1.SMGS_TNVED_DOCUMENTS_UN, d1.SMGS_TNVED_UN, d1.DOCUMENT_CODE, d1.DOCUMENT_NAME, d1.DOCUMENT_NUMBER, d1.DOCUMENT_DATE,  d1.DOCUMENT_DATE_TO, d1.COPY_COUNT, d1.LIST_COUNT "
                        + "from KTZ.NE_SMGS_TN_VED t1 "
                        + "JOIN KTZ.NE_SMGS_TNVED_DOCUMENTS d1 ON d1.SMGS_TNVED_UN=t1.SMGS_TN_VED_UN where t1.INVOICE_UN = ?1";
        List<NeSmgsTnVedDocuments> list = em.createNativeQuery(sqlText, NeSmgsTnVedDocuments.class)
                        .setParameter(1, invoiceUn).getResultList();
        for (NeSmgsTnVedDocuments item : list) {
            String docs = result.get(item.getSmgsTnVedUn());
            docs = (docs != null ? docs + ", " : "");
            docs += item.getDocumentCode();
            result.put(item.getSmgsTnVedUn(), docs);
        }
        return result;
    }

    public List<NeCurrencyRates> getRates(Date date) {
        String sql = "select * from NSI.NE_CURRENCY_RATES where (? between RATE_BGN and RATE_END) and CURRENCU_CODE_UN IN (select b.CUR_CODE_UN from nsi.CURRENCY_CODE b where ? between b.CUR_CODE_BGN and b.CUR_CODE_END)";
        List<NeCurrencyRates> list = em.createNativeQuery(sql, NeCurrencyRates.class)
                .setParameter(1, date)
                .setParameter(2, date)
                .getResultList();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<NeVagonLists> getVagonList(Long invoiceUn) {
        return em.createQuery("select a from NeVagonLists a where a.invcUn = :inv").setParameter("inv", invoiceUn)
                        .getResultList();
    }

    public Integer checkSend(String invoiceId, String invNum, String conteinerNum, List<VagonItem> vagonList,
                    String statusPI) {

        if (statusPI == null)
            statusPI = "";

        String vagNo = "";

        if (!vagonList.isEmpty() || vagonList.size() > 0) {
            for (VagonItem vagonItem : vagonList) {
                if (vagNo.equals(""))
                    vagNo = "'" + vagonItem.getNumber() + "'";
                else
                    vagNo = vagNo + ",'" + vagonItem.getNumber() + "'";
            }
        } else {
            vagNo = "''";
        }

        String sqlText = "";
        // Контейнер
        if (conteinerNum != null && !conteinerNum.equals("false")) {
            String[] strings = conteinerNum.split("-");
            sqlText = "SELECT count(inv.INVC_NUM) FROM KTZ.NE_INVOICE inv "
                            + "JOIN KTZ.NE_INVOICE_PREV_INFO pi ON inv.INVC_UN=pi.INVOICE_UN "
                            + "JOIN KTZ.NE_CONTAINER_LISTS cl ON cl.INVOICE_UN = pi.INVOICE_UN "
                            + "WHERE inv.INVC_NUM = '" + invNum + "' AND cl.CONTAINER_NO = '" + strings[1]
                            + "' AND cl.CONTAINER_MARK = '" + strings[0] + "' ";
            sqlText += "AND pi.RESPONSE_DATETIME > (CURRENT_TIMESTAMP - 12 hour) ";

            if (statusPI.equals("1") || statusPI.equals("2")) { // принят/отклоненный
                sqlText = "SELECT count(ri.INVC_UN) " + " FROM ktz.NE_INVC_REF_PI ri "
                                + " LEFT JOIN ktz.NE_INVOICE inv ON inv.INVC_UN = ri.INVC_UN " +
                                // " LEFT JOIN KTZ.NE_CONTAINER_LISTS cl ON cl.INVOICE_UN = ri.PREV_INFO_INVC_UN "+
                                " WHERE ri.PREV_INFO_INVC_UN = " + invoiceId
                                + " AND inv.INVC_STATUS NOT IN(100,101) AND inv.INVC_NUM = '" + invNum + "'";
                // "' AND cl.CONTAINER_NO = '"+strings[1]+"' AND cl.CONTAINER_MARK = '"+strings[0]+"' ";
            }
        } // Вагон
        else {
            sqlText = "SELECT count(inv.INVC_NUM) FROM KTZ.NE_INVOICE inv "
                            + "JOIN KTZ.NE_INVOICE_PREV_INFO pi ON inv.INVC_UN=pi.INVOICE_UN "
                            + "JOIN KTZ.NE_VAGON_LISTS vl ON vl.INVC_UN = pi.INVOICE_UN ";
            sqlText += "WHERE inv.INVC_NUM = '" + invNum + "' AND vl.VAG_NO in(" + vagNo + ") "
                            + "AND pi.RESPONSE_DATETIME IS NOT NULL ";
            sqlText += "AND pi.RESPONSE_DATETIME > (CURRENT_TIMESTAMP - 12 hour) ";

            if (statusPI.equals("1") || statusPI.equals("2")) { // принят/отклоненный
                sqlText = "SELECT count(ri.INVC_UN) " + " FROM ktz.NE_INVC_REF_PI ri "
                                + " LEFT JOIN ktz.NE_INVOICE inv ON inv.INVC_UN = ri.INVC_UN " +
                                // " LEFT JOIN KTZ.NE_VAGON_LISTS vl ON vl.INVC_UN = ri.PREV_INFO_INVC_UN "+
                                " WHERE ri.PREV_INFO_INVC_UN = " + invoiceId + " AND inv.INVC_STATUS NOT IN(100,101) ";
                // "AND inv.INVC_NUM = '"+invNum+"' AND vl.VAG_NO IN("+vagNo+")";
            }
        }

        Integer no = 0; // Если 0 даем отправить!
        try {
            System.out.println(sqlText);
            no = (Integer) em.createNativeQuery(sqlText).getSingleResult();
        } catch (NoResultException e) {
        }

        return no == null ? 0 : no;
    }

    public String getStationName(String stationCode, boolean onlyName) {
        List<Sta> list = em.createQuery("select a from Sta a where a.staNo = ?1 and a.stEnd > CURRENT_TIMESTAMP")
                        .setParameter(1, stationCode).getResultList();
        if (list.size() > 0) {
            Sta sta = list.get(0);
            if (onlyName)
                return sta.getStaName();
            else
                return sta.getStaNo() + " - " + sta.getStaName();
        }
        return null;
    }

    public NeInvoice getInvoice(Long invoiceUn) {
        List<NeInvoice> list = em.createQuery("select a from NeInvoice a where a.invcUn = ?1", NeInvoice.class)
                        .setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeInvcRefPi getNeInvcRefPi(final Long piId) {
        String sql = "select * from ktz.NE_INVC_REF_PI  where PREV_INFO_INVC_UN = ?";
        List<NeInvcRefPi> list = em.createNativeQuery(sql, NeInvcRefPi.class).setParameter(1, piId).getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public NeSmgsCargo getNeSmgsCargo(Long invoiceUn) {
        List<NeSmgsCargo> list = em.createQuery("select a from NeSmgsCargo a where a.invUn = ?1", NeSmgsCargo.class)
                        .setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeInvoicePrevInfo getInvoicePrevInfo(Long invoiceUn) {
        List<NeInvoicePrevInfo> list = em.createQuery("select a from NeInvoicePrevInfo a where a.invoiceUn = ?1",
                        NeInvoicePrevInfo.class).setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsSenderInfo getSenderInfo(Long invoiceUn) {
        List<NeSmgsSenderInfo> list =
                        em.createQuery("select a from NeSmgsSenderInfo a where a.invUn = ?1", NeSmgsSenderInfo.class)
                                        .setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsDeclarantInfo getDeclarantInfo(Long invoiceUn) {
        List<NeSmgsDeclarantInfo> list = em.createQuery("select a from NeSmgsDeclarantInfo a where a.invUn = ?1",
                        NeSmgsDeclarantInfo.class).setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    // DUPLICATE
    // public NeSmgsDeclarantInfo getDeclarant(Long id) {
    // List<NeSmgsDeclarantInfo> list = em.createQuery("select a from NeSmgsDeclarantInfo a where
    // a.invUn = ?")
    // .setParameter(1,id)
    // .getResultList();
    // if (list != null && !list.isEmpty()) {
    // return list.get(0);
    // }
    // return null;
    // }

    public NeSmgsExpeditorInfo getExpeditorInfo(Long invoiceUn) {
        List<NeSmgsExpeditorInfo> list = em.createQuery("select a from NeSmgsExpeditorInfo a where a.invUn = ?1",
                        NeSmgsExpeditorInfo.class).setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsRecieverInfo getRecieverInfo(Long invoiceUn) {
        List<NeSmgsRecieverInfo> list = em
                        .createQuery("select a from NeSmgsRecieverInfo a where a.invUn = ?1", NeSmgsRecieverInfo.class)
                        .setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsDestinationPlaceInfo getNeSmgsDestinationPlaceInfo(Long invoiceUn) {
        List<NeSmgsDestinationPlaceInfo> list =
                        em.createQuery("select a from NeSmgsDestinationPlaceInfo a where a.invoiceUn = ?1",
                                        NeSmgsDestinationPlaceInfo.class).setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsShipList getNeSmgsShipList(Long invoiceUn) {
        List<NeSmgsShipList> list =
                        em.createQuery("select a from NeSmgsShipList a where a.invUn = ?1", NeSmgsShipList.class)
                                        .setParameter(1, invoiceUn).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public NeVessel getVessel(Long un) {
        return em.find(NeVessel.class, un);
    }

    public List<NeSmgsTnVed> getTnVedList(Long invoiceUn) {
        return em.createQuery("select a from NeSmgsTnVed a where a.invoiceUn = ?1", NeSmgsTnVed.class)
                        .setParameter(1, invoiceUn).getResultList();
    }

    public NeVagonGroup getVagonGroup(Long id) {
        List<NeVagonGroup> list = em.createNativeQuery(
                        "SELECT a.* FROM KTZ.NE_VAGON_GROUP a join ktz.NE_VAGON_LISTS b on a.VAG_GROUP_UN = b.VAG_GROUP_UN where b.INVC_UN = ?1 fetch first row only OPTIMIZE FOR 1 ROWS ",
                        NeVagonGroup.class).setParameter(1, id).getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public Country getCountry(String countryCode) {
        List<Country> list = em.createQuery("select a from Country a where a.countryNo = ?1")
                        .setParameter(1, countryCode).getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    public Sta getStation(String stationCode) {
        List<Sta> list = em.createQuery("select a from Sta a where a.staNo = ?1 and a.stEnd > CURRENT_TIMESTAMP")
                        .setParameter(1, stationCode).getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Management getManagement(Long managUn) {
        List<Management> list = em.createQuery("select a from Management a where a.managUn = ?1 ")
                        .setParameter(1, managUn).getResultList();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Country getCountry(Long countryUn) {
        List<Country> list = em.createQuery("select a from Country a where a.couUn = ?1").setParameter(1, countryUn)
                        .getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<NeSmgsTnVedDocuments> getSmgsTnVedDocuments(Long tnVedId) {
        return em.createQuery(
                        "select a from NeSmgsTnVedDocuments a where a.smgsTnVedUn = ?1 order by a.smgsTnVedDocumentsUn asc")
                        .setParameter(1, tnVedId).getResultList();
    }

    @Transactional
    public NeSmgsTnVedDocuments saveSmgsTnVedDocuments(NeSmgsTnVedDocuments saveDoc) {
        saveDoc = em.merge(saveDoc);
        em.flush();
        return saveDoc;
    }

    @Transactional
    public void deleteTnVedDocuments(Long id) {
        if (id != null) {
            NeSmgsTnVedDocuments doc = getSmgsTnVedDocumentsById(id);
            if (doc != null) {
                em.remove(doc);
                em.flush();
            }
        }
    }

    @Transactional
    public void updateTnVedDocuments(Long id, NeSmgsTnVedDocuments updateDoc) {
        NeSmgsTnVedDocuments doc = getSmgsTnVedDocumentsById(id);
        if (doc != null) {
            doc.setDocumentDate(updateDoc.getDocumentDate());
            doc.setDocumentName(updateDoc.getDocumentName());
            doc.setDocumentNumber(updateDoc.getDocumentNumber());
            doc.setDocumentCode(updateDoc.getDocumentCode());
            doc.setCopyCount(updateDoc.getCopyCount());
            doc.setListCount(updateDoc.getListCount());
            em.merge(doc);
            em.flush();
        }

    }

    @Transactional
    public void updateNeInvoicePrevInfo(Long id, NeInvoicePrevInfo updateDoc) {
        if (updateDoc != null) {
            em.merge(updateDoc);
            em.flush();
        }

    }

    public NeSmgsTnVedDocuments getSmgsTnVedDocumentsById(Long id) {
        List<NeSmgsTnVedDocuments> list =
                        em.createQuery("select a from NeSmgsTnVedDocuments a where a.smgsTnVedDocumentsUn = ?1")
                                        .setParameter(1, id).getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public NeSmgsExpeditorInfo getExpeditor(Long id) {
        List<NeSmgsExpeditorInfo> list = em.createQuery("select a from NeSmgsExpeditorInfo a where a.invUn = ?1")
                        .setParameter(1, id).getResultList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<NeUnitType> getUnitType() {
        return em.createQuery("select a from NeUnitType a ")
                .getResultList();
    }

    public Country getCountryBycode(String countryCode) {
        return (Country) em.createNativeQuery("select * from NSI.COUNTRY s where s.COUNTRY_NO = ?1", Country.class)
                        .setParameter(1, countryCode).getSingleResult();
    }

    public List<NeContainerLists> getContinerList(Long id) {
        List<NeContainerLists> result = em.createQuery("select a from NeContainerLists a where a.invoiceUn = ?1")
                        .setParameter(1, id).getResultList();
        return result;
    }

    public void bigUpdateTnVedDocuments(Long id, NeSmgsTnVedDocuments updateDoc) {
        NeSmgsTnVedDocuments doc = getSmgsTnVedDocumentsById(id);
        if (doc != null) {
            doc.setDocumentDate(updateDoc.getDocumentDate());
            doc.setDocumentName(updateDoc.getDocumentName());
            doc.setDocumentNumber(updateDoc.getDocumentNumber());
            doc.setDocumentCode(updateDoc.getDocumentCode());
            em.merge(doc);
            em.flush();
        }
    }

    public String getTnVedDocumentsIds(Long invoiceUn) {
        String res = null;
        String sql = "select 1 as num, REPLACE(REPLACE(REPLACE(VARCHAR( XML2CLOB( XMLAGG( XMLELEMENT( NAME a ,D.SMGS_TN_VED_UN) ) ) ,2048 ) ,'</A><A>',',') ,'<A>','') ,'</A>','') AS ids "
                        + "from KTZ.NE_SMGS_TN_VED D WHERE D.INVOICE_UN IN(" + invoiceUn + ") " + "GROUP BY 1";
        try {
            Query query = em.createNativeQuery(sql);
            List<Object[]> list = query.getResultList();
            for (Object[] object : list) {
                res = (String) object[1];
            }
        } catch (NoResultException nre) {
            nre.getLocalizedMessage();
            return null;
        } catch (Exception e) {
            e.getLocalizedMessage();
            return null;
        }
        return res;
    }

    public NePersonCategoryType getPersonCategoryType(Long categoryType) {
        if (categoryType != null) {
            return em.find(NePersonCategoryType.class, categoryType);
        } else {
            return null;
        }
    }

    public Country getCountryByManagNo(int managNo) {
        String sql = "select * from nsi.COUNTRY where COU_UN in (select COU_UN from NSI.MANAGEMENT where MANAG_NO = ?1 and CURRENT_TIMESTAMP between MANAG_BGN and MANAG_END )";
        List<Country> list = em.createNativeQuery(sql, Country.class).setParameter(1, managNo).getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Country getCountryByManagUn(Long managUn) {
        if (managUn == null) {
            return null;
        }
        String sql = "select * from nsi.COUNTRY where COU_UN in (select COU_UN from NSI.MANAGEMENT where MANAG_UN = ?1 and MANAG_END > CURRENT_TIMESTAMP FETCH FIRST 1 ROWS ONLY)";
        List<Country> list = em.createNativeQuery(sql, Country.class).setParameter(1, managUn).getResultList();
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void updatePrevInfoStatus(NeInvoicePrevInfo invoicePrevInfo) {
        em.merge(invoicePrevInfo);
    }

}
