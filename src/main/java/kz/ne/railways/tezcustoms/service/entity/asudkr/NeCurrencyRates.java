package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="NE_CURRENCY_RATES", schema="NSI")
public class NeCurrencyRates {
	
	@Id
	@Column(name="CURRENCY_RATE_UN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long currencyRateUn;
	
	@Column(name="CURRENCU_CODE_UN")
	private Long currencuCodeUn;
	
	@Column(name="RATE")
	private Double rate;
	
	@Column(name="RATE_BGN")
	private Timestamp rateBgn;
	
	@Column(name="RATE_END")
	private Timestamp rateEnd;

	public Long getCurrencyRateUn() {
		return currencyRateUn;
	}

	public void setCurrencyRateUn(Long currencyRateUn) {
		this.currencyRateUn = currencyRateUn;
	}

	public Long getCurrencuCodeUn() {
		return currencuCodeUn;
	}

	public void setCurrencuCodeUn(Long currencuCodeUn) {
		this.currencuCodeUn = currencuCodeUn;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Timestamp getRateBgn() {
		return rateBgn;
	}

	public void setRateBgn(Timestamp rateBgn) {
		this.rateBgn = rateBgn;
	}

	public Timestamp getRateEnd() {
		return rateEnd;
	}

	public void setRateEnd(Timestamp rateEnd) {
		this.rateEnd = rateEnd;
	}
}
