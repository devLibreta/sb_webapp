package com.dev.sbWebapp.java.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;

public class Run {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        marshal();
        Book bk = unmarshal();
        System.out.println(bk);
    }

    public static void marshal() throws JAXBException {
        Book book = new Book();
        book.setId(1L);
        book.setName("Book1");
        book.setAuthor("Author1");
        book.setDate(new Date());

        JAXBContext context = JAXBContext.newInstance(Book.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // 마샬러가 산출물을 포맷팅 설정을 true로 변경.
        mar.marshal(book, new File("./book.xml"));
    }

    public static Book unmarshal() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(Book.class);
        return (Book) context.createUnmarshaller().unmarshal(new FileReader("./book.xml"));
    }




}
