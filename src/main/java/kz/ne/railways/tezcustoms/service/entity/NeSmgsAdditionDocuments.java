package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="NE_SMGS_ADDITION_DOCUMENTS", schema = "KTZ")
public class NeSmgsAdditionDocuments implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SMGS_ADDITION_DOCS#UN")
	private Long smgsAdditionDocsUn;

	@Column(name="INV#UN")
	private long invUn;

	@Column(name="DOC_NUMBER")
	private String docNumber;
	
	@Column(name="DOC_NAME")
	private String docName;

	@Column(name="DOC_DATE")
	private Date docDate;
	
	@Column(name="FILENAME")
	private String fileName;
	
	@Column(name="FILE_UUID")
	private String fileUuid;

	private int status;

	private static final long serialVersionUID = 1L;

	public NeSmgsAdditionDocuments() {
		super();
	}

	
	
	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public Long getSmgsAdditionDocsUn() {
		return this.smgsAdditionDocsUn;
	}

	public void setSmgsAdditionDocsUn(Long smgsAdditionDocsUn) {
		this.smgsAdditionDocsUn = smgsAdditionDocsUn;
	}

	public long getInvUn() {
		return this.invUn;
	}

	public void setInvUn(long invUn) {
		this.invUn = invUn;
	}

	public String getDocNumber() {
		return this.docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Date getDocDate() {
		return this.docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocName() {
		return docName;
	}



	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}



	public String getFileUuid() {
		return fileUuid;
	}

}
