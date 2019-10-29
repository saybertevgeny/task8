package ru.lanit.mapper.request;

import ru.lanit.entity.Person;
import ru.lanit.exception.MappingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class PersonMapper {

    private enum RequiredParams{firstname,middlename,lastname,birthday}

    public static Person map(HttpServletRequest request) throws MappingException{
        Person person = new Person();
        for(PersonMapper.RequiredParams p: PersonMapper.RequiredParams.values()){
            String paramValue = request.getParameter(p.name());
            if(paramValue == null || paramValue.isEmpty()){
                throw new MappingException("Все поля обязательны");
            }
        }
        person.setFirstName(request.getParameter("firstname"))
                .setMiddleName(request.getParameter("middlename"))
                .setLastName(request.getParameter("lastname"))
                .setBirthDate(LocalDate.parse(request.getParameter("birthday")));
        return person;
    }
}
