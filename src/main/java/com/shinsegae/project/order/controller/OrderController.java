package com.shinsegae.project.order.controller;

import com.shinsegae.project.order.service.OrderService;
import com.shinsegae.project.order.vo.OrderInventoryManagementDTO;
import com.shinsegae.project.order.vo.OrderManagementDTO;
import com.shinsegae.project.order.vo.OutputVO;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user/order")
@AllArgsConstructor
public class  OrderController {

    private final OrderService orderService;


    @GetMapping("do_outgoing")
    public String do_outgoing(Model model) {
        List<OrderInventoryManagementDTO> list_confirm = orderService.selectOutputTable();
        model.addAttribute("tableDataConfirm", list_confirm);


        return "user/order/do_outgoing";
    }

    @GetMapping("confirm_outgoing_list")
    public String confirm_outgoing_list(Model model, HttpSession session) {
        List<OrderManagementDTO> list = orderService.selectOutputConfirmTable();
        model.addAttribute("tableData", list);
        model.addAttribute("userId", session.getAttribute("userId"));


        return "user/order/confirm_outgoing_list";
    }

    @PostMapping("/save")
    public String saveOutput(@RequestBody OutputVO outputVO, HttpSession session) {
        orderService.saveOutput(outputVO,session);
        return "redirect:/user/order/do_outgoing";
    }




}