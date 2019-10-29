package ru.lanit.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lanit.entity.Person;
import ru.lanit.mapper.request.PersonMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save-person")
public class SavePersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try( SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory() ){
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Person person = PersonMapper.map(request);
            session.save(person);
            session.getTransaction().commit();
        }
        response.sendRedirect("/");
    }
}
