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

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
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
/*
    @RequestMapping(value ="toorders", method = RequestMethod.GET)
    public String listToorder(Model model) {
        model.addAttribute("toorder", new Toorder());
        model.addAttribute("listToorder", this.toorderService.listToorders());
        return "toorders";
    }*/


    @RequestMapping(value = "/toorders/", method = RequestMethod.GET)
    public ModelAndView orderLinesList()
    {

        ModelAndView model = new ModelAndView("toorders");
        model.addObject("listToorder", this.toorderService.listToorders());
        model.addObject("productsListFiltered", this.partService.filterByAvgCost());
     //  model.addObject("productsListOrdered", this.partService.filterByOrderCostProducts());
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


/*

    @RequestMapping("/removetoorder/{id}")
    public String  removeToOrder(@PathVariable("id") int id)
    {
        //Обращение к списку текущего заказа из которого удаляются услуги
        //Простановка цены по оставшимся услугам
        Toorder f = this.toorderService.getToorderById(id);
        Order o=f.getOrder();
        this.toorderService.removeToorder(id);
        List<Toorder> toorderList = this.orderService.getOrderById(o.getOrderid()).getToorders();

        double sum = 0;
        for(Toorder t: toorderList)
        {
            sum+=t.getPart().getPrice()*t.getNumofparts()+t.getOperation().getPrice();
        }

        System.err.println(sum);
        sum=sum*(1-(double)o.getDiscount()/100);
        System.err.println(sum);
        o.setTotalcost(sum);
        System.err.println(o.getTotalcost());

        this.orderService.updateOrder(o);

        return  "redirect:/orders";
    }
*/
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

/*
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<Customer> listFilter()
    {
        return this.toorderService.listFilter();
    }

    @RequestMapping("customerdata/{id}")
    public String bookData(@PathVariable("id") String id, Model model){
        model.addAttribute("customer", this.toorderService.getCustomerById(id));

        return "customerdata";
    }
   // */



}

