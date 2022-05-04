package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "NE_CONTAINER_LISTS", schema = "KTZ")
public class NeContainerLists implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTAINER_LISTS#UN")
    private Long containerListsUn;

    @Column(name = "CONTAINER_NO")
    private String containerNo;

    @Column(name = "CONTAINER_MARK")
    private String containerMark;

    @Column(name = "CONTAINER_TYPE#UN")
    private Long containerTypeUn;

    @Column(name = "NPP")
    private Integer npp;

    @Column(name = "INVOICE#UN")
    private Long invoiceUn;

    @Column(name = "VAGON_LIST#UN")
    private Long vagonListUn;

    @Column(name = "VAG_GROUP#UN")
    private Long vagGroupUn;

    @Column(name = "MASS_FACT")
    private BigDecimal massFact;

    @Column(name = "MASS_TARA")
    private BigDecimal massTara;

    @Column(name = "GP")
    private BigDecimal gp;

    @Column(name = "MANAG#UN")
    private Long managUn;

    @Column(name = "PREV_ETSNG_CODE")
    private String prevEtsngCode;

    @Column(name = "C_OWNER_CODE")
    private Integer cOwnerCode;

    @Column(name = "C_OWNER_TYPE")
    private Short cOwnerType;

    @Column(name = "C_OWNER_NAME")
    private String cOwnerName;

    @Column(name = "LOCK_ARM_TYPE#UN")
    private Long lockArmTypeUn;

    @Column(name = "LOCK_ARM_COUNT")
    private Integer lockArmCount;

    @Column(name = "LOCK_ARM_MARKS")
    private String lockArmMarks;

    @Column(name = "LOCK_OWN")
    private Integer lockOwn;

    @Column(name = "SPEC_CONTAINER")
    private short specContainer;

    @Column(name = "FILLED_CONTAINER")
    private Integer filledContainer;

    @Column(name = "CON#UN")
    private String conUn;

    public Long getContainerListsUn() {
        return containerListsUn;
    }

    public void setContainerListsUn(Long containerListsUn) {
        this.containerListsUn = containerListsUn;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getContainerMark() {
        return containerMark;
    }

    public void setContainerMark(String containerMark) {
        this.containerMark = containerMark;
    }

    public Long getContainerTypeUn() {
        return containerTypeUn;
    }

    public void setContainerTypeUn(Long containerTypeUn) {
        this.containerTypeUn = containerTypeUn;
    }

    public Integer getNpp() {
        return npp;
    }

    public void setNpp(Integer npp) {
        this.npp = npp;
    }

    public Long getInvoiceUn() {
        return invoiceUn;
    }

    public void setInvoiceUn(Long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public Long getVagonListUn() {
        return vagonListUn;
    }

    public void setVagonListUn(Long vagonListUn) {
        this.vagonListUn = vagonListUn;
    }

    public Long getVagGroupUn() {
        return vagGroupUn;
    }

    public void setVagGroupUn(Long vagGroupUn) {
        this.vagGroupUn = vagGroupUn;
    }

    public BigDecimal getMassFact() {
        return massFact;
    }

    public void setMassFact(BigDecimal massFact) {
        this.massFact = massFact;
    }

    public BigDecimal getMassTara() {
        return massTara;
    }

    public void setMassTara(BigDecimal massTara) {
        this.massTara = massTara;
    }

    public BigDecimal getGp() {
        return gp;
    }

    public void setGp(BigDecimal gp) {
        this.gp = gp;
    }

    public Long getManagUn() {
        return managUn;
    }

    public void setManagUn(Long managUn) {
        this.managUn = managUn;
    }

    public String getPrevEtsngCode() {
        return prevEtsngCode;
    }

    public void setPrevEtsngCode(String prevEtsngCode) {
        this.prevEtsngCode = prevEtsngCode;
    }

    public Integer getcOwnerCode() {
        return cOwnerCode;
    }

    public void setcOwnerCode(Integer cOwnerCode) {
        this.cOwnerCode = cOwnerCode;
    }

    public Short getcOwnerType() {
        return cOwnerType;
    }

    public void setcOwnerType(Short cOwnerType) {
        this.cOwnerType = cOwnerType;
    }

    public String getcOwnerName() {
        return cOwnerName;
    }

    public void setcOwnerName(String cOwnerName) {
        this.cOwnerName = cOwnerName;
    }

    public Long getLockArmTypeUn() {
        return lockArmTypeUn;
    }

    public void setLockArmTypeUn(Long lockArmTypeUn) {
        this.lockArmTypeUn = lockArmTypeUn;
    }

    public Integer getLockArmCount() {
        return lockArmCount;
    }

    public void setLockArmCount(Integer lockArmCount) {
        this.lockArmCount = lockArmCount;
    }

    public String getLockArmMarks() {
        return lockArmMarks;
    }

    public void setLockArmMarks(String lockArmMarks) {
        this.lockArmMarks = lockArmMarks;
    }

    public Integer getLockOwn() {
        return lockOwn;
    }

    public void setLockOwn(Integer lockOwn) {
        this.lockOwn = lockOwn;
    }

    public short getSpecContainer() {
        return specContainer;
    }

    public void setSpecContainer(short specContainer) {
        this.specContainer = specContainer;
    }

    public Integer getFilledContainer() {
        return filledContainer;
    }

    public void setFilledContainer(Integer filledContainer) {
        this.filledContainer = filledContainer;
    }

    public String getConUn() {
        return conUn;
    }

    public void setConUn(String conUn) {
        this.conUn = conUn;
    }
}
