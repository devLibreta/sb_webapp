package com.dev.sbWebapp.java.jaxb;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "name", "date" }) // XML 출력 순서 설정
@Getter
@ToString
//@XmlAccessorType(XmlAccessType.FIELD) // 자동매핑설정
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    @XmlAttribute // 명시적 매핑. XML 문서의 attribute 값으로 설정한다.
    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title") // 명시적 매핑. XML 문서의 element 값으로 설정한다.
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient // XML 문서에 포함되지 않는다.
    public void setAuthor(String author) {
        this.author = author;
    }
//    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(DateAdapter.class) // 명시적 매핑. XML 값이 특정한 타입의 포맷이 아니면 adapter를 사용한다.
    public void setDate(Date date) {
        this.date = date;
    }
}
