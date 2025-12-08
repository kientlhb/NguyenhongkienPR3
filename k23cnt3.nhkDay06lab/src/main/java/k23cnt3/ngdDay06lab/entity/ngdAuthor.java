package k23cnt3.ngdDay06lab.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ngd_author")
public class ngdAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ngdId;

    String ngdCode;
    String ngdName;
    String ngdDescription;
    String ngdImgUrl;
    String ngdEmail;
    String ngdPhone;
    String ngdAddress;
    Boolean ngdActive;

    @ManyToMany(mappedBy = "ngdAuthors")
    List<ngdBook> ngdBooks = new ArrayList<>();
}
