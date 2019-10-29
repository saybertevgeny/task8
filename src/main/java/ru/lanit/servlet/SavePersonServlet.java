package ru.lanit.servlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lanit.exception.MappingException;
import ru.lanit.provider.SessionFactoryProvider;
import ru.lanit.entity.Person;
import ru.lanit.mapper.request.PersonMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save-person")
public class SavePersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean existError = false;
        try (SessionFactory sessionFactory = SessionFactoryProvider.getInstance().getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Person person = PersonMapper.map(request);
            session.save(person);
            session.getTransaction().commit();
        } catch (MappingException e) {
            existError = true;
            request.setAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Ошибка при сохранении");
            existError = true;
        }
        if (!existError) {
            request.setAttribute("successMessage", "Пользователь сохранен");
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
