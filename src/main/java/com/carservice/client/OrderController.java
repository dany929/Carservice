package com.carservice.client;


import com.carservice.model.Operation;
import com.carservice.model.Order;
import com.carservice.model.Part;
import com.carservice.model.Toorder;
import com.carservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;
    private PartService partService;
    private CustomerService customerService;
    private ToorderService toorderService;
    private OperationService operationService;

    @Autowired()
    @Qualifier(value = "partService")
    public void setPartService(PartService ps)
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

    @RequestMapping(value ="/orders/", method = RequestMethod.GET)
    public ModelAndView listOrders() {
       ModelAndView model = new ModelAndView("orders");
        model.addObject("order", new Order());
        model.addObject("listOrders", this.orderService.listOrders());

            ///Обновление цен заказов
        /*
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

         */

        return model;
    }

    /**
     * Добавление клиента к заказу
     * Передача ид клиента на след этап
     */

    @RequestMapping(value ="/orders/chooseCustomer", method = RequestMethod.GET)
    public ModelAndView addOrder() {
        ModelAndView model = new ModelAndView("ChooseCustomer");
        model.addObject("listCustomers", this.customerService.listCustomers());
        return model;
    }

    /**
     *Выбор ЗЧ и услуг, скидки
     * передача в метод по добавлению заказа
     */
    @RequestMapping(value = "/orders/client", method = RequestMethod.POST)
    public  ModelAndView addOrder(String id)
    {
        ModelAndView model = new ModelAndView("ChoosePart");
        model.addObject("orderId", 0);
        model.addObject("gosznak", id);
        model.addObject("listParts", this.partService.listParts());
        model.addObject("listOperations", this.operationService.listOperations());
        return model;
    }

    /**
     * 1этап редактирования подзаказа
     *Принимает ссылку на подзаказ, открывает страницу с выбором услуг
     */
    @RequestMapping(value = "/orders/edittoorder", method = RequestMethod.POST)
    public ModelAndView editToorder(int id)
    {
        ModelAndView model = new ModelAndView("EditToorder");
        model.addObject("toorderid", id);
        model.addObject("listParts", this.partService.listParts());
        model.addObject("listOperations", this.operationService.listOperations());
        return model;
    }
    /**
     *2этап редактирования подзаказа
     * Считывает переданные параметры и обновляет элемент
     */
    @RequestMapping(value = "/orders/updatetoorder", method = RequestMethod.POST)
    public ModelAndView updateToOrder(HttpServletRequest request) throws Exception {

        String prts = request.getParameter("id");

        String oper = request.getParameter("idopr");

        /////////////////////Исключение при неверном запросе услуг////////////////////
        String[] prtss = request.getParameterValues("id");

        String[] opers = request.getParameterValues("idopr");

        Exception ex=null;
        if(prtss.length>1 || opers.length>1)
        {
            throw ex;
        }
        /////////////////////////////////////////////////////////////////////////////
        int numofparts = Integer.parseInt(request.getParameter("num"));

        int tor = Integer.parseInt(request.getParameter("tor"));

        Toorder toorder;

        double sum = 0;
        toorder = this.toorderService.getToorderById(tor);

        Part temp = this.partService.getPartById(Integer.parseInt(prts));
        toorder.setPart(temp);

        Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));
        toorder.setOperation(tmp);
        toorder.setNumofparts(numofparts);

        //Обновляем подзаказ через мердж метод в сессии
        this.toorderService.addToorder(toorder);

        //Заполнение сущностей из за ленивой загрузки и простанока цены
        Toorder f = this.toorderService.getToorderById(tor);
        Order o = f.getOrder();

        List<Toorder> toorderList = this.orderService.getOrderById(o.getOrderid()).getToorders();

        Order order  = toorder.getOrder();

        for(Toorder t: toorderList)
        {
            sum+=t.getPart().getPrice()*t.getNumofparts()+t.getOperation().getPrice();
        }
        System.err.println(sum);
        sum=sum*(1-(double)order.getDiscount()/100);
        System.err.println(sum);

        order.setTotalcost(sum);
        System.err.println(sum + " стоимость ордера "+order.getTotalcost());
        //Обновление цены
        this.orderService.updateOrder(order);
        System.err.println(sum + " стоимость ордера осле апдейта "+order.getTotalcost());
        return new ModelAndView("redirect:/orders/");
    }
    //////////////////////////ДОБАВЛЕНИЕ ЗАКАЗА/////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/orders/add", method = RequestMethod.POST)
    public ModelAndView addOrder(HttpServletRequest request) throws Exception {
        //Обработка полученных параметров
        String prts = request.getParameter("id");
        String oper = request.getParameter("idopr");
        String client = request.getParameter("cst");

        /////////////////////Исключение при неверном запросе услуг////////////////////
        String[] prtss = request.getParameterValues("id");

        String[] opers = request.getParameterValues("idopr");


        Exception ex=null;
        if(prtss.length>1 || opers.length>1)
        {
            throw ex;
        }
        /////////////////////////////////////////////////////////////////////////////
        int numofparts = Integer.parseInt(request.getParameter("num"));

        int ord = Integer.parseInt(request.getParameter("ord"));
        List<Toorder> goods = new ArrayList<>();

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

            Part temp = this.partService.getPartById(Integer.parseInt(prts));

            Toorder tord = new Toorder();

            int lastToOrder=this.toorderService.listLastToorder();
            tord.setToorderid(lastToOrder+1);
            tord.setOrder(order);
            System.err.println("Номер нового тоордера"+lastToOrder);

            tord.setPart(temp);

            tord.setNumofparts(numofparts);

            Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));

            tord.setOperation(tmp);
            goods.add(tord);

            /**
             * Заполнение данных о деталях и услугах
             */
            order.setToorders(goods);

            order.setTotalcost(sum);

            System.err.println("!!!!!!!!!!!");
            System.err.println(order.toString()+order.getToorders().toString());
            this.orderService.updateOrder(order);

            //Добавление нового подзаказа
            this.toorderService.addToorder(tord);

            System.err.println("!!!!!!!!!!!");
            System.err.println(order.toString()+order.getToorders().toString());

            this.orderService.updateOrder(order);

            order = this.orderService.findOrder(ord);

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
            double discount = Double.parseDouble(request.getParameter("dis"));

            System.err.println("Создание пустого заказа");
            order = new Order();

            ///Простановка даты
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

            Part temp = this.partService.getPartById(Integer.parseInt(prts));

            /**
             *Создание и заполнение подзаказа
             */
            Toorder tord = new Toorder();

            int lastToOrder=this.toorderService.listLastToorder();
            tord.setToorderid(lastToOrder+1);
            tord.setOrder(order);
            System.err.println("Номер нового тоордера"+lastToOrder);

            tord.setPart(temp);

            tord.setNumofparts(numofparts);

            sum += temp.getPrice() * numofparts;

            Operation tmp = this.operationService.getOperationById(Integer.parseInt(oper));

            tord.setOperation(tmp);
            goods.add(tord);

            sum += tmp.getPrice();
            System.err.println(sum);
            sum=sum*(1-(double)order.getDiscount()/100);
            System.err.println(sum);

            //Заполняем остальные данные и отправляем на добавление/обновление
            order.setToorders(goods);

            order.setTotalcost(sum);
            System.err.println(this.customerService.getCustomerById(client));
            order.setCustomer(this.customerService.getCustomerById(client));
            order.setDiscount((int)discount);

            System.err.println(order.toString()+order.getToorders().toString());

            this.orderService.addOrder(order);
            this.toorderService.addToorder(tord);
        }
        return new ModelAndView("redirect:/orders/");
    }

    /**
     * Добавление в заказ подзаказов
     */

    @RequestMapping(value = "/orders/edit", method = RequestMethod.POST)
    public ModelAndView editOrder(int id)
    {
        ModelAndView model = new ModelAndView("ChoosePartinToorder");
        model.addObject("orderId", id);
        model.addObject("gosznak", this.orderService.findOrder(id).getCustomer().getGosznak());
        model.addObject("listParts", this.partService.listParts());
        model.addObject("listOperations", this.operationService.listOperations());
        return model;
    }

