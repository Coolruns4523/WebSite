package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ContactRepository contactRepository;

    @RequestMapping("/health")
    public String health() {
        return "health";
    }

    @GetMapping("/addContact")
    public String contactForm (Model model)
    {
        model.addAttribute("aContact", new Contact());
        return "contactForm";
    }

    @PostMapping("/saveContact")
    public String saveContact(@Valid Contact contact, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "contactForm";
        }
        contactRepository.save(contact);
        return "redirect:/";
    }

    @RequestMapping("/update/{id}")
    public String updateContact (@PathVariable("id") long id,Model model)
    {
        model.addAttribute("aContact", contactRepository.findContactById(id));
        return "contactForm";
    }

    @RequestMapping("/detail/{id}")
    public String showContact (@PathVariable("id") long id, Model model)
    {
        model.addAttribute("aContact", contactRepository.findContactById(id));
        return "contactForm";
    }

    @RequestMapping("/delete?{id}")
    public String delContact(@PathVariable("id") long id, Model model)
    {
        contactRepository.deleteById(id);
        return "redirect:/";
    }

}
