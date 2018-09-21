/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetoBD;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author cn05814135
 */
public class CreateUser {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setUsername("Usuario"+i);
                user.setPassword("123");
                user.setCreateTime(new Date());
                user.setExpireTime(new Date());
                session.save(user);
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        System.exit(0);

    }

}
