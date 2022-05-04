package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "NE_SMGS_TNVED_DOCUMENTS", schema = "KTZ")
public class NeSmgsTnVedDocuments implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_TNVED_DOCUMENTS#UN")
    private Long smgsTnVedDocumentsUn;

    @Column(name = "SMGS_TNVED#UN")
    private long smgsTnVedUn;

    @Column(name = "DOCUMENT_CODE")
    private String documentCode;

    @Column(name = "DOCUMENT_NAME")
    private String documentName;

    @Column(name = "DOCUMENT_NUMBER")
    private String documentNumber;

    @Column(name = "DOCUMENT_DATE")
    private Date documentDate;

    @Column(name = "DOCUMENT_DATE_TO")
    private Date documentDateTo;

    @Column(name = "COPY_COUNT")
    private int copyCount;

    @Column(name = "LIST_COUNT")
    private int listCount;

    public Long getSmgsTnVedDocumentsUn() {
        return smgsTnVedDocumentsUn;
    }

    public void setSmgsTnVedDocumentsUn(Long smgsTnVedDocumentsUn) {
        this.smgsTnVedDocumentsUn = smgsTnVedDocumentsUn;
    }

    public long getSmgsTnVedUn() {
        return smgsTnVedUn;
    }

    public void setSmgsTnVedUn(long smgsTnVedUn) {
        this.smgsTnVedUn = smgsTnVedUn;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public void setDocumentDateTo(Date documentDateTo) {
        this.documentDateTo = documentDateTo;
    }

    public Date getDocumentDateTo() {
        return documentDateTo;
    }

    public int getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(int copyCount) {
        this.copyCount = copyCount;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }
}
