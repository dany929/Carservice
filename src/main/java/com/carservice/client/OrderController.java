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

///Обновление цен заказов
        List<Order> order =this.orderService.listOrders();
        for (Order o: order)
        {
            double sum = 0;
            for( Toorder t: o.getToorders())
            {
                sum+=t.getPart().getPrice()*t.getNumofparts()+t.getOperation().getPrice();
            }
            System.err.println(sum);
            sum=sum*(1-(double)o.getDiscount()/100);
            System.err.println(sum);
            o.setTotalcost(sum);
            System.err.println(o.getTotalcost());
            this.orderService.updateOrder(o);
        }

        return "orders";
    }

    /**
     * Добавление клиента к заказу
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
        double discount = Double.parseDouble(request.getParameter("dis"));
        int ord = Integer.parseInt(request.getParameter("ord"));
        List<Toorder> products = new ArrayList<>();

        double sum = 0;
        Order order;


        /**
         * В положительной ветке подтягивается список параметров
         * В имеющийся заказ добавляется новые товары и услуги, обновляется цена для заказа
         *
         * В отрицательной ветке создается новый заказ
         */
        if(ord > 0)
        {

            order = this.orderService.findOrder(ord);

            Part temp = this.partService.getPartById(Integer.parseInt(prds));

            Toorder line = new Toorder();

            int lastToOrder=this.toorderService.listLastToorder();
            line.setToorderid(lastToOrder+1);
            line.setOrder(order);
            System.err.println("Номер нового тоордера"+lastToOrder);

            line.setPart(temp);


            line.setNumofparts(numofparts);


            sum += temp.getPrice() * numofparts;


            Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));

            line.setOperation(tmp);
            products.add(line);

            sum += tmp.getPrice();

            sum=sum*(1-discount/100);


            /**
             * Заполнение данных о деталях и услугах
             */
            order.setToorders(products);

            order.setTotalcost(sum);


            if(discount!=-1)
            {
                order.setDiscount((int)(discount));
            }

            System.err.println("!!!!!!!!!!!");
            System.err.println(order.toString()+order.getToorders().toString());
            this.orderService.updateOrder(order);

            //Добавление нового подзаказа
            this.toorderService.addToorder(line);

            System.err.println("!!!!!!!!!!!");
            System.err.println(order.toString()+order.getToorders().toString());

          this.orderService.updateOrder(order);
          System.err.println("!!!!!!!!!!! После апдейта");
          System.err.println(order.toString()+order.getToorders().toString());


            order = this.orderService.findOrder(ord);
            System.err.println("!!!!!!!!!!!ОБНОВЛЕНИЕ СУЩНОСТИ");
            System.err.println(order.toString()+order.getToorders().toString());

            ///ПРОСТАНОВКА СТОИМОСТИ ЗАКАЗА
            sum=0;
            for(Toorder t: order.getToorders())
            {
              sum+=t.getPart().getPrice()*t.getNumofparts()+t.getOperation().getPrice();
            }
            System.err.println(sum);
            sum=sum*(1-(double)order.getDiscount()/100);
            System.err.println(sum);

            order.setTotalcost(sum);
            System.err.println(sum + "стоимость ордера"+order.getTotalcost());

            this.orderService.updateOrder(order);

        }

        else
        {
            System.err.println("Создание пустого заказа");
            order = new Order();

            ///Простановка дат
            LocalDate localDate = LocalDate.now();
            order.setDatein(DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate));
            order.setDateout("In Process");


            /**
             * Узнаем ид последнего заказа,
             * ставим ид новому
             */

            int lastOrder=this.orderService.listLastOrder();
            order.setOrderid(lastOrder+1);
            System.err.println(lastOrder);
            System.err.println("Новый ийди заказа"+order.getOrderid());

            //Заполняем заказ

            Part temp = this.partService.getPartById(Integer.parseInt(prds));

            /**
             *Создание и заполнение подзаказа
             */
            Toorder line = new Toorder();

            int lastToOrder=this.toorderService.listLastToorder();
            line.setToorderid(lastToOrder+1);
            line.setOrder(order);
            System.err.println("Номер нового тоордера"+lastToOrder);

            line.setPart(temp);


            line.setNumofparts(numofparts);


            sum += temp.getPrice() * numofparts;


            Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));

            line.setOperation(tmp);
            products.add(line);

            sum += tmp.getPrice();
            System.err.println(sum);
            sum=sum*(1-(double)order.getDiscount()/100);
            System.err.println(sum);

            //Заполняем остальные данные и отправляем на добавление/обновление
            order.setToorders(products);

            order.setTotalcost(sum);
            System.err.println(this.customerService.getCustomerById(client));
            order.setCustomer(this.customerService.getCustomerById(client));
            order.setDiscount((int)discount);

            System.err.println(order.toString()+order.getToorders().toString());

            this.orderService.addOrder(order);
            this.toorderService.addToorder(line);

        }

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

