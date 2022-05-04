package kz.ne.railways.tezcustoms.service.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FormData {

    private String invoiceId;
    private Long userUn; // Пользователь изменивший запись.
    private String user; // Пользователь изменивший запись для историчности

    private String trainIndex; // Индекс поезда
    private String invoiceNumber; // Номер накладной NeInvoice
    private String conteinerRef; // is container
    private int docType;
    private String invDateTime;
    private String gngCode; // Вид груза
    private String gngName;


    /** NE_INVOICE_PREV_INFO */
    private String AppoPrInf; // Назн. пред.информации
    private Integer featureType;
    private Timestamp arrivalDate; // Дата прибития
    private Timestamp arrivalTime;
    private Long customOrgUn; // Таможенный орган спр.
    private String customCode;// Таможенный орган код
    private String customName;// Таможенный орган наим
    private String arriveStation; // Станция прибития
    private String arriveStationName;
    private String responseMessage; // RESPONSE_TEXT

    private String startStation; // Станция отправления NeInvoice
    private String startStationName;
    private String senderSolrUUID;
    private String recieverSolrUUID;
    private String declarantSolrUUID;

    private String senderName; // NeSmgsSenderInfo.SenderName
    private String senderShortNam;
    private String senderCountry; // NeSmgsSenderInfo.SenderCountryCode
    private String senderCountryName; // NeSmgsSenderInfo.SenderCountryCode
    private String senderIndex = "000000"; // NeSmgsSenderInfo.setSenderPostIndex
    private String senderPoint; // NeSmgsSenderInfo.setSenderSity
    private String senderOblast;
    private String senderStreetNh;// NeSmgsSenderInfo.SenderStreet
    private String senderBIN;
    private String senderIIN;
    private Long senderKatFace;// Categoriya lica
    private String senderKatFaceCode;
    private String senderKatFaceName;
    private String senderKATO;
    private String senderKATOName;
    private String senderITNreserv; // ИТН Резерв
    private String senderKpp; // КПП

    private String destStation; // Станция назначения NeInvoice
    private String destStationName;
    private String recieverName; // NeSmgsRecieverInfo.RecieverName
    private String recieverShortNam;
    private String recieverCountry; // NeSmgsRecieverInfo.RecieverCountryCode
    private String recieverCountryName; // NeSmgsRecieverInfo.RecieverCountryCode
    private String recieverIndex = "000000"; // NeSmgsRecieverInfo.RecieverPostIndex
    private String recieverPoint; // NeSmgsRecieverInfo.RecieverSity
    private String recieverOblast;
    private String recieverStreetNh;// NeSmgsRecieverInfo.RecieverStreet
    private String recieverBIN;
    private String recieverIIN;
    private Long recieverKatFace;
    private String recieverKatFaceCode;
    private String recieverKatFaceName;
    private String recieverKATO;
    private String recieverKATOName;
    private String recieverITNreserv;
    private String recieverKPP;
    /** NE_SMGS_DESTINATION_INFO */ // сведения о месте назначения
    private String destPlace;
    private String destPlaceStation;
    private String destPlaceStationName;
    private String destPlaceCountryCode;
    private String destPlaceCountryName;
    private String destPlaceIndex = "000000"; // индекс
    private String destPlacePoint;
    private String destPlaceOblast;
    private String destPlaceStreet;
    private Long destPlaceCustomOrgUn; // Таможенный орган спр.
    private String destPlaceCustomCode;// Таможенный орган код
    private String destPlaceCustomName;// Таможенный орган наим

    // декларант
    private String declarantName; // Наименование/ФИО
    private String declarantShortName; // Краткое наименование
    private String declarantCountry; // Страна code
    private String declarantCountryName; // Страна name
    private String declarantIndex = "000000"; // Почтовый индекс
    private String declarantCity; // Населеннывй пункт
    private String declarantRegion; // Область (регион, штат и т.п.)
    private String declarantAddress; // улица, номер дома, номер офиса
    private String declarantKZBin; // БИН для РК
    private String declarantKZIin; // ИИН для РК
    private String declarantKZPersonsCategory; // Категория лица для РК
    private String declarantKZKATO; // КАТО для РК
    private String declarantKZITN; // ИТН резерва для РК
    private String declarantRUOGRN; // ОГРН/ОГРНИП для РФ
    private String declarantRUKPP; // КПП для РФ
    private String declarantRUINN; // ИНН для РФ
    private String declarantBYUNP; // УНП для Беларуси
    private String declarantBYIN; // Идентификационный номер физ.лица для Беларуси
    private String declarantAMUNN; // УНН для Армении
    private String declarantAMNZOU; // НЗОУ для Армении
    private String declarantKGINN; // ИНН для Кыргызстана
    private String declarantKGOKPO; // ОКПО для Кыргызстана

    // экспедитор
    private String expeditorName; // Наименование/ФИО
    private String expeditorShortName; // Краткое наименование
    private String expeditorCountry; // Страна code
    private String expeditorCountryName; // Страна name
    private String expeditorIndex = "000000"; // Почтовый индекс
    private String expeditorCity; // Населеннывй пункт
    private String expeditorRegion; // Область (регион, штат и т.п.)
    private String expeditorAddress; // улица, номер дома, номер офиса
    private String expeditorKZBin; // БИН для РК
    private String expeditorKZIin; // ИИН для РК
    private String expeditorKZPersonsCategory; // Категория лица для РК
    private String expeditorKZKATO; // КАТО для РК
    private String expeditorKZITN; // ИТН резерва для РК
    private String expeditorRUOGRN; // ОГРН/ОГРНИП для РФ
    private String expeditorRUKPP; // КПП для РФ
    private String expeditorRUINN; // ИНН для РФ
    private String expeditorBYUNP; // УНП для Беларуси
    private String expeditorBYIN; // Идентификационный номер физ.лица для Беларуси
    private String expeditorAMUNN; // УНН для Армении
    private String expeditorAMNZOU; // НЗОУ для Армении
    private String expeditorKGINN; // ИНН для Кыргызстана
    private String expeditorKGOKPO; // ОКПО для Кыргызстана

    private String startStaCountry;
    private String destStationCountry;

    /* NE_CONTAINER_LISTS */
    /*
     * private String NumContainer;//номер контейнера private Integer containerFilled;//признак
     * заполненности контейнера
     */
    private Long vagonAccessory;// принадлежность контейнера, если отправка контейнерная, иначе принадлежность
                                // вагона(KTZ.NE_VAGON_LISTS.OWNER_RAILWAYS)
    /*
     * private String containerMark;//код контейнера private String containerCode;//Типоразмер
     * контейнера
     */
    private DicDao containerCountry;
    private DicDao containerCode;

    private Long vesselUn;// судно для актау паром и курык паром эксп
    private DicDao vessel;

    private List<ContainerData> containers = new ArrayList<ContainerData>();
    private String createDate;// дата создания

    private List<VagonItem> vagonList;

    private ContainerDatas containerDatas;

    public void addContainer(ContainerData container) {
        containers.add(container);
    }
    /*
     * public String getContainerCode() { return containerCode; }
     * 
     * public void setContainerCode(String containerCode) { this.containerCode = containerCode; }
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<VagonItem> getVagonList() {
        return vagonList;
    }

    public void setVagonList(List<VagonItem> vagonList) {
        this.vagonList = vagonList;
    }

    public ContainerDatas getContainerDatas() {
        return containerDatas;
    }

    public void setContainerDatas(ContainerDatas containerDatas) {
        this.containerDatas = containerDatas;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGngCode() {
        return gngCode;
    }

    public void setGngCode(String gngCode) {
        this.gngCode = gngCode;
    }

    public void setTrainIndex(String trainIndex) {
        this.trainIndex = trainIndex;
    }

    public String getTrainIndex() {
        return trainIndex;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public String getInvDateTime() {
        return invDateTime;
    }

    public void setInvDateTime(String invDateTime) {
        this.invDateTime = invDateTime;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setConteinerRef(String conteinerRef) {
        this.conteinerRef = conteinerRef;
    }

    public String getConteinerRef() {
        return conteinerRef;
    }

    public void setAppoPrInf(String appoPrInf) {
        AppoPrInf = appoPrInf;
    }

    public String getAppoPrInf() {
        return AppoPrInf;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }


    public void setSenderShortNam(String senderShortNam) {
        this.senderShortNam = senderShortNam;
    }

    public String getSenderShortNam() {
        return senderShortNam;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderIndex(String senderIndex) {
        this.senderIndex = senderIndex;
    }

    public String getSenderIndex() {
        return senderIndex;
    }

    public void setSenderOblast(String senderOblast) {
        this.senderOblast = senderOblast;
    }

    public String getSenderOblast() {
        return senderOblast;
    }

    public void setSenderStreetNh(String senderStreetNh) {
        this.senderStreetNh = senderStreetNh;
    }

    public String getSenderStreetNh() {
        return senderStreetNh;
    }

    public void setSenderBIN(String senderBIN) {
        this.senderBIN = senderBIN;
    }

    public String getSenderBIN() {
        return senderBIN;
    }

    public void setSenderIIN(String senderIIN) {
        this.senderIIN = senderIIN;
    }

    public String getSenderIIN() {
        return senderIIN;
    }

    public void setSenderKatFace(Long senderKatFace) {
        this.senderKatFace = senderKatFace;
    }

    public Long getSenderKatFace() {
        return senderKatFace;
    }

    public void setSenderKATO(String senderKATO) {
        this.senderKATO = senderKATO;
    }

    public String getSenderKATO() {
        return senderKATO;
    }

    public void setSenderITNreserv(String senderITNreserv) {
        this.senderITNreserv = senderITNreserv;
    }

    public String getSenderITNreserv() {
        return senderITNreserv;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public Long getCustomOrgUn() {
        return customOrgUn;
    }

    public void setCustomOrgUn(Long customOrgUn) {
        this.customOrgUn = customOrgUn;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public void setArriveStation(String arriveStation) {
        this.arriveStation = arriveStation;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPoint() {
        return senderPoint;
    }

    public void setSenderPoint(String senderPoint) {
        this.senderPoint = senderPoint;
    }

    public String getSenderKpp() {
        return senderKpp;
    }

    public void setSenderKpp(String senderKpp) {
        this.senderKpp = senderKpp;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverShortNam() {
        return recieverShortNam;
    }

    public void setRecieverShortNam(String recieverShortNam) {
        this.recieverShortNam = recieverShortNam;
    }

    public String getRecieverCountry() {
        return recieverCountry;
    }

    public void setRecieverCountry(String recieverCountry) {
        this.recieverCountry = recieverCountry;
    }

    public String getRecieverIndex() {
        return recieverIndex;
    }

    public void setRecieverIndex(String recieverIndex) {
        this.recieverIndex = recieverIndex;
    }

    public String getRecieverPoint() {
        return recieverPoint;
    }

    public void setRecieverPoint(String recieverPoint) {
        this.recieverPoint = recieverPoint;
    }

    public String getRecieverOblast() {
        return recieverOblast;
    }

    public void setRecieverOblast(String recieverOblast) {
        this.recieverOblast = recieverOblast;
    }

    public String getRecieverStreetNh() {
        return recieverStreetNh;
    }

    public void setRecieverStreetNh(String recieverStreetNh) {
        this.recieverStreetNh = recieverStreetNh;
    }

    public String getRecieverBIN() {
        return recieverBIN;
    }

    public void setRecieverBIN(String recieverBIN) {
        this.recieverBIN = recieverBIN;
    }

    public String getRecieverIIN() {
        return recieverIIN;
    }

    public void setRecieverIIN(String recieverIIN) {
        this.recieverIIN = recieverIIN;
    }

    public Long getRecieverKatFace() {
        return recieverKatFace;
    }

    public void setRecieverKatFace(Long recieverKatFace) {
        this.recieverKatFace = recieverKatFace;
    }

    public String getRecieverKATO() {
        return recieverKATO;
    }

    public void setRecieverKATO(String recieverKATO) {
        this.recieverKATO = recieverKATO;
    }

    public String getRecieverITNreserv() {
        return recieverITNreserv;
    }

    public void setRecieverITNreserv(String recieverITNreserv) {
        this.recieverITNreserv = recieverITNreserv;
    }

    public String getRecieverKPP() {
        return recieverKPP;
    }

    public void setRecieverKPP(String recieverKPP) {
        this.recieverKPP = recieverKPP;
    }

    public String getDestPlace() {
        return destPlace;
    }

    public void setDestPlace(String destPlace) {
        this.destPlace = destPlace;
    }

    public String getDestPlaceStation() {
        return destPlaceStation;
    }

    public void setDestPlaceStation(String destPlaceStation) {
        this.destPlaceStation = destPlaceStation;
    }

    public String getDestPlaceCountryCode() {
        return destPlaceCountryCode;
    }

    public void setDestPlaceCountryCode(String destPlaceCountryCode) {
        this.destPlaceCountryCode = destPlaceCountryCode;
    }

    public String getDestPlaceIndex() {
        return destPlaceIndex;
    }

    public void setDestPlaceIndex(String destPlaceIndex) {
        this.destPlaceIndex = destPlaceIndex;
    }

    public String getDestPlacePoint() {
        return destPlacePoint;
    }

    public void setDestPlacePoint(String destPlacePoint) {
        this.destPlacePoint = destPlacePoint;
    }

    public String getDestPlaceOblast() {
        return destPlaceOblast;
    }

    public void setDestPlaceOblast(String destPlaceOblast) {
        this.destPlaceOblast = destPlaceOblast;
    }

    public String getDestPlaceStreet() {
        return destPlaceStreet;
    }

    public void setDestPlaceStreet(String destPlaceStreet) {
        this.destPlaceStreet = destPlaceStreet;
    }

    public Long getDestPlaceCustomOrgUn() {
        return destPlaceCustomOrgUn;
    }

    public void setDestPlaceCustomOrgUn(Long destPlaceCustomOrgUn) {
        this.destPlaceCustomOrgUn = destPlaceCustomOrgUn;
    }

    public String getDestPlaceCustomCode() {
        return destPlaceCustomCode;
    }

    public void setDestPlaceCustomCode(String destPlaceCustomCode) {
        this.destPlaceCustomCode = destPlaceCustomCode;
    }

    public String getDestPlaceCustomName() {
        return destPlaceCustomName;
    }

    public void setDestPlaceCustomName(String destPlaceCustomName) {
        this.destPlaceCustomName = destPlaceCustomName;
    }

    public String getSenderSolrUUID() {
        return senderSolrUUID;
    }

    public void setSenderSolrUUID(String senderSolrUUID) {
        this.senderSolrUUID = senderSolrUUID;
    }

    public String getRecieverSolrUUID() {
        return recieverSolrUUID;
    }

    public void setRecieverSolrUUID(String recieverSolrUUID) {
        this.recieverSolrUUID = recieverSolrUUID;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getSenderKatFaceName() {
        return senderKatFaceName;
    }

    public void setSenderKatFaceName(String senderKatFaceName) {
        this.senderKatFaceName = senderKatFaceName;
    }

    public String getSenderKATOName() {
        return senderKATOName;
    }

    public void setSenderKATOName(String senderKATOName) {
        this.senderKATOName = senderKATOName;
    }

    public String getRecieverCountryName() {
        return recieverCountryName;
    }

    public void setRecieverCountryName(String recieverCountryName) {
        this.recieverCountryName = recieverCountryName;
    }

    public String getRecieverKatFaceName() {
        return recieverKatFaceName;
    }

    public void setRecieverKatFaceName(String recieverKatFaceName) {
        this.recieverKatFaceName = recieverKatFaceName;
    }

    public String getRecieverKATOName() {
        return recieverKATOName;
    }

    public void setRecieverKATOName(String recieverKATOName) {
        this.recieverKATOName = recieverKATOName;
    }

    public void setSenderKatFaceCode(String senderKatFaceCode) {
        this.senderKatFaceCode = senderKatFaceCode;
    }

    public String getSenderKatFaceCode() {
        return senderKatFaceCode;
    }

    public void setRecieverKatFaceCode(String recieverKatFaceCode) {
        this.recieverKatFaceCode = recieverKatFaceCode;
    }

    public String getRecieverKatFaceCode() {
        return recieverKatFaceCode;
    }

    public void setDestPlaceStationName(String destPlaceStationName) {
        this.destPlaceStationName = destPlaceStationName;
    }

    public String getDestPlaceStationName() {
        return destPlaceStationName;
    }

    public void setDestStationName(String destStationName) {
        this.destStationName = destStationName;
    }

    public String getDestStationName() {
        return destStationName;
    }

    public void setArriveStationName(String arriveStationName) {
        this.arriveStationName = arriveStationName;
    }

    public String getArriveStationName() {
        return arriveStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setGngName(String gngName) {
        this.gngName = gngName;
    }

    public String getGngName() {
        return gngName;
    }

    public Long getUserUn() {
        return userUn;
    }

    public void setUserUn(Long userUn) {
        this.userUn = userUn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDestPlaceCountryName() {
        return destPlaceCountryName;
    }

    public void setDestPlaceCountryName(String destPlaceCountryName) {
        this.destPlaceCountryName = destPlaceCountryName;
    }

    public String getDeclarantName() {
        return declarantName;
    }

    public void setDeclarantName(String declarantName) {
        this.declarantName = declarantName;
    }

    public String getDeclarantShortName() {
        return declarantShortName;
    }

    public void setDeclarantShortName(String declarantShortName) {
        this.declarantShortName = declarantShortName;
    }

    public String getDeclarantCountry() {
        return declarantCountry;
    }

    public void setDeclarantCountry(String declarantCountry) {
        this.declarantCountry = declarantCountry;
    }

    public String getDeclarantIndex() {
        return declarantIndex;
    }

    public void setDeclarantIndex(String declarantIndex) {
        this.declarantIndex = declarantIndex;
    }

    public String getDeclarantCity() {
        return declarantCity;
    }

    public void setDeclarantCity(String declarantCity) {
        this.declarantCity = declarantCity;
    }

    public String getDeclarantRegion() {
        return declarantRegion;
    }

    public void setDeclarantRegion(String declarantRegion) {
        this.declarantRegion = declarantRegion;
    }

    public String getDeclarantAddress() {
        return declarantAddress;
    }

    public void setDeclarantAddress(String declarantAddress) {
        this.declarantAddress = declarantAddress;
    }

    public String getDeclarantKZBin() {
        return declarantKZBin;
    }

    public void setDeclarantKZBin(String declarantKZBin) {
        this.declarantKZBin = declarantKZBin;
    }

    public String getDeclarantKZIin() {
        return declarantKZIin;
    }

    public void setDeclarantKZIin(String declarantKZIin) {
        this.declarantKZIin = declarantKZIin;
    }

    public String getDeclarantKZPersonsCategory() {
        return declarantKZPersonsCategory;
    }

    public void setDeclarantKZPersonsCategory(String declarantKZPersonsCategory) {
        this.declarantKZPersonsCategory = declarantKZPersonsCategory;
    }

    public String getDeclarantKZKATO() {
        return declarantKZKATO;
    }

    public void setDeclarantKZKATO(String declarantKZKATO) {
        this.declarantKZKATO = declarantKZKATO;
    }

    public String getDeclarantKZITN() {
        return declarantKZITN;
    }

    public void setDeclarantKZITN(String declarantKZITN) {
        this.declarantKZITN = declarantKZITN;
    }

    public String getDeclarantRUOGRN() {
        return declarantRUOGRN;
    }

    public void setDeclarantRUOGRN(String declarantRUOGRN) {
        this.declarantRUOGRN = declarantRUOGRN;
    }

    public String getDeclarantRUKPP() {
        return declarantRUKPP;
    }

    public void setDeclarantRUKPP(String declarantRUKPP) {
        this.declarantRUKPP = declarantRUKPP;
    }

    public String getDeclarantRUINN() {
        return declarantRUINN;
    }

    public void setDeclarantRUINN(String declarantRUINN) {
        this.declarantRUINN = declarantRUINN;
    }

    public String getDeclarantBYUNP() {
        return declarantBYUNP;
    }

    public void setDeclarantBYUNP(String declarantBYUNP) {
        this.declarantBYUNP = declarantBYUNP;
    }

    public String getDeclarantBYIN() {
        return declarantBYIN;
    }

    public void setDeclarantBYIN(String declarantBYIN) {
        this.declarantBYIN = declarantBYIN;
    }

    public String getDeclarantAMUNN() {
        return declarantAMUNN;
    }

    public void setDeclarantAMUNN(String declarantAMUNN) {
        this.declarantAMUNN = declarantAMUNN;
    }

    public String getDeclarantAMNZOU() {
        return declarantAMNZOU;
    }

    public void setDeclarantAMNZOU(String declarantAMNZOU) {
        this.declarantAMNZOU = declarantAMNZOU;
    }

    public String getDeclarantKGINN() {
        return declarantKGINN;
    }

    public void setDeclarantKGINN(String declarantKGINN) {
        this.declarantKGINN = declarantKGINN;
    }

    public String getDeclarantKGOKPO() {
        return declarantKGOKPO;
    }

    public void setDeclarantKGOKPO(String declarantKGOKPO) {
        this.declarantKGOKPO = declarantKGOKPO;
    }

    public String getDeclarantSolrUUID() {
        return declarantSolrUUID;
    }

    public void setDeclarantSolrUUID(String declarantSolrUUID) {
        this.declarantSolrUUID = declarantSolrUUID;
    }

    public String getDeclarantCountryName() {
        return declarantCountryName;
    }

    public void setDeclarantCountryName(String declarantCountryName) {
        this.declarantCountryName = declarantCountryName;
    }

    public void setStartStaCountry(String startStaCountry) {
        this.startStaCountry = startStaCountry;
    }

    public String getStartStaCountry() {
        return startStaCountry;
    }

    public void setDestStationCountry(String destStationCountry) {
        this.destStationCountry = destStationCountry;
    }

    public String getDestStationCountry() {
        return destStationCountry;
    }

    public void setFeatureType(Integer featureType) {
        this.featureType = featureType;
    }

    public Integer getFeatureType() {
        return featureType;
    }

    /*
     * public String getNumContainer() { return NumContainer; }
     * 
     * public void setNumContainer(String numContainer) { NumContainer = numContainer; }
     * 
     * public Integer getContainerFilled() { return containerFilled; }
     * 
     * public void setContainerFilled(Integer containerFilled) { this.containerFilled = containerFilled;
     * }
     */
    public Long getVagonAccessory() {
        return vagonAccessory;
    }

    public void setVagonAccessory(Long vagonAccessory) {
        this.vagonAccessory = vagonAccessory;
    }

    /*
     * public String getContainerMark() { return containerMark; }
     * 
     * public void setContainerMark(String containerMark) { this.containerMark = containerMark; }
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getExpeditorName() {
        return expeditorName;
    }

    public void setExpeditorName(String expeditorName) {
        this.expeditorName = expeditorName;
    }

    public String getExpeditorShortName() {
        return expeditorShortName;
    }

    public void setExpeditorShortName(String expeditorShortName) {
        this.expeditorShortName = expeditorShortName;
    }

    public String getExpeditorCountry() {
        return expeditorCountry;
    }

    public void setExpeditorCountry(String expeditorCountry) {
        this.expeditorCountry = expeditorCountry;
    }

    public String getExpeditorCountryName() {
        return expeditorCountryName;
    }

    public void setExpeditorCountryName(String expeditorCountryName) {
        this.expeditorCountryName = expeditorCountryName;
    }

    public String getExpeditorIndex() {
        return expeditorIndex;
    }

    public void setExpeditorIndex(String expeditorIndex) {
        this.expeditorIndex = expeditorIndex;
    }

    public String getExpeditorCity() {
        return expeditorCity;
    }

    public void setExpeditorCity(String expeditorCity) {
        this.expeditorCity = expeditorCity;
    }

    public String getExpeditorRegion() {
        return expeditorRegion;
    }

    public void setExpeditorRegion(String expeditorRegion) {
        this.expeditorRegion = expeditorRegion;
    }

    public String getExpeditorAddress() {
        return expeditorAddress;
    }

    public void setExpeditorAddress(String expeditorAddress) {
        this.expeditorAddress = expeditorAddress;
    }

    public String getExpeditorKZBin() {
        return expeditorKZBin;
    }

    public void setExpeditorKZBin(String expeditorKZBin) {
        this.expeditorKZBin = expeditorKZBin;
    }

    public String getExpeditorKZIin() {
        return expeditorKZIin;
    }

    public void setExpeditorKZIin(String expeditorKZIin) {
        this.expeditorKZIin = expeditorKZIin;
    }

    public String getExpeditorKZPersonsCategory() {
        return expeditorKZPersonsCategory;
    }

    public void setExpeditorKZPersonsCategory(String expeditorKZPersonsCategory) {
        this.expeditorKZPersonsCategory = expeditorKZPersonsCategory;
    }

    public String getExpeditorKZKATO() {
        return expeditorKZKATO;
    }

    public void setExpeditorKZKATO(String expeditorKZKATO) {
        this.expeditorKZKATO = expeditorKZKATO;
    }

    public String getExpeditorKZITN() {
        return expeditorKZITN;
    }

    public void setExpeditorKZITN(String expeditorKZITN) {
        this.expeditorKZITN = expeditorKZITN;
    }

    public String getExpeditorRUOGRN() {
        return expeditorRUOGRN;
    }

    public void setExpeditorRUOGRN(String expeditorRUOGRN) {
        this.expeditorRUOGRN = expeditorRUOGRN;
    }

    public String getExpeditorRUKPP() {
        return expeditorRUKPP;
    }

    public void setExpeditorRUKPP(String expeditorRUKPP) {
        this.expeditorRUKPP = expeditorRUKPP;
    }

    public String getExpeditorRUINN() {
        return expeditorRUINN;
    }

    public void setExpeditorRUINN(String expeditorRUINN) {
        this.expeditorRUINN = expeditorRUINN;
    }

    public String getExpeditorBYUNP() {
        return expeditorBYUNP;
    }

    public void setExpeditorBYUNP(String expeditorBYUNP) {
        this.expeditorBYUNP = expeditorBYUNP;
    }

    public String getExpeditorBYIN() {
        return expeditorBYIN;
    }

    public void setExpeditorBYIN(String expeditorBYIN) {
        this.expeditorBYIN = expeditorBYIN;
    }

    public String getExpeditorAMUNN() {
        return expeditorAMUNN;
    }

    public void setExpeditorAMUNN(String expeditorAMUNN) {
        this.expeditorAMUNN = expeditorAMUNN;
    }

    public String getExpeditorAMNZOU() {
        return expeditorAMNZOU;
    }

    public void setExpeditorAMNZOU(String expeditorAMNZOU) {
        this.expeditorAMNZOU = expeditorAMNZOU;
    }

    public String getExpeditorKGINN() {
        return expeditorKGINN;
    }

    public void setExpeditorKGINN(String expeditorKGINN) {
        this.expeditorKGINN = expeditorKGINN;
    }

    public String getExpeditorKGOKPO() {
        return expeditorKGOKPO;
    }

    public void setExpeditorKGOKPO(String expeditorKGOKPO) {
        this.expeditorKGOKPO = expeditorKGOKPO;
    }

    public DicDao getContainerCountry() {
        return containerCountry;
    }

    public void setContainerCountry(DicDao containerCountry) {
        this.containerCountry = containerCountry;
    }

    public DicDao getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(DicDao containerCode) {
        this.containerCode = containerCode;
    }

    public Long getVesselUn() {
        return vesselUn;
    }

    public void setVesselUn(Long vesselUn) {
        this.vesselUn = vesselUn;
    }

    public DicDao getVessel() {
        return vessel;
    }

    public void setVessel(DicDao vessel) {
        this.vessel = vessel;
    }

}
