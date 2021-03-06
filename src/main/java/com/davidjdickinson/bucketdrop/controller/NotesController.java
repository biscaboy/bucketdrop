package com.davidjdickinson.bucketdrop.controller;

import com.davidjdickinson.bucketdrop.model.Note;
import com.davidjdickinson.bucketdrop.model.User;
import com.davidjdickinson.bucketdrop.services.ResponsePackingService;
import com.davidjdickinson.bucketdrop.services.ValidationService;
import com.davidjdickinson.bucketdrop.services.NoteService;
import com.davidjdickinson.bucketdrop.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private UserService userService;
    private NoteService noteService;
    private ValidationService validationService;
    private ResponsePackingService packingService;

    public NotesController(UserService userService, NoteService noteService,
                           ValidationService validationService, ResponsePackingService packingService) {
        this.userService = userService;
        this.noteService = noteService;
        this.validationService = validationService;
        this.packingService = packingService;

    }

    @GetMapping("/delete")
    public String deleteNote(Authentication authentication, @ModelAttribute Note note, Model model) {

        User currentUser = userService.getUser(authentication.getName());
        note = noteService.getNote(note.getNoteId());

        boolean result = validationService.validate(note, currentUser, model,"delete");

        if (result) {
            result = noteService.deleteNote(note);
        }

        return packingService.packResultResponse(result, model, currentUser, noteService);
    }

    @PostMapping()
    public String editNote(Authentication authentication, @ModelAttribute Note note, Model model) {

        boolean result = false;
        User currentUser = userService.getUser(authentication.getName());

        if (note.getNoteId() == null) {
            note.setUserId(currentUser.getUserId());
            result = noteService.createNote(note);
        } else {
            Note existing = noteService.getNote(note.getNoteId());
            result = validationService.validate(existing, currentUser, model,"create or edit");
            if (result) {
                note.setUserId(currentUser.getUserId());
                result = noteService.updateNote(note);
            }
        }

        return packingService.packResultResponse(result, model, currentUser, noteService);
    }
}
