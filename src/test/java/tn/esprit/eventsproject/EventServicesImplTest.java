package tn.esprit.eventsproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServicesImplTest {

    @InjectMocks
    private EventServicesImpl eventServices;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddParticipant() {
        Participant participant = new Participant(1, "John", "Doe", Tache.INVITE, null);
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant savedParticipant = eventServices.addParticipant(participant);

        assertNotNull(savedParticipant);
        assertEquals("John", savedParticipant.getNom());
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    void testAddAffectEvenParticipant() {
        Participant participant = new Participant(1, "John", "Doe", Tache.INVITE, new HashSet<>());
        Event event = new Event(1, "Sample Event", LocalDate.now(), LocalDate.now().plusDays(1), 0, null, null);

        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event savedEvent = eventServices.addAffectEvenParticipant(event, 1);

        assertNotNull(savedEvent);
        assertEquals("Sample Event", savedEvent.getDescription());
        verify(participantRepository, times(1)).findById(1);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testAddAffectLog() {
        Event event = new Event(1, "Event1", LocalDate.now(), LocalDate.now().plusDays(1), 0, null, new HashSet<>());
        Logistics logistics = new Logistics(1, "Chairs", true, 50.0f, 10);

        when(eventRepository.findByDescription("Event1")).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        Logistics savedLogistics = eventServices.addAffectLog(logistics, "Event1");

        assertNotNull(savedLogistics);
        assertEquals("Chairs", savedLogistics.getDescription());
        verify(eventRepository, times(1)).findByDescription("Event1");
        verify(logisticsRepository, times(1)).save(logistics);
    }

    @Test
    void testGetLogisticsDates() {
        Logistics logistics = new Logistics(1, "Chairs", true, 50.0f, 10);
        Event event = new Event(1, "Event1", LocalDate.now(), LocalDate.now().plusDays(1), 0, null, Set.of(logistics));

        when(eventRepository.findByDateDebutBetween(LocalDate.now(), LocalDate.now().plusDays(5)))
                .thenReturn(List.of(event));

        List<Logistics> logisticsList = eventServices.getLogisticsDates(LocalDate.now(), LocalDate.now().plusDays(5));

        assertNotNull(logisticsList);
        assertEquals(1, logisticsList.size());
        assertEquals("Chairs", logisticsList.get(0).getDescription());
        verify(eventRepository, times(1)).findByDateDebutBetween(LocalDate.now(), LocalDate.now().plusDays(5));
    }

    @Test
    void testCalculCout() {
        Logistics logistics = new Logistics(1, "Chairs", true, 50.0f, 10);
        Event event = new Event(1, "Event1", LocalDate.now(), LocalDate.now().plusDays(1), 0, null, Set.of(logistics));

        when(eventRepository.findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache(
                "Tounsi", "Ahmed", Tache.ORGANISATEUR)).thenReturn(List.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        eventServices.calculCout();

        verify(eventRepository, times(1))
                .findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache("Tounsi", "Ahmed", Tache.ORGANISATEUR);
        verify(eventRepository, times(1)).save(event);
    }
}
