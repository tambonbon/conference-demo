package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sessions")
public class SessionsController {
    // inject or auto wire the sessions repository that we just created
    @Autowired
    private SessionRepository sessionRepository;


    @GetMapping
    public List<Session> list() {
        return sessionRepository.list();
    }

    @GetMapping
    @RequestMapping("{id}") // our class RequestMapping is api/v1/sessions
                            // and the RequestMapping on this is adding an additional id to url
    public Session get(@PathVariable Long id) {
        return sessionRepository.find(id);
    }

    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED) // specify the exact response is occur
    public Session create(@RequestBody final Session session) {
        return sessionRepository.create(session); // save object as you're working with it,
                                                        // but it doesn't get committed to db until you flush it
    }
    
//    @DeleteMapping
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE) // Spring only provides @get and @post
    public void delete(@PathVariable Long id) {
        // Also need to check for children records before deleting
        sessionRepository.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // because this is a PUT, we expect all attr. to be passed in
            // PUT will replace all of the attr. on the record you're updating
            // A PATCH would only need a portion of the attr. to be updated
        //TODO: Add validation
        Session existingSession = sessionRepository.find(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        // if dont ignore PK, you'll copy null onto PK
        return sessionRepository.create(existingSession);
    }
}
