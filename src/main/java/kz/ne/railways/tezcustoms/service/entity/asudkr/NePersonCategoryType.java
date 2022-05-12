package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "NSI", name = "NE_PERSON_CATEGORY_TYPE")
public class NePersonCategoryType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_TYPE_UN")
    private Long categoryTypeUn;

    @Column(name = "CATEGORY_CODE", length = 25)
    private String categoryCode;

    @Column(name = "CATEGORY_NAME", length = 512)
    private String categoryName;

    @Column(name = "CATEGORY_BGN")
    private Timestamp categoryBgn;

    @Column(name = "CATEGORY_END")
    private Timestamp categoryEnd;

    public void setCategoryTypeUn(Long categoryTypeUn) {
        this.categoryTypeUn = categoryTypeUn;
    }

    public Long getCategoryTypeUn() {
        return categoryTypeUn;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryBgn(Timestamp categoryBgn) {
        this.categoryBgn = categoryBgn;
    }

    public Timestamp getCategoryBgn() {
        return categoryBgn;
    }

    public void setCategoryEnd(Timestamp categoryEnd) {
        this.categoryEnd = categoryEnd;
    }

    public Timestamp getCategoryEnd() {
        return categoryEnd;
    }


}
