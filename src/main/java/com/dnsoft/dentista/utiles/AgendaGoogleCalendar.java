/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.dentista.utiles;

import com.dnsoft.dentista.beans.Consulta;
import com.dnsoft.dentista.beans.Parametros;
import com.dnsoft.dentista.daos.IParametrosDAO;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgendaGoogleCalendar {

    private static final String APPLICATION_NAME = "Consultorio odontologico";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static String CREDENTIALS_FOLDER; // Directory to store user credentials.
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";
    Calendar service;
    private static Parametros PARAMETROS;
    Container container;
    IParametrosDAO dAO;

    public AgendaGoogleCalendar() throws GeneralSecurityException, IOException {

        this.container = Container.getInstancia();
        this.dAO = container.getBean(IParametrosDAO.class);
        this.PARAMETROS = dAO.findOne(new Long(1));
        this.CREDENTIALS_FOLDER = PARAMETROS.getDirectorioCredencialesGoogleCalendar();

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = AgendaGoogleCalendar.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

    }

    public void creaEvento(Consulta consulta) throws IOException {

        Event event = new Event()
                .setSummary("Dentista " + consulta.getPaciente().toString())
                .setLocation(PARAMETROS.getDireccionConsultorio())
                .setDescription(consulta.getComentario());

        DateTime inicioFechaHora = new DateTime(consulta.getFechaConsulta().toString() + "T"
                + consulta.getHoraConsultaDesde().toString()+":00-03:00");

        EventDateTime iniciofechaEvento = new EventDateTime()
                .setDateTime(inicioFechaHora)
                .setTimeZone("America/Montevideo");
        event.setStart(iniciofechaEvento);

        DateTime finFechaHora = new DateTime(consulta.getFechaConsulta().toString() + "T"
                + consulta.getHoraConsultaHasta().toString()+":00-03:00");
        EventDateTime finEvento = new EventDateTime()
                .setDateTime(finFechaHora)
                .setTimeZone("America/Montevideo");

        if (consulta.getPaciente().getNotificaGoogleCalendar() == true) {

            String[] recurrence = new String[]{"RRULE:FREQ=DAILY;COUNT=1"};
            event.setRecurrence(Arrays.asList(recurrence));

            EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail(consulta.getPaciente().getEmail())};
            event.setAttendees(Arrays.asList(attendees));

            EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10)};
            Event.Reminders reminders = new Event.Reminders()
                    .setUseDefault(false)
                    .setOverrides(Arrays.asList(reminderOverrides));
            event.setReminders(reminders);

        }

        event.setEnd(finEvento);
        event.setId("0000" + consulta.getId().toString());
        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Evento creado correctamente: %s\n", event.getHtmlLink());

    }

    public void modificarEvento(Consulta consulta) throws IOException {

        Event event = service.events().get("primary", "0000" + consulta.getId()).execute();

        event.setSummary("Dentista " + consulta.getPaciente().toString())
                .setLocation(PARAMETROS.getDireccionConsultorio())
                .setDescription(consulta.getComentario());

        DateTime inicioFechaHora = new DateTime(consulta.getFechaConsulta().toString() + "T"
                + consulta.getHoraConsultaDesde().toString() + ":00-03:00");

        EventDateTime iniciofechaEvento = new EventDateTime()
                .setDateTime(inicioFechaHora);
        //.setTimeZone("Montevideo (UYT)");
        event.setStart(iniciofechaEvento);

        DateTime finFechaHora = new DateTime(consulta.getFechaConsulta().toString() + "T"
                + consulta.getHoraConsultaHasta().toString() + ":00-03:00");
        EventDateTime finEvento = new EventDateTime()
                .setDateTime(finFechaHora);
        //.setTimeZone("Montevideo (UYT)");
        event.setEnd(finEvento);

        Event updatedEvent = service.events().update("primary", event.getId(), event).execute();
        System.out.println(updatedEvent.getUpdated());
    }

    public void eliminarEvento(Consulta consulta) throws IOException {

        Event event = service.events().get("primary", "0000" + consulta.getId()).execute();
        service.events().delete("primary", event.getId()).execute();

    }

    public void consultarEventos(Calendar service) throws IOException, GeneralSecurityException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

    }
}
