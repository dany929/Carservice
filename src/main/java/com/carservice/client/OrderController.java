package com.carservice.client;


import com.carservice.model.Operation;
import com.carservice.model.Order;
import com.carservice.model.Part;
import com.carservice.model.Toorder;
import com.carservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//@ImportResource("/WEB-INF/dispatcher-servlet.xml")
@Controller
public class OrderController {

    private OrderService orderService;
    private PartService partService;
    private CustomerService customerService;
    private ToorderService toorderService;
    private OperationService operationService;

    @Autowired()
    @Qualifier(value = "partService")
    public void setProductService(PartService ps)
    {
        this.partService = ps;
    }
    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService ps)
    {
        this.orderService = ps;
    }
    @Autowired(required = true)
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService cs)
    {
        this.customerService = cs;
    }
    @Autowired(required = true)
    @Qualifier(value = "toorderService")
    public void setToorderService(ToorderService ts){ this.toorderService = ts;    }
    @Autowired(required = true)
    @Qualifier(value = "operationService")
    public void setOperationService(OperationService os){ this.operationService = os;    }

    /**
     * Вывод списка на стартовой странице
     * атрибуты модели используются в jsp файле
     */

    @RequestMapping(value ="orders", method = RequestMethod.GET)
    public String listOrders(Model model) {
        model.addAttribute("order", new Order());
        ///////////////////////////////////////////
      //  model.addAttribute("listpart",this.orderService.listPartsByOrder());
        //////////////////////////////////////////
        model.addAttribute("listOrders", this.orderService.listOrders());
        return "orders";
    }



    /**
     * Запрос при нажатии на кнопку добавления записи
     * проверка на повторяющийся элемент в списке
     */

    /*
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

     */
    @RequestMapping(value ="/orders/chooseCustomer", method = RequestMethod.GET)
    public ModelAndView addOrder() {
        ModelAndView model = new ModelAndView("ChooseCustomer");

        model.addObject("listCustomers", this.customerService.listCustomers());
        return model;
    }

    @RequestMapping(value = "/orders/client", method = RequestMethod.GET)
    public  ModelAndView addOrder(String id)
    {
        ModelAndView model = new ModelAndView("ChoosePart");
        model.addObject("orderId", 0);
        model.addObject("gosznak", id);
        model.addObject("listParts", this.partService.listParts());
        model.addObject("listOperations", this.operationService.listOperations());
        return model;
    }

    @RequestMapping(value = "/orders/add", method = RequestMethod.GET)
    public ModelAndView addOrder(HttpServletRequest request)
    {

        //Парсим параметры и инициализируем все объекты и переменные
        String prds = request.getParameter("id");

        String oper = request.getParameter("idopr");

        String client = request.getParameter("clt");

        int numofparts = Integer.parseInt(request.getParameter("num"));
        int discount = Integer.parseInt(request.getParameter("dis"));
        int ord = Integer.parseInt(request.getParameter("ord"));
        List<Toorder> products = new ArrayList<>();
        List<Toorder> operations = new ArrayList<>();

        int sum = 0;
        Order order;

        //Проверяем новый ли заказ
        //Если да, ищем его
        //Если нет, инициализурем как новую переменную
        if(ord > 0)
        {
            order = this.orderService.findOrder(ord);
            for(Toorder o : order.getToorders())
            {
                this.toorderService.deleteOrderLine(o);
            }
        }
        else
        {

            order = new Order();

            LocalDate localDate = LocalDate.now();
            order.setDatein(DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate));
        }

        System.err.println("до парт?");
        /*
        //Заполняем заказ
        for(String p: prds)
        {
            Part temp = this.partService.getPartById(Integer.parseInt(p));

            Toorder line = new Toorder();
            System.err.println("После нового тоордера?");
            line.setPart(temp);
            System.err.println("После добавления частей?");
          //  line.setNumofparts(Integer.parseInt(request.getParameter(p)));
            line.setNumofparts(8);
            System.err.println("После колва*?");
            products.add(line);
            System.err.println("После добавления в продуктс?");
            sum += temp.getPrice() * 8;
            System.err.println("После суммы?");
        }
        System.err.println("после парт переж опер?");
        for(String o: oper)
        {
            Operation tmp = this.operationService.getOperationById(Integer.parseInt(o));
            Toorder line = new Toorder();
            line.setOperation(tmp);
            operations.add(line);
            sum += tmp.getPrice();
        }

         */


        //Заполняем заказ

            Part temp = this.partService.getPartById(Integer.parseInt(prds));

            Toorder line = new Toorder();
            System.err.println("После нового тоордера?");
            line.setPart(temp);
            System.err.println("После добавления частей?");
            //  line.setNumofparts(Integer.parseInt(request.getParameter(p)));
            line.setNumofparts(numofparts);
            System.err.println("После колва*?");
            products.add(line);
            System.err.println("После добавления в продуктс?");
            sum += temp.getPrice() * numofparts;
            System.err.println("После суммы?");

        System.err.println("после парт переж опер?");

            Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));

            line.setOperation(tmp);
            products.add(line);
            sum += tmp.getPrice();
            sum=sum*(1-discount/100);





        //Заполняем остальные данные и отправляем на добавление/обновление
        order.setToorders(products);

        System.err.println("Хф");


        order.setTotalcost(sum);
        System.err.println(this.customerService.getCustomerById(client));
        order.setCustomer(this.customerService.getCustomerById(client));
        order.setDiscount(discount);

        this.orderService.addOrder(order);
        this.toorderService.addToorder(line);
        return new ModelAndView("redirect:/orders");
    }



    //Обработчик ссылки редактирования заказа
    //Принимает номер заказа для редактирования и выводит страницу выбора товаров
    @RequestMapping(value = "/orders/edit", method = RequestMethod.GET)
    public ModelAndView editOrder(int id)
    {
        ModelAndView model = new ModelAndView("ChoosePart");
        model.addObject("orderId", id);
        model.addObject("gosznak", this.orderService.findOrder(id).getCustomer().getGosznak());
        model.addObject("listParts", this.partService.listParts());
        model.addObject("listOperations", this.operationService.listOperations());
        return model;
    }





    /**
     * Запрос на удаление по id
     */
    @RequestMapping("/removeorder/{id}")
    public String  removeOrder(@PathVariable("id") int id)
    {
        this.orderService.deleteOrders(id);
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

