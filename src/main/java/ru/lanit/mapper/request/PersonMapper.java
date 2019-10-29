package ru.lanit.mapper.request;

import ru.lanit.entity.Person;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class PersonMapper {

    public static Person map(HttpServletRequest request){
        Person person = new Person();
        person.setFirstName(request.getParameter("firstname"))
                .setMiddleName(request.getParameter("middlename"))
                .setLastName(request.getParameter("lastname"))
                .setBirthDate(LocalDate.parse(request.getParameter("birthday")));
        return person;
    }
}