//////////////////////////РЕДАКТИРОВАНИЕ ЗАКАЗА/////////////////////////////////////////////////////////////////////////
    /**
     *Автозаполнение формы редактрования заказа
     */

    @RequestMapping(value = "/orders/editorder", method = RequestMethod.POST)
    public ModelAndView  updateCustomer(@RequestParam int id)
    {
        ModelAndView model = new ModelAndView("orders");
        model.addObject("order", this.orderService.getOrderById(id));
        model.addObject("listOrders", this.orderService.listOrders());

        System.err.println("Контроллер апдейт заполнил");

        return model;
    }

    /**
     * Обновление заказа со страницы заказов
     * Вызов сущностей из за ленивой загрузки
     */
    @RequestMapping(value ="/orders/update", method = RequestMethod.POST)
    public ModelAndView updateOrder(Order order)
    {
        int id = order.getOrderid();

        List<Toorder> toorderList = this.orderService.getOrderById(id).getToorders();


        double sum = 0;
        for( Toorder t: toorderList)
        {
           sum+=t.getPart().getPrice()*t.getNumofparts()+t.getOperation().getPrice();
        }
        System.err.println(sum);
        sum=sum*(1-(double)order.getDiscount()/100);
        System.err.println(sum);
        order.setTotalcost(sum);

        this.orderService.updateOrder(order);


        return new ModelAndView("redirect:/orders/") ;
    }

    /**
     * Простановка сегодняшней даты при завершении заказа
     */
    @RequestMapping(value = "/orders/cpl", method = RequestMethod.POST)
    public ModelAndView completeOrder(int id)
    {
        this.orderService.competeOrder(id);
        return new ModelAndView("redirect:/orders/");
    }
///////////////////////////////////////////Удаление/////////////////////////////////////////////////////////////////////

    /**
     * Удаление заказ по ид
     */
    @RequestMapping(value = "/orders/delete", method = RequestMethod.POST)
    public ModelAndView deleteOrder(int id)
    {
        this.orderService.removeOrder(id);
        return new ModelAndView("redirect:/orders/");
    }

    /**
     * Обновление стоимости заказа вызов сущностей из за ленивой загрузки
     */

    @RequestMapping(value = "/orders/removetoorder", method = RequestMethod.POST)
    public ModelAndView removeToOrder(int id)
    {
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
        return new ModelAndView("redirect:/orders/");
    }

}

