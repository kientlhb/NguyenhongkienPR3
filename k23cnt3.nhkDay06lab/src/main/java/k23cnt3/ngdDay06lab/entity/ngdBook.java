package k23cnt3.ngdDay06lab.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ngd_book")
public class ngdBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ngdId;

    String ngdCode;
    String ngdName;
    String ngdDescription;
    String ngdImgUrl;
    Integer ngdQuantity;
    Double ngdPrice;
    Boolean ngdActive;

    @ManyToMany
    @JoinTable(
            name = "ngd_book_author",
            joinColumns = @JoinColumn(name = "ngd_book_id"),
            inverseJoinColumns = @JoinColumn(name = "ngd_author_id")
    )
    List<ngdAuthor> ngdAuthors = new ArrayList<>();

    public Long getNgdId() {
        return ngdId;
    }

    public void setNgdId(Long ngdId) {
        this.ngdId = ngdId;
    }

    public String getNgdCode() {
        return ngdCode;
    }

    public void setNgdCode(String ngdCode) {
        this.ngdCode = ngdCode;
    }

    public String getNgdName() {
        return ngdName;
    }

    public void setNgdName(String ngdName) {
        this.ngdName = ngdName;
    }

    public String getNgdDescription() {
        return ngdDescription;
    }

    public void setNgdDescription(String ngdDescription) {
        this.ngdDescription = ngdDescription;
    }

    public String getNgdImgUrl() {
        return ngdImgUrl;
    }

    public void setNgdImgUrl(String ngdImgUrl) {
        this.ngdImgUrl = ngdImgUrl;
    }

    public Integer getNgdQuantity() {
        return ngdQuantity;
    }

    public void setNgdQuantity(Integer ngdQuantity) {
        this.ngdQuantity = ngdQuantity;
    }

    public Double getNgdPrice() {
        return ngdPrice;
    }

    public void setNgdPrice(Double ngdPrice) {
        this.ngdPrice = ngdPrice;
    }

    public Boolean getNgdActive() {
        return ngdActive;
    }

    public void setNgdActive(Boolean ngdActive) {
        this.ngdActive = ngdActive;
    }

    public List<ngdAuthor> getNgdAuthors() {
        return ngdAuthors;
    }

    public void setNgdAuthors(List<ngdAuthor> ngdAuthors) {
        this.ngdAuthors = ngdAuthors;
    }
}
