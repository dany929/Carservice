package com.carservice.client;

import com.carservice.model.Part;
import com.carservice.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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
    @RequestMapping(value ="/parts/", method = RequestMethod.GET)
    public ModelAndView listParts() {
        ModelAndView model = new ModelAndView("parts");
        model.addObject("part", new Part());
        model.addObject("listParts", this.partService.listParts());
        return model;
    }

   /**
    * Запрос при нажатии на кнопку добавления записи
    * проверка на повторяющийся элемент в списке
    */
   @RequestMapping(value ="/parts/add", method = RequestMethod.POST)
   public ModelAndView addPart(Part part)
   {
       this.partService.addPart(part);
       return new ModelAndView("redirect:/parts/") ;
   }
   /**
    * Запрос на удаление по id
    */
   @RequestMapping(value = "/parts/remove", method = RequestMethod.POST)
   public ModelAndView  removePart(@RequestParam int id)
   {
       this.partService.removePart(id);
       return  new ModelAndView("redirect:/parts/") ;
   }
   /**
    * Заполнение полей для добавления\обновления записи
    */
   @RequestMapping(value = "/parts/edit", method = RequestMethod.POST)
   public ModelAndView  updatePart(@RequestParam int id)
   {
       ModelAndView model = new ModelAndView("parts");
       model.addObject("part", this.partService.getPartById(id));
       model.addObject("listParts", this.partService.listParts());
       System.err.println("Контроллер апдейт заполнил");
       return model;
   }


}

