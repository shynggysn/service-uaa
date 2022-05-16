package kz.ne.railways.tezcustoms.service.model;

import lombok.Data;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
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

    private InvoiceData invoiceData;

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
}
