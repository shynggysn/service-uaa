package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema="NSI", name="NE_CUSTOMS_ORGS")
public class NeCustomsOrgs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CUSTOMS_ORG_UN")
	private Long customsOrgUn;
	@Column(name="CUSTOM_CODE", length = 10)
	private String customCode;
	@Column(name="CUSTOM_NAME", length = 1024)
	private String customName;
	@Column(name="CUSTOM_ORG_BGN")
	private Timestamp customOrgBgn;
	@Column(name="CUSTOM_ORG_END")
	private Timestamp customOrgEnd;
	@Column(name="COUNTRY_NO")
	private String countryNo;
	
	public void setCustomsOrgUn(Long customsOrgUn) {
		this.customsOrgUn = customsOrgUn;
	}
	public Long getCustomsOrgUn() {
		return customsOrgUn;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomOrgBgn(Timestamp customOrgBgn) {
		this.customOrgBgn = customOrgBgn;
	}
	public Timestamp getCustomOrgBgn() {
		return customOrgBgn;
	}
	public void setCustomOrgEnd(Timestamp customOrgEnd) {
		this.customOrgEnd = customOrgEnd;
	}
	public Timestamp getCustomOrgEnd() {
		return customOrgEnd;
	}
	public String getCountryNo() {
		return countryNo;
	}
	public void setCountryNo(String countryNo) {
		this.countryNo = countryNo;
	}
}
