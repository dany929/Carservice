package com.carservice.client;

import com.carservice.model.Toorder;
import com.carservice.service.OrderService;
import com.carservice.service.PartService;
import com.carservice.service.ToorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ToorderController {

    private ToorderService toorderService;
    private  PartService partService;
    private OrderService orderService;

    @Autowired(required = true)
    @Qualifier(value = "toorderService")
    public void setToorderService(ToorderService ps)
    {
        this.toorderService = ps;
    }

    @Autowired(required = true)
    @Qualifier(value = "partService")
    public void setToorderService(PartService pp)
    {
        this.partService = pp;
    }

    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService ps)
    {
        this.orderService = ps;
    }

    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value = "/toorders/", method = RequestMethod.GET)
    public ModelAndView orderLinesList()
    {
        ModelAndView model = new ModelAndView("toorders");
        model.addObject("ordersFiltered",this.orderService.listFilteredOrders());
        model.addObject("partsOrdered", this.partService.filterByOrdered());
        return model;
    }


    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/toorders/add", method = RequestMethod.POST)
    public String addToOrder(@ModelAttribute("toorder") Toorder toorder)
    {
        this.toorderService.addToorder(toorder);
        return "redirect:/toorders";
    }

    @RequestMapping(value ="/toorders/update", method = RequestMethod.POST)
    public String updateToOrder(@ModelAttribute("toorder") Toorder toorder)
    {

        this.toorderService.addToorder(toorder);
        return "redirect:/toorders";
    }



    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/edittoorder/{id}")
    public String  updateToOrder(@PathVariable("id") int id, Model model)
    {

        model.addAttribute("toorder", this.toorderService.getToorderById(id));
        model.addAttribute("listToorder", this.toorderService.listToorders());

        System.err.println("Контроллер апдейт заполнил");

        return "toorders";
    }

}

