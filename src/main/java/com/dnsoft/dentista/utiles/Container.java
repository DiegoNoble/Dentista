package com.dnsoft.dentista.utiles;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe responsavel por inicializar o Spring, carregar os beans e injetar as dependencias.
 */
public class Container {
    /**
     * Array que contém os 2 arquivos de inicializacao do Spring.
     */
    private static final String[] APPLICATION_CONTEXT = {"applicationContext.xml"};

    private ApplicationContext contextoSpring;

    // Instancia estática privada que será acessada.
    private static Container instancia = new Container();

    // Construtor privado suprime o construtor público padrao.
    private Container() {
        super();
    }

    // Método público estático de acesso único ao objeto!
    public static synchronized Container getInstancia() {
        return instancia;
    }


    // Carrega o container do Spring e inicializa o framework.
    private ApplicationContext getContextoSpring() {
        if (contextoSpring == null) {
            contextoSpring = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
        }
        return contextoSpring;
    }

    // Retorna um bean carregado pelo Spring
    public <T> T getBean(Class<T> clazz) {
        ApplicationContext contexto = getContextoSpring();
        if (contexto != null) {
            try {
                return contexto.getBean(clazz);
            } catch (NoSuchBeanDefinitionException ex) {
                return null;
            }
        }
        return null;
    }
}

