package com.example.music.inbox.backend.event;

import com.example.music.inbox.backend.entity.User;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompletionEvent  extends ApplicationEvent {

    public RegistrationCompletionEvent(User user) {
        super(user);
    }
}
