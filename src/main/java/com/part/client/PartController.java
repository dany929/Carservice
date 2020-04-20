package com.part.client;

import com.part.model.Part;
import com.part.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class PartController {

    private PartService partService;

    @Autowired(required = true)
    @Qualifier(value = "partService")
    public void setPartService(PartService ps)
    {
        this.partService = ps;
    }


    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value ="parts", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        model.addAttribute("part", new Part());
        model.addAttribute("listParts", this.partService.listParts());
        return "parts";
    }

    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/parts/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("part") Part part)
    {

        if(!partService.listParts().contains(part))
        {
            System.err.println("Контроллер Адд попал в иф");
            this.partService.addPart(part);
        }
        else
            {
                System.err.println("Контроллер Адд попал в елсу");
                this.partService.updatePart(part);
            }

        return "redirect:/parts";
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/removepart/{id}")
    public String  removePart(@PathVariable("id") int id)
    {
        this.partService.removePart(id);
        return  "redirect:/parts";
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/editpart/{id}")
    public String  updatePart(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("part", this.partService.getPartById(id));
        model.addAttribute("listParts", this.partService.listParts());

        System.err.println("Контроллер апдейт заполнил");

        return "parts";
    }

/*
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Customer> listFilter()
    {
        return this.partService.listFilter();
    }

    @RequestMapping("customerdata/{id}")
    public String bookData(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", this.partService.getCustomerById(id));

        return "customerdata";
    }
   // */



}

