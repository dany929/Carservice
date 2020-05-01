package com.Carservice.client;


import com.Carservice.model.Order;
import com.Carservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class OrderController {

    private OrderService orderService;

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

    @RequestMapping(value ="orders", method = RequestMethod.GET)
    public String listOrders(Model model) {
        model.addAttribute("order", new Order());

        model.addAttribute("listOrders", this.orderService.listOrders());
        return "orders";
    }

    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */
    @RequestMapping(value ="/orders/add", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("orders") Order order)
    {
        System.err.println("Контроллер Адд ");
        if(order.getOrderid() == 0)
        {
            System.err.println("Контроллер Адд попал в иф");
            this.orderService.addOrder(order);
        }
        else
            {
                System.err.println("Контроллер Адд попал в елсу");
                this.orderService.updateOrder(order);
            }

        return "redirect:/orders";
    }

    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/removeorder/{id}")
    public String  removeOrder(@PathVariable("id") int id)
    {
        this.orderService.removeOrder(id);
        return  "redirect:/orders";
    }

    /**
     * Заполнение полей для добавления\обновления записи
     */
    @RequestMapping("/editorder/{id}")
    public String  updateOrder(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("order", this.orderService.getOrderById(id));
        model.addAttribute("listOrders", this.orderService.listOrders());

        System.err.println("Контроллер апдейт заполнил");

        return "orders";
    }





}

