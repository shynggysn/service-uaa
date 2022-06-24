package kz.ne.railways.tezcustoms.service.entity.asudkr;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ne_invoice_details", schema = "ktz")
public class NeInvoiceDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specification_number")
    private String specificationNumber;  // 4 - Отгрузочные спецификации

    @Column(name = "total_sheet_number")
    private String totalSheetNumber; // 3 - общее количество листов

    @Column(name = "preceding_document_number")
    private String precedingDocumentNumber; // 40 - Общая декларация/Предшествующий документ

    @Column(name = "transport_means")
    private String transportMeans; // 21 - Идентификация и страна регистрации активного транспортного средства на границе

    @Column(name = "reload_place_and_country")
    private String reloadPlaceAndCountry; // 55 - Место и страна

    @Column(name = "container_indicator")
    private int containerIndicator; // 55 - Контейнер 0/1

    @Column(name = "container_number")
    private String reloadContainerNumber; // 55 - Номер нового контейнера

    @Column(name = "transport_means_nationality_code")
    private String transportMeansNationalityCode; // 55 - Идентификация и страна регистрации нового транспортного средства

    @Column(name = "guarantee_measure_code")
    private String guaranteeMeasureCode; // 52 - код меры обеспечения соблюдения таможенного транзита в соответствии с классификатором мер обеспечения соблюдения таможенного транзита

    @Column(name = "guarantee_doc_number")
    private String guaranteeDocNumber; // 52 - Номер документа

    @OneToOne(targetEntity = NeInvoice.class, fetch = FetchType.EAGER)
    @JoinColumn(name="invoice_un", nullable=false, referencedColumnName = "invc_un")
    private NeInvoice neInvoice;

}
